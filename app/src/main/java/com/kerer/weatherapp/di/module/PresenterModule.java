package com.kerer.weatherapp.di.module;

import com.kerer.weatherapp.mvp.presenter.WeatherServicePresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Date: 05.02.17
 * Time: 14:01
 *
 * @author Ivan Kerer
 */

@Module
public class PresenterModule {

    //just for WeatherService
    @Provides
    @Singleton
    WeatherServicePresenter provideWeatherServicePresenter(){
        return new WeatherServicePresenter();
    }

}
