package com.bestsnail.recyclerviewrefesh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bestsnail.adapter.BaseRecyclerAdapter;
import com.bestsnail.adapter.FooterLoadMoreAdapter;
import com.bestsnail.adapter.GetHttp;
import com.bestsnail.bean.BookTable;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    BaseRecyclerAdapter<BookTable> tBaseRecyclerAdapter = null;
    @Bind(R.id.recyclerView)
    RecyclerView _mRecyclerView;
    List<BookTable> lisshujut = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        _mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        _mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        initView();
    }

    private void initView() {

//        tBaseRecyclerAdapter = new BaseRecyclerAdapter<BookTable>(this, lisshujut) {
//            @Override
//            protected int getItemLayoutId(int viewType) {
//                return R.layout.heard;
//            }
//
//            @Override
//            protected void bindData(RecyclerViewHolder holder, int position, BookTable item) {
//
//            }
//        };
//        _mRecyc.setAdapter(tBaseRecyclerAdapter);
        getGuanCangReMenBook();
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
                lisshujut = gson.fromJson(responseInfo.result, type);
                tBaseRecyclerAdapter = new FooterLoadMoreAdapter(MainActivity.this, lisshujut);
                _mRecyclerView.setAdapter(tBaseRecyclerAdapter);


            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });


    }

    @OnClick(R.id.recyclerView)
    public void onClick() {
    }
}
