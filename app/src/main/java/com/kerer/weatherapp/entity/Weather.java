package com.kerer.weatherapp.entity;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 04.02.17.
 */
public class Weather extends SugarRecord{

    private String mCity;
    private CurrentlyWeather mCurrentlyWeather;
    private List<DayWeather> mWeatherByDays;

    public Weather() {
    }

    public Weather(CurrentlyWeather mCurrentlyWeather, List<DayWeather> mWeatherByDays, String mCity) {
        this.mCurrentlyWeather = mCurrentlyWeather;
        this.mWeatherByDays = mWeatherByDays;
        this.mCity = mCity;
    }

    public CurrentlyWeather getmCurrentlyWeather() {
        return mCurrentlyWeather;
    }

    public List<DayWeather> getmWeatherByDays() {
        return mWeatherByDays;
    }

    public void setmCurrentlyWeather(CurrentlyWeather mCurrentlyWeather) {
        this.mCurrentlyWeather = mCurrentlyWeather;
    }

    public void setmWeatherByDays(ArrayList<DayWeather> mWeatherByDays) {
        this.mWeatherByDays = mWeatherByDays;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public void setmWeatherByDays(List<DayWeather> mWeatherByDays) {
        this.mWeatherByDays = mWeatherByDays;
    }
}
