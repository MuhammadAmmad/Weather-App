package com.kerer.weatherapp.di.module;

import android.location.Geocoder;

import com.kerer.weatherapp.util.DatabaseUtil;
import com.kerer.weatherapp.api.DarkSkyApi;
import com.kerer.weatherapp.mvp.model.WeatherModel;
import com.kerer.weatherapp.util.IconsUtil;
import com.kerer.weatherapp.util.NetworkUtil;

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
    WeatherModel provideCitiesListModel(DarkSkyApi darkSkyApi, Geocoder geocoder, DatabaseUtil databaseUtil, NetworkUtil networkUtil, IconsUtil iconsUtil){
        return new WeatherModel(darkSkyApi, geocoder, databaseUtil, networkUtil, iconsUtil);
    }
}
