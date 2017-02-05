package com.kerer.weatherapp.mvp.presenter;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.f2prateek.rx.preferences.Preference;
import com.kerer.weatherapp.App;
import com.kerer.weatherapp.WeatherService;
import com.kerer.weatherapp.mvp.model.WeatherModel;
import com.kerer.weatherapp.mvp.view.CitiesListView;

import javax.inject.Inject;

import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by ivan on 04.02.17.
 */

@InjectViewState
public class CitiesListPresenter extends BasePresenter<CitiesListView> {
    private static final String TAG = "CitiesListPresenter";

    @Inject
    WeatherModel mModel;
    @Inject
    Preference<Boolean> mPreference;
    @Inject
    Context mContext;

    public CitiesListPresenter() {
        App.getAppComponent().injectCitiesListPresenter(this);
        updateData();
        startService();
    }

    /**
     * Loading data from model can bee from Internet or from DB (if internet is not aviable)
     */
    public void loadData(String cityName) {

        Subscription subscription = mModel.loadCity(cityName)
                .subscribe(weather -> {
                            getViewState().hideProgress();
                            getViewState().updateWeather(weather);
                        }
                        , throwable -> {
                            getViewState().showError();
                            throwable.printStackTrace();
                        });
        unsubscribeOnDestroy(subscription);
    }

    /**
     * updating data by current city
     */
    private void updateData() {
        if (!mModel.isSomeCitySaved()) {
            getViewState().showNoDataSavedYet();
            return;
        }

        Subscription subscription = mModel.updateWeather()
                .subscribe(weather -> {
                            getViewState().hideProgress();
                            getViewState().updateWeather(weather);
                        }
                        , throwable -> getViewState().showError());

        unsubscribeOnDestroy(subscription);
    }

    /**
     * starting service for updating weather
     */
    private void startService() {

        Subscription subscription = mPreference
                .asObservable()
                .observeOn(Schedulers.io())
                .subscribe(aBoolean -> WeatherService.setServiceAlarm(mContext, aBoolean));

        unsubscribeOnDestroy(subscription);
    }
}
