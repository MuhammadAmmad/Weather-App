package com.kerer.weatherapp.mvp.view;

import com.arellomobile.mvp.MvpView;

/**
 * 05.02.17
 * Author ivan
 */

public interface WeatherServiceView extends MvpView {
    void showUpdates(String title, String subTitle);
}
