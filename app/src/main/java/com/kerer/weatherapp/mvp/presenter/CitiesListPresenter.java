package com.kerer.weatherapp.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.kerer.weatherapp.App;
import com.kerer.weatherapp.mvp.model.CitiesListModel;
import com.kerer.weatherapp.mvp.view.CitiesListView;

import javax.inject.Inject;

/**
 * Created by ivan on 04.02.17.
 */

@InjectViewState
public class CitiesListPresenter extends BasePresenter<CitiesListView> {
    private static final String TAG = "CitiesListPresenter";

    @Inject
    CitiesListModel mModel;

    public CitiesListPresenter() {
        App.getAppComponent().injectCitiesListPresenter(this);
        updateData();
    }

    /**
     * Loading data from model can bee from Internet or from DB (if internet is not aviable)
     */
    public void loadData(String cityName) {

        mModel.loadCity(cityName)
                .subscribe(weather -> {
                            getViewState().hideProgress();
                            getViewState().updateWeather(weather);
                        }
                        , throwable -> {getViewState().showError(); throwable.printStackTrace();});
    }

    /**
     * updating data by current city
     */
    private void updateData() {
        if (!mModel.isSomeCitySaved()){
            getViewState().showNoDataSavedYet();
            return;
        }

        mModel.updateWeather()
                .subscribe(weather -> {
                            getViewState().hideProgress();
                            getViewState().updateWeather(weather);
                        }
                        , throwable -> getViewState().showError());
    }

}
