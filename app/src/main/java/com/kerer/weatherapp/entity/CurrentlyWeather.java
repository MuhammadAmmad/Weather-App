package com.kerer.weatherapp.entity;


import com.orm.SugarRecord;

/**
 * Created by ivan on 04.02.17.
 */

public class CurrentlyWeather extends SugarRecord {
    private double mTemperature;
    private String mDescription;
    private String mSkyState;

    public CurrentlyWeather() {
    }

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

    public static Builder newBuilder(){
        return new CurrentlyWeather().new Builder();
    }

    public class Builder {

        public Builder() {
        }

        public Builder setTemperature(double temperature) {
            CurrentlyWeather.this.mTemperature = temperature;
            return this;
        }

        public Builder setDescription(String description) {
            CurrentlyWeather.this.mDescription = description;
            return this;
        }

        public Builder setSkyState(String skyState) {
            CurrentlyWeather.this.mSkyState = skyState;
            return this;
        }

        public CurrentlyWeather build() {
            return CurrentlyWeather.this;
        }

    }


}
