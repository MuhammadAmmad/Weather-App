package com.kerer.weatherapp.entity;

/**
 * Created by ivan on 04.02.17.
 */

public class CurrentlyWeather {
    private double mTemperature;
    private String mDescription;
    private String mSkyState;

    public CurrentlyWeather(double mTemperature, String mDescription, String mSkyState) {
        this.mTemperature = mTemperature;
        this.mDescription = mDescription;
        this.mSkyState = mSkyState;
    }

    public double getmTemperature() {
        return mTemperature;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmSkyState() {
        return mSkyState;
    }
}
