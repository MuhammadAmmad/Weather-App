package com.kerer.weatherapp.entity.mapper;

import com.kerer.weatherapp.api.dto.WeatherResponseDTO;
import com.kerer.weatherapp.entity.DayWeather;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ivan on 04.02.17.
 */

public class DayWeatherMapper implements Func1<WeatherResponseDTO, List<DayWeather>> {

    @Override
    public List<DayWeather> call(WeatherResponseDTO weatherResponseDTO) {
        if (weatherResponseDTO == null){
            return null;
        }
        return Observable.from(weatherResponseDTO.getDailyDTO().getData())
                .map(datumDTO -> new DayWeather(datumDTO.getTemperatureMin(), datumDTO.getTemperatureMax(), datumDTO.getTime()))
                .toList()
                .toBlocking()
                .first();
    }
}
