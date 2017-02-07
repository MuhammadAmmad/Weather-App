package com.kerer.weatherapp.util;

import android.content.Context;
import android.net.ConnectivityManager;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Date: 05.02.17
 * Time: 20:44
 *
 * @author Ivan Kerer
 */

public class NetworkUtil {
    private Context mContext;

    public NetworkUtil(Context context){
        this.mContext = context;
    }

    /**
     * Checking internet connection
     * @return internet connection status
     */
    public boolean isNetworkAvailableAndConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(CONNECTIVITY_SERVICE);
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable &&
                cm.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }

}
