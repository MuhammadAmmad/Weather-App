package com.kerer.weatherapp.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.kerer.weatherapp.api.dto.DatumDTO;
import com.kerer.weatherapp.entity.DayWeather;
import com.kerer.weatherapp.entity.Weather;

import java.util.List;

/**
 * Created by ivan on 04.02.17.
 */


public interface CitiesListView extends MvpView{
    void showCitiesList(List<DayWeather> cities);
    void addToCityToList(DatumDTO datumDTO);
    void showProgress();
    void hideProgress();
    void showEmptyList();
    void showError();
    void updateWeather(Weather weather);
}
