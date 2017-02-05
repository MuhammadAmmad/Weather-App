package com.kerer.weatherapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.kerer.weatherapp.App;
import com.kerer.weatherapp.mvp.model.WeatherModel;
import com.kerer.weatherapp.mvp.view.WeatherServiceView;

import javax.inject.Inject;

/**
 * Date: 05.02.17
 * Time: 12:29
 *
 * @author Ivan Kerer
 */
@InjectViewState
public class WeatherServicePresenter extends BasePresenter<WeatherServiceView> {
    private static final String TAG = "WeatherServicePresenter";

    @Inject
    WeatherModel mModel;

    public WeatherServicePresenter() {
        App.getAppComponent().injectWeatherServicePresenter(this);
    }

    public void loadData(){
        if (mModel.isSomeCitySaved()){
            mModel.updateWeather().subscribe(weather -> {
               getViewState().showUpdates(String.valueOf(weather.getmCurrentlyWeather().getmTemperature()), weather.getmCurrentlyWeather().getmDescription()); // todo param - some data
            });
        } else {
            getViewState().showUpdates("Add city please", "No cities added yet");
        }
    }


}
