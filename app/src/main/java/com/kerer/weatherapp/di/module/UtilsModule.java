package com.kerer.weatherapp.di.module;

import android.content.Context;
import android.location.Geocoder;

import com.kerer.weatherapp.DatabaseUtil;

import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ivan on 04.02.17.
 */

@Module(includes = ContextModule.class)
public class UtilsModule {

    @Provides
    @Singleton
    public Geocoder provideGeocoder(Context context){
        return new Geocoder(context, Locale.US);
    }

    @Provides
    @Singleton
    public DatabaseUtil provideDatabaseUtil(){
        return new DatabaseUtil();
    }
}
