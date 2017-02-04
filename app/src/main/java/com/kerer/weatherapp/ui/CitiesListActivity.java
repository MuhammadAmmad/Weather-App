package com.kerer.weatherapp.ui;

import android.support.v4.app.Fragment;

import com.kerer.weatherapp.ui.fragment.CitiesListFragment;

public class CitiesListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return CitiesListFragment.newInstance();
    }
}
