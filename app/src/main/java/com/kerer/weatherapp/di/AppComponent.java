package com.kerer.weatherapp.di;

import com.kerer.weatherapp.service.WeatherService;
import com.kerer.weatherapp.api.DarkSkyApi;
import com.kerer.weatherapp.di.module.ApiModule;
import com.kerer.weatherapp.di.module.ContextModule;
import com.kerer.weatherapp.di.module.PresenterModule;
import com.kerer.weatherapp.di.module.RetrofitModule;
import com.kerer.weatherapp.di.module.UtilsModule;
import com.kerer.weatherapp.mvp.presenter.CitiesListPresenter;
import com.kerer.weatherapp.mvp.presenter.WeatherServicePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ivan on 04.02.17.
 */

@Singleton
@Component(modules = {ApiModule.class, RetrofitModule.class, ContextModule.class, UtilsModule.class, PresenterModule.class})
public interface AppComponent {
    DarkSkyApi getYahooApi();
    void injectCitiesListPresenter(CitiesListPresenter presenter);
    void injectWeatherServicePresenter(WeatherServicePresenter presenter);
    void injectWeatherService(WeatherService weatherService);
}
