package com.kerer.weatherapp.entity;


import com.orm.SugarRecord;

/**
 * Created by ivan on 04.02.17.
 */

public class DayWeather extends SugarRecord {
    private Double minTemperature;
    private Double maxTemperature;
    private Integer time;

    public DayWeather() {
    }

    public DayWeather(Double minTemperature, Double maxTemperature, Integer time) {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.time = time;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public Integer getTime() {
        return time;
    }
}
