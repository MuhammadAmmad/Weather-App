package com.kerer.weatherapp.di.module;

import com.kerer.weatherapp.api.DarkSkyApi;
import com.kerer.weatherapp.mvp.model.CitiesListModel;

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
    CitiesListModel provideCitiesListModel(DarkSkyApi darkSkyApi){
        return new CitiesListModel(darkSkyApi);
    }
}
