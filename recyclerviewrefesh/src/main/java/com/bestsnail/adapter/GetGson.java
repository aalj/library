package com.bestsnail.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 作者：liang on 2016/5/5 16:34
 */
public class GetGson {

    public static Gson mGetGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }
}
