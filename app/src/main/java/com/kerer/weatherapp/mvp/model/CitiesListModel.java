package com.kerer.weatherapp.mvp.model;

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
    DarkSkyApi mDarkSkyApi;

    @Inject
    public CitiesListModel(DarkSkyApi darkSkyApi) {
        mDarkSkyApi = darkSkyApi;
    }

    public Observable<Weather> loadCity() {
        return mDarkSkyApi.getCityInfo("48.292079,25.935837")
                .map(this::getWeatherFromResponse)
                .doOnError(throwable -> {
                    Log.d("TAFFFF", throwable.getMessage());
                    throwable.printStackTrace();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Weather getWeatherFromResponse(WeatherResponseDTO dto) {
        CurrentlyWeather currentlyWeather = new CurrentlyWeather(dto.getCurrentlyDTO().getTemperature(),
                dto.getCurrentlyDTO().getSummary(), dto.getCurrentlyDTO().getPrecipType());


        List<DayWeather> daysWeather = Observable.from(dto.getDailyDTO().getData())
                .map(datumDTO -> new DayWeather(datumDTO.getTemperatureMin(), datumDTO.getTemperatureMax(), datumDTO.getTime()))
                .toList()
                .toBlocking()
                .first();
        return new Weather(currentlyWeather, daysWeather);
    }
}
