package com.kerer.weatherapp.di.module;

import android.location.Geocoder;

import com.kerer.weatherapp.DatabaseUtil;
import com.kerer.weatherapp.api.DarkSkyApi;
import com.kerer.weatherapp.mvp.model.WeatherModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ivan on 04.02.17.
 */
@Module(includes = ApiModule.class)
public class ModelsModule {
    @Provides
    @Singleton
    WeatherModel provideCitiesListModel(DarkSkyApi darkSkyApi, Geocoder geocoder, DatabaseUtil databaseUtil){
        return new WeatherModel(darkSkyApi, geocoder, databaseUtil);
    }
}
