package com.kerer.weatherapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.gson.Gson;
import com.kerer.weatherapp.R;
import com.kerer.weatherapp.entity.Weather;
import com.kerer.weatherapp.mvp.presenter.CitiesListPresenter;
import com.kerer.weatherapp.mvp.view.CitiesListView;

/**
 * Created by ivan on 04.02.17.
 */

public class CitiesListFragment extends MvpAppCompatFragment implements CitiesListView {

    @InjectPresenter
    CitiesListPresenter mPresenter;

    public static CitiesListFragment newInstance(){
        return new CitiesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cities_list, container, false);
        mPresenter.loadData();

        return v;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showEmptyList() {
        toast("Empty");
    }

    @Override
    public void showError() {
        toast("ERRORRRRRRRRR!!!");
    }

    @Override
    public void updateWeather(Weather weather) {
        Log.d("TAGGGGG", new Gson().toJson(weather));
    }


    private void toast(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT)
                .show();
    }
}
