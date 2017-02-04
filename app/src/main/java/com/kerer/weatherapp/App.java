package com.kerer.weatherapp;

import android.app.Application;

import com.kerer.weatherapp.di.AppComponent;
import com.kerer.weatherapp.di.DaggerAppComponent;
import com.kerer.weatherapp.di.module.ContextModule;


/**
 * Created by ivan on 04.02.17.
 */

public class App extends Application {
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        //SugarContext.init(this);
    }

    public static AppComponent getAppComponent(){
        return sAppComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
       // SugarContext.terminate();
    }
}
