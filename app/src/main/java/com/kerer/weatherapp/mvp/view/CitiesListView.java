package com.kerer.weatherapp.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.kerer.weatherapp.entity.Weather;

/**
 * Created by ivan on 04.02.17.
 */


public interface CitiesListView extends MvpView{
    void showProgress();
    void hideProgress();
    void showEmptyList();
    void showError();
    void updateWeather(Weather weather);
}
