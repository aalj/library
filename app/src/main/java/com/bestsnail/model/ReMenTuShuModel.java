package com.bestsnail.model;

import android.util.Log;

import com.bestsnail.bean.BookTable;
import com.bestsnail.presenter.IRemenTuShuPresenter;
import com.bestsnail.utils.GetHttp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 作者：liang on 2016/4/30 19:44
 */
public class ReMenTuShuModel {

    IRemenTuShuPresenter iRemenTuShuPresenter = null;

    public ReMenTuShuModel(IRemenTuShuPresenter iRemenTuShuPresenter) {
        this.iRemenTuShuPresenter = iRemenTuShuPresenter;
    }


    public void getRemenTushu() {

        //实现访问网络的到数据
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        String url = GetHttp.getHttpConnetion() + "SendReMenSousuo";
        Log.i("tag", url);

        httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("TAG", responseInfo.result);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                Type type = new TypeToken<List<BookTable>>() {
                }.getType();
                List<BookTable> list = gson.fromJson(responseInfo.result, type);
                iRemenTuShuPresenter.loadDataSuccess(list);


            }

            @Override
            public void onFailure(HttpException error, String msg) {
                iRemenTuShuPresenter.loadDataFailure();
            }
        });


    }
}
