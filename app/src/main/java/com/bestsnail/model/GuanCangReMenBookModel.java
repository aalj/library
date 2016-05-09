package com.bestsnail.model;

import android.util.Log;

import com.bestsnail.bean.BookTable;
import com.bestsnail.presenter.GuanCangBookPL;
import com.bestsnail.utils.GetHttp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 作者：liang on 2016/5/4 16:04
 */
public class GuanCangReMenBookModel {
    private GuanCangBookPL _mGuanCangBookPL = null;

    public GuanCangReMenBookModel(GuanCangBookPL guanCangBookPL) {
        this._mGuanCangBookPL = guanCangBookPL;
    }

    /**
     * 通过盖房能够得到馆藏里面的热门图书
     */
    public void getGuanCangReMenBook() {

        HttpUtils httpUtils = new HttpUtils();
        String url = GetHttp.getHttpConnetion() + "SearchRemenBook";
        httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("tag", "RementushuFragment------->>>>>>" + responseInfo.result);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                Type type = new TypeToken<List<BookTable>>() {
                }.getType();
                List<BookTable> lisshujut = gson.fromJson(responseInfo.result, type);
                _mGuanCangBookPL.loadDataSuccess(lisshujut);

            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });

    }
}
