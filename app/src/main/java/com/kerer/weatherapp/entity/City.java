package com.kerer.weatherapp.entity;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * 05.02.17
 * Author ivan
 */
@Table
public class City extends SugarRecord {
    private String mCityName;

    public City() {
    }

    public City(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }
}
