package com.kerer.weatherapp.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Geocoder;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.kerer.weatherapp.util.DatabaseUtil;
import com.kerer.weatherapp.util.IconsUtil;
import com.kerer.weatherapp.util.NetworkUtil;

import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

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

    @Provides
    @Singleton
    public Preference<Boolean> providePreference(Context context){
        SharedPreferences sharedPreferences = getDefaultSharedPreferences(context);
        RxSharedPreferences rxSharedPreferences = RxSharedPreferences.create(sharedPreferences);
        return rxSharedPreferences.getBoolean("isFirst");
    }

    @Provides
    @Singleton
    public NetworkUtil providNetworkUtil(Context context){
        return new NetworkUtil(context);
    }

    @Provides
    @Singleton
    public IconsUtil provideIconsUtil(){
        return new IconsUtil();
    }
}
