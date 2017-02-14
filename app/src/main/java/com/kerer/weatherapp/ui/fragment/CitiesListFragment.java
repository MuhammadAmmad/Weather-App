package com.kerer.weatherapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.kerer.weatherapp.R;
import com.kerer.weatherapp.entity.DayWeather;
import com.kerer.weatherapp.entity.Weather;
import com.kerer.weatherapp.mvp.presenter.CitiesListPresenter;
import com.kerer.weatherapp.mvp.view.CitiesListView;

import java.util.ArrayList;
import java.util.List;

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

    private DailyWeatherAdapter mAdapter;

    @BindView(R.id.fragment_main_change_city)
    EditText mChangeCityEd;
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
    @BindView(R.id.fragment_main_progress)
    ProgressBar mProgress;
    @BindView(R.id.fragment_main_recyclerview)
    RecyclerView mRecyclerView;

    public static CitiesListFragment newInstance() {
        return new CitiesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, v);
        showProgress();
        mPresenter.updateData();
        init();
        return v;
    }

    private void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new DailyWeatherAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);

        mChangeCityEd.setOnKeyListener((v, code, event) -> {
            mPresenter.loadData(mChangeCityEd.getText().toString());
            return true;
        });
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(GONE);
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
        //setting current weather
        mCityNameTv.setText(mPresenter.getCurrentCity() + ", " + weather.getmCurrentlyWeather().getmDescription());
        mHumanidityTv.setText(String.valueOf(weather.getmCurrentlyWeather().getmHumidity()).split("\\.")[0] + "%");
        mPressureTv.setText(String.valueOf(weather.getmCurrentlyWeather().getmPressure()).split("\\.")[0] + " мм рт. ст.");
        mWindSpeedTv.setText(String.valueOf(weather.getmCurrentlyWeather().getmWindSpeed()).split("\\.")[0] + " м/с");
        mCurrentTemperatureTv.setText(String.valueOf(weather.getmCurrentlyWeather().getmTemperature()).split("\\.")[0] + (char) 0X00b0);
        mCurrentWeatherIcoTv.setText(weather.getmCurrentlyWeather().getmWeatherIco());

        //setting daily weather
        mAdapter.updateItems(weather.getmWeatherByDays());
    }

    private void toast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT)
                .show();
    }

    public class DailyWeatherHolder extends RecyclerView.ViewHolder {
        private DayWeather mDayWeather;

        @BindView(R.id.daily_item_temperature_tv)
        TextView mTemperatureTv;
        @BindView(R.id.daily_item_day_of_week)
        TextView mDayOfWeekTv;
        @BindView(R.id.daily_item_weather_ico)
        TextView mIco;

        public DailyWeatherHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(DayWeather dayWeather) {
            mDayWeather = dayWeather;

            mTemperatureTv.setText(String.valueOf(dayWeather.getmMaxTemperature()).split("\\.")[0] + " / " + String.valueOf(dayWeather.getmMinTemperature()).split("\\.")[0]);
            mDayOfWeekTv.setText(dayWeather.getmDayOfWeek());
            mIco.setText(dayWeather.getmIco());
        }

    }

    public class DailyWeatherAdapter extends RecyclerView.Adapter<CitiesListFragment.DailyWeatherHolder> {
        private List<DayWeather> mDayWheathers;

        public DailyWeatherAdapter(List<DayWeather> mDayWheathers) {
            this.mDayWheathers = mDayWheathers;
        }

        @Override
        public CitiesListFragment.DailyWeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.daily_weather_item_layout, parent, false);
            return new DailyWeatherHolder(v);
        }

        @Override
        public void onBindViewHolder(DailyWeatherHolder holder, int position) {
            holder.bind(mDayWheathers.get(position));
        }

        @Override
        public int getItemCount() {
            return mDayWheathers.size();
        }

        public void updateItems(List<DayWeather> dayWeathers) {
            mDayWheathers = dayWeathers;
            notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDestroy();
    }


}
