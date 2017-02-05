package com.kerer.weatherapp;

import com.kerer.weatherapp.entity.City;
import com.kerer.weatherapp.entity.CurrentlyWeather;
import com.kerer.weatherapp.entity.DayWeather;
import com.kerer.weatherapp.entity.Weather;

import java.util.List;

import rx.Observable;

/**
 * 05.02.17
 * Author ivan
 */

public class DatabaseUtil {
    /**
     * deleting all records from database.
     * saving new record
     * @param weather new record to save
     */
    public void cachToDb(Weather weather) {
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
     * saving new city into databse
     * @param cityName new city name
     */
    public void saveCity(String cityName){
        City.deleteAll(City.class);
        new City(cityName).save();
    }

    /**
     * check is some city saved
     * @return saved city name or null (if no cities saved yet)
     */
    public String getSavedCity(){
        List<City> savedCity = City
                .findWithQuery(City.class, "Select * from CITY");
        if (savedCity.isEmpty()){
            return null;
        }
        return savedCity.get(0).getmCityName();
    }

    /**
     * getting saved into Db info, while no internet connection
     * @return Observable with Weather
     */
    public Observable<Weather> getSavedInfo() {
        return Observable
                .from(Weather.listAll(Weather.class))
                .doOnNext(weather -> {
                    weather.setmCurrentlyWeather(CurrentlyWeather.findWithQuery(CurrentlyWeather.class, "Select * from CURRENTLY_WEATHER").get(0));
                    weather.setmWeatherByDays(DayWeather.findWithQuery(DayWeather.class, "Select * from DAY_WEATHER"));
                });
    }
}
