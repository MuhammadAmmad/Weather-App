package com.kerer.weatherapp.di.module;

import com.kerer.weatherapp.api.DarkSkyApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by ivan on 04.02.17.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    public DarkSkyApi proviewYahooApi(Retrofit retrofit){
        return retrofit.create(DarkSkyApi.class);
    }
}
