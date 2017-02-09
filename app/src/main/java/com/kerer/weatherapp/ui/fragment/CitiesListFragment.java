package com.kerer.weatherapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.kerer.weatherapp.R;
import com.kerer.weatherapp.entity.Weather;
import com.kerer.weatherapp.mvp.presenter.CitiesListPresenter;
import com.kerer.weatherapp.mvp.view.CitiesListView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * Date: 04.02.17
 * Time: null
 *
 * @author Ivan Kerer
 */

public class CitiesListFragment extends BaseFragment implements CitiesListView {
    @InjectPresenter
    CitiesListPresenter mPresenter;

    @BindView(R.id.fragment_main_change_city)
    TextView mChangeCityTv;
    @BindView(R.id.fragment_main_toolbar)
    TextView mCityNameTv;
    @BindView(R.id.fragment_main_wind_speed_tv)
    TextView mWindSpeedTv;
    @BindView(R.id.fragment_main_humidity_tv)
    TextView mHumanidityTv;
    @BindView(R.id.fragment_main_pressure_tv)
    TextView mPressureTv;
    @BindView(R.id.fragment_main_temperature_tv)
    TextView mCurrentTemperatureTv;
    @BindView(R.id.fragment_main_weather_ico_tv)
    TextView mCurrentWeatherIcoTv;

    public static CitiesListFragment newInstance(){
        return new CitiesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        showProgress();
        mPresenter.loadData("Chernivtsi");
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
        ProgressBar r = (ProgressBar) getView().findViewById(R.id.asfasfasfass);
        r.setVisibility(GONE);
    }

    @Override
    public void showNoDataSavedYet() {
        toast(getString(R.string.no_info_yet));
    }

    @Override
    public void showError() {
        toast("ERRORRRRRRRRR!!!");
    }

    @Override
    public void showNoInternet() {

    }

    @Override
    public void updateWeather(Weather weather) {
        mCityNameTv.setText(mPresenter.getCuttrntCity() + ", " + weather.getmCurrentlyWeather().getmDescription());
        mHumanidityTv.setText(String .valueOf(weather.getmCurrentlyWeather().getmHumidity()).split("\\.")[0]  + "%");
        mPressureTv.setText(String.valueOf(weather.getmCurrentlyWeather().getmPressure()).split("\\.")[0]  + " мм рт. ст.");
        mWindSpeedTv.setText(String.valueOf(weather.getmCurrentlyWeather().getmWindSpeed()).split("\\.")[0]  + " м/с");
        mCurrentTemperatureTv.setText(String.valueOf(weather.getmCurrentlyWeather().getmTemperature()).split("\\.")[0] + (char) 0X00b0);
        mCurrentWeatherIcoTv.setText(weather.getmCurrentlyWeather().getmWeatherIco());
    }

    private void toast(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDestroy();
    }



}
