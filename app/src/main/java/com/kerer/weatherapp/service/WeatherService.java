package com.kerer.weatherapp.service;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import com.kerer.weatherapp.App;
import com.kerer.weatherapp.mvp.presenter.WeatherServicePresenter;
import com.kerer.weatherapp.mvp.view.WeatherServiceView;
import com.kerer.weatherapp.ui.CitiesListActivity;

import javax.inject.Inject;

/**
 * Date: 05.02.17
 * Time: 12:26
 *
 * @author Ivan Kerer
 */

public class WeatherService extends IntentService implements WeatherServiceView {
    private static final String TAG = "WeatherService";
    private static final int POLL_INTERVAL = 1000 * 60 * 60; // 60 секунд

    @Inject
    public WeatherServicePresenter mPresenter;

    public static Intent newIntent(Context context) {
        return new Intent(context, WeatherService.class);
    }

    public WeatherService() {
        super(TAG);
        App.getAppComponent().injectWeatherService(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //DO request
        Log.d(TAG, "onHandleIntent");
        mPresenter.loadData();
    }

    /**
     * starting service repeating
     * @param context - some context
     * @param isStart - starting service
     */
    public static void setServiceAlarm(Context context, boolean isStart) {
        if (isStart){
            return;
        }
        Intent i = WeatherService.newIntent(context);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, i, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), POLL_INTERVAL, pendingIntent);
    }

    /**
     * Show notification with updated info
     */
    @Override
    public void showUpdates(String title, String subTitle) {

        Intent intent = new Intent(this, CitiesListActivity.class);
        PendingIntent pebdingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle(title)
                .setSubText(subTitle)
                .setSmallIcon(android.R.drawable.editbox_dropdown_dark_frame)
                .setContentIntent(pebdingIntent).setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(101, notification);
    }
}
