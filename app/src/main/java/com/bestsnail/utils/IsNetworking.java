package com.bestsnail.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 作者：liang on 2016/5/1 11:36
 */
public class IsNetworking {
    public static boolean isNet(Context v) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) v.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return !(networkInfo == null || !networkInfo.isAvailable());
    }

}
