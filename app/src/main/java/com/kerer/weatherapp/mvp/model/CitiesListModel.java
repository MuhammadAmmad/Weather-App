package com.kerer.weatherapp.mvp.model;

import android.location.Geocoder;
import android.util.Log;

import com.kerer.weatherapp.api.DarkSkyApi;
import com.kerer.weatherapp.api.dto.WeatherResponseDTO;
import com.kerer.weatherapp.entity.CurrentlyWeather;
import com.kerer.weatherapp.entity.DayWeather;
import com.kerer.weatherapp.entity.Weather;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ivan on 04.02.17.
 */

public class CitiesListModel {

    private DarkSkyApi mDarkSkyApi;
    private Geocoder mGeocoder;

    @Inject
    public CitiesListModel(DarkSkyApi darkSkyApi, Geocoder geocoder) {
        this.mDarkSkyApi = darkSkyApi;
        this.mGeocoder = geocoder;
    }

    public Observable<Weather> loadCity() {

        return mDarkSkyApi.getCityInfo("48.292079,25.935837")
                .map(this::getWeatherFromResponse)
                .doOnNext(this::cachToDb)
                .doOnError(throwable -> Log.d("TAG_ERROR", throwable.getMessage()))
                .onErrorResumeNext(getSavedInfo())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * @param dto http request response. Need to be converted at correct model
     * @return
     */
    private Weather getWeatherFromResponse(WeatherResponseDTO dto) {
        CurrentlyWeather currentlyWeather = new CurrentlyWeather(dto.getCurrentlyDTO().getTemperature(),
                dto.getCurrentlyDTO().getSummary(), dto.getCurrentlyDTO().getPrecipType(), "Default");


        List<DayWeather> daysWeather = Observable.from(dto.getDailyDTO().getData())
                .map(datumDTO -> new DayWeather(datumDTO.getTemperatureMin(), datumDTO.getTemperatureMax(), datumDTO.getTime()))
                .toList()
                .toBlocking()
                .first();

        return new Weather(currentlyWeather, daysWeather, "Default");
    }

    /**
     * deleting all records from database.
     * saving new record
     *
     * @param weather new record to save
     */
    private void cachToDb(Weather weather) {
        //delete all data from DB
        Weather.deleteAll(Weather.class);
        DayWeather.deleteAll(DayWeather.class);
        CurrentlyWeather.deleteAll(CurrentlyWeather.class);

        //insert new record
        weather.save();
        weather.getmCurrentlyWeather().save();
        for (DayWeather item : weather.getmWeatherByDays()) {
            item.save();
        }
    }

    /**
     * getting saved into Db info, while no internet connection
     * @return Observable with Weather
     */
    private Observable<Weather> getSavedInfo() {
        Log.d("ASFASF", "asfasfasf");

        return Observable
                .from(Weather.listAll(Weather.class))
                .doOnNext(weather -> {
                    weather.setmCurrentlyWeather(CurrentlyWeather.findWithQuery(CurrentlyWeather.class, "Select * from CURRENTLY_WEATHER").get(0));
                    weather.setmWeatherByDays(DayWeather.findWithQuery(DayWeather.class, "Select * from DAY_WEATHER"));
                });
    }
}
