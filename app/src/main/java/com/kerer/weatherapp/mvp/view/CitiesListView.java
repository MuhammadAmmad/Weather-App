package com.kerer.weatherapp.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.kerer.weatherapp.entity.Weather;

/**
 *
 */


public interface CitiesListView extends MvpView{
    void showProgress();
    void hideProgress();
    void showNoDataSavedYet();
    void showError();
    void showNoInternet();
    void updateWeather(Weather weather);
}
