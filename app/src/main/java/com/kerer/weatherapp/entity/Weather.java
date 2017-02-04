package com.kerer.weatherapp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 04.02.17.
 */
public class Weather{

    private CurrentlyWeather mCurrentlyWeather;

    private List<DayWeather> mWeatherByDays;

    public Weather() {
    }

    public Weather(CurrentlyWeather mCurrentlyWeather, List<DayWeather> mWeatherByDays) {
        this.mCurrentlyWeather = mCurrentlyWeather;
        this.mWeatherByDays = mWeatherByDays;
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
}
