package com.kerer.weatherapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kerer.weatherapp.service.WeatherService;

/**
 * Date: 05.02.17
 * Time: 16:38
 *
 * @author Ivan Kerer
 */

public class StartupReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        WeatherService.setServiceAlarm(context, true);
    }
}
