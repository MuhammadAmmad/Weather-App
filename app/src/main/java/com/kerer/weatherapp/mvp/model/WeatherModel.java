package com.kerer.weatherapp.mvp.model;

import android.location.Address;
import android.location.Geocoder;

import com.kerer.weatherapp.api.DarkSkyApi;
import com.kerer.weatherapp.api.dto.WeatherResponseDTO;
import com.kerer.weatherapp.entity.CurrentlyWeather;
import com.kerer.weatherapp.entity.DayWeather;
import com.kerer.weatherapp.entity.Weather;
import com.kerer.weatherapp.util.DatabaseUtil;
import com.kerer.weatherapp.util.IconsUtil;
import com.kerer.weatherapp.util.NetworkUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ivan on 04.02.17.
 */

public class WeatherModel {

    private DarkSkyApi mDarkSkyApi;
    private Geocoder mGeocoder;
    private DatabaseUtil mDatabaseUtil;
    private NetworkUtil mNetworkUtil;
    private IconsUtil mIconsUtil;

    @Inject
    public WeatherModel(DarkSkyApi darkSkyApi, Geocoder geocoder, DatabaseUtil databaseUtil, NetworkUtil networkUtil, IconsUtil iconsUtil) {
        this.mDarkSkyApi = darkSkyApi;
        this.mGeocoder = geocoder;
        this.mDatabaseUtil = databaseUtil;
        this.mNetworkUtil = networkUtil;
        this.mIconsUtil = iconsUtil;
    }

    /**
     * Loading weater information -> save to database;
     * if no internet connection - load from database;
     *
     * @param city city for loading weather;
     * @return Observable with Weather object for working with it in presenter
     */
    public Observable<Weather> loadCity(String city) {

        if (!mNetworkUtil.isNetworkAvailableAndConnected()) {
            return mDatabaseUtil.getSavedInfo()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
        return mDarkSkyApi.getCityInfo(getFromAddress(city))
                .map(this::getWeatherFromResponse)
                .doOnNext(weather -> {
                    mDatabaseUtil.saveCity(city);
                    mDatabaseUtil.cachToDb(weather);
                })
                .onErrorResumeNext(throwable -> mDatabaseUtil.getSavedInfo())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * call when user need update weather for saved city
     *
     * @return Observable with Weather object for working with it in presenter
     */
    public Observable<Weather> updateWeather() {
        return loadCity(mDatabaseUtil.getSavedCity());
    }

    public boolean isSomeCitySaved() {
        return mDatabaseUtil.getSavedCity() != null;
    }

    /**
     * getting city name from db
     *
     * @return city name
     */
    public String getCurrentCityName() {
        return mDatabaseUtil.getSavedCity();
    }

    /**
     * @param dto http request response. Need to be converted at correct model
     * @return entity for View
     */
    private Weather getWeatherFromResponse(WeatherResponseDTO dto) {
       /* CurrentlyWeather currentlyWeather = new CurrentlyWeather(dto.getCurrentlyDTO().getTemperature(),
                dto.getCurrentlyDTO().getSummary(), dto.getCurrentlyDTO().getPrecipType());*/

        CurrentlyWeather currentlyWeather = CurrentlyWeather.newBuilder()
                .setTemperature(dto.getCurrentlyDTO().getTemperatureInCelcium().intValue())
                .setDescription(dto.getCurrentlyDTO().getSummary())
                .setHumidity(dto.getCurrentlyDTO().getHumidity())
                .setWindSpreed(dto.getCurrentlyDTO().getWindSpeed())
                .setPressure((int) (dto.getCurrentlyDTO().getPressure() * 0.75)) //Hectopascals to mmHg
                .setIco(mIconsUtil.getFromText(dto.getCurrentlyDTO().getIcon()))
                .build();

        List<DayWeather> daysWeather = Observable.from(dto.getDailyDTO().getData())
                .map(datumDTO -> DayWeather.newBuilder()
                        .maxTemperature(datumDTO.getMaxTemperatureInCelcium().intValue())
                        .minTemperature(datumDTO.getMinTemperatureInCelcium().intValue())
                        .dayOfWeek(timestampToDayOfWeek(datumDTO.getTime()))
                        .ico(mIconsUtil.getFromText(datumDTO.getIcon()))
                        .build())
                .toList()
                .toBlocking()
                .first();

        return new Weather(currentlyWeather, daysWeather);
    }

    /**
     * make day of week from UNIX time stamp
     *
     * @param time - UNIZ timestamp
     * @return day of week
     */
    private String timestampToDayOfWeek(Integer time) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE"); // the format of your date
        String formattedDate = simpleDateFormat.format(date);

        return formattedDate;
    }

    /**
     * Method for casting address to coordinates
     *
     * @param address city name what input user
     * @return coordinates like 23.2323,22.323233
     */
    private String getFromAddress(String address) {
        try {
            List<Address> addresses = mGeocoder.getFromLocationName(address, 1);
            if (!addresses.isEmpty()) {
                return addresses.get(0).getLatitude() + "," + addresses.get(0).getLongitude();
            }
        } catch (IOException ignored) {
        }
        return "1,1";

    }
}
