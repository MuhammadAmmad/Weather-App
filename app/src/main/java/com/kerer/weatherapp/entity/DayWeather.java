package com.kerer.weatherapp.entity;


import com.orm.SugarRecord;

/**
 * Created by ivan on 04.02.17.
 */

public class DayWeather extends SugarRecord {
    private Double mMinTemperature;
    private Double mMaxTemperature;
    private String mDescription;
    private String mIco;

    public DayWeather() {

    }

    public Double getmMinTemperature() {
        return mMinTemperature;
    }

    public void setmMinTemperature(Double mMinTemperature) {
        this.mMinTemperature = mMinTemperature;
    }

    public Double getmMaxTemperature() {
        return mMaxTemperature;
    }

    public void setmMaxTemperature(Double mMaxTemperature) {
        this.mMaxTemperature = mMaxTemperature;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmIco() {
        return mIco;
    }

    public void setmIco(String mIco) {
        this.mIco = mIco;
    }

    public static DayWeather.Builder newBuilder() {
        return new DayWeather().new Builder();
    }

    public class Builder {

        public Builder() {
        }

        public Builder minTemperature(double minTemperature){
            DayWeather.this.mMinTemperature = minTemperature;
            return this;
        }

        public  Builder maxTemperature(double maxTemperature){
            DayWeather.this.mMaxTemperature = maxTemperature;
            return this;
        }

        public Builder description(String description){
            DayWeather.this.mDescription = description;
            return this;
        }

        public Builder ico(String ico){
            DayWeather.this.mIco = ico;
            return this;
        }

        public DayWeather build(){
            return DayWeather.this;
        }
    }
}
