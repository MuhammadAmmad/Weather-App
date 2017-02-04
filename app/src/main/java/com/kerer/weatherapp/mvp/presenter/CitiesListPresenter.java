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
public class CitiesListPresenter extends  BasePresenter<CitiesListView>{
    private static final String TAG = "CitiesListPresenter";

    @Inject
    CitiesListModel mModel;

    public CitiesListPresenter() {
        App.getAppComponent().injectCitiesListPresenter(this);
    }

    public void loadData(){

        mModel.loadCity()
                .subscribe(weather -> getViewState().updateWeather(weather), Throwable::printStackTrace);
    }

    public void onAddButtonClick(){
        //TODO dialog
    }



}
