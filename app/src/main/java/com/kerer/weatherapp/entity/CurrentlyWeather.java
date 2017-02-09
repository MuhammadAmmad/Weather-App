package com.kerer.weatherapp.entity;


import com.orm.SugarRecord;

/**
 * Created by ivan on 04.02.17.
 */

public class CurrentlyWeather extends SugarRecord {
    private int mTemperature;
    private String mDescription;
    private double mHumidity;
    private double mWindSpeed;
    private int mPressure;
    private String mWeatherIco;


    public CurrentlyWeather() {
    }

    private CurrentlyWeather(int mTemperature, String mDescription, double mHumidity) {
        this.mTemperature = mTemperature;
        this.mDescription = mDescription;
        this.mHumidity = mHumidity;
    }

    public String getmWeatherIco() {
        return mWeatherIco;
    }

    public void setmWeatherIco(String mWeatherIco) {
        this.mWeatherIco = mWeatherIco;
    }

    public double getmTemperature() {
        return mTemperature;
    }

    public void setmTemperature(int mTemperature) {
        this.mTemperature = mTemperature;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public double getmHumidity() {
        return mHumidity;
    }

    public void setmHumidity(double mHumidity) {
        this.mHumidity = mHumidity;
    }

    public double getmWindSpeed() {
        return mWindSpeed;
    }

    public void setmWindSpeed(int mWindSpeed) {
        this.mWindSpeed = mWindSpeed;
    }

    public double getmPressure() {
        return mPressure;
    }

    public void setmPressure(int mPressure) {
        this.mPressure = mPressure;
    }

    public static Builder newBuilder() {
        return new CurrentlyWeather().new Builder();
    }


    public class Builder {

        public Builder() {
        }

        public Builder setTemperature(int temperature) {
            CurrentlyWeather.this.mTemperature = temperature;
            return this;
        }

        public Builder setDescription(String description) {
            CurrentlyWeather.this.mDescription = description;
            return this;
        }

        public Builder setHumidity(double humidity) {
            CurrentlyWeather.this.mHumidity = humidity * 100;
            return this;
        }

        public Builder setWindSpreed(double windSpeed) {
            CurrentlyWeather.this.mWindSpeed = windSpeed;
            return this;
        }

        public Builder setPressure(int pressure) {
            CurrentlyWeather.this.mPressure = pressure;
            return this;
        }
        public Builder setIco(String ico){
            CurrentlyWeather.this.mWeatherIco = ico;

            return this;
        }

        public CurrentlyWeather build() {
            return CurrentlyWeather.this;
        }

    }


}
