package com.bestsnail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.adapter.BaseRecyclerAdapter;
import com.bestsnail.adapter.FootLoadScrollListener;
import com.bestsnail.adapter.NewsLoadMoreAdapter;
import com.bestsnail.bean.NewsTable;
import com.bestsnail.utils.GetGson;
import com.bestsnail.utils.GetHttp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity {

    public static final int FLAG = 1;

    @Bind(R.id.title_actionbar)
    TextView _mTitleActionbar;
    @Bind(R.id.toolbar)
    Toolbar _mToolbar;
    @Bind(R.id.recyclerView)
    RecyclerView _mRecyclerView;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout _mSwipeRefresh;
    List<NewsTable> mlist = new ArrayList<>();
    List<NewsTable> listOld = new ArrayList<>();
    private int page = 1;
    private BaseRecyclerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_yuan);
        ButterKnife.bind(this);

        setSupportActionBar(_mToolbar);
        //加载右面的突变
        _mToolbar.setNavigationIcon(R.mipmap.back);
        //设置右面的点击事件
        _mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        initView();
    }

    //页面加载初始化
    private void initView() {
        //下拉刷新的旋转的颜色变化
        _mSwipeRefresh.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        _mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //调用适配器加载资源
        mAdapter = new NewsLoadMoreAdapter(NewsActivity.this, mlist);

        _mRecyclerView.setLayoutManager(new LinearLayoutManager(NewsActivity.this));
        _mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Intent intent = new Intent(NewsActivity.this, NRInfoActivity.class);
                intent.putExtra("info", mlist.get(pos));
                intent.putExtra("flag", FLAG);
                startActivity(intent);


            }
        });


        firstLoad();


        //上拉加载
        _mRecyclerView.addOnScrollListener(new FootLoadScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isScrollEnd(recyclerView, newState)) {
                    page++;
                    ((NewsLoadMoreAdapter) mAdapter).setFooterStatus(NewsLoadMoreAdapter.LOADING);//更新正在拼命加载文字
                    HttpUtils httpUtils = new HttpUtils();
                    String url = GetHttp.getHttpConnetion() + "GetNews";
                    RequestParams pre = new RequestParams();
                    pre.addBodyParameter("page", page + "");
                    httpUtils.send(HttpMethod.POST, url, pre, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            String result = responseInfo.result;
                            if ("[]".equals(result)) {
                                ((NewsLoadMoreAdapter) mAdapter).setFooterStatus(NewsLoadMoreAdapter.NO_MORE);//更新文字没有更多了
                            } else {
                                Type type = new TypeToken<List<NewsTable>>() {
                                }.getType();
                                Gson gson = GetGson.mGetGson();
                                List<NewsTable> list = gson.fromJson(String.valueOf(result), type);
                                for (NewsTable i :
                                        list) {
                                    mAdapter.add(mAdapter.getItemCount() - 1, i);
                                    mlist.add(mAdapter.getItemCount() - 1, i);
                                }
                            }
                        }

                        @Override
                        public void onFailure(HttpException error, String msg) {

                        }
                    });

                }
            }
        });


        //下拉刷新
        _mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HttpUtils httpUtils = new HttpUtils();
                String url = GetHttp.getHttpConnetion() + "GetNews";
                RequestParams pre = new RequestParams();
                pre.addBodyParameter("page", "1");
                httpUtils.send(HttpMethod.POST, url, pre, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;

                        Type type = new TypeToken<List<NewsTable>>() {
                        }.getType();
                        Gson gson = GetGson.mGetGson();
                        List<NewsTable> list = gson.fromJson(String.valueOf(result), type);

                        for (int i = 0; i < listOld.size(); i++) {
                            if (!listOld.contains(list.get(i))) {


                                mAdapter.add(0, list.get(i));
                                mlist.add(0, list.get(i));

                            }

                        }
                        _mSwipeRefresh.setRefreshing(false);
//                        _mSwipeRefresh.setRefreshing(true);
                        //滑倒指定的位置
                        _mRecyclerView.smoothScrollToPosition(0);

                        listOld = list;
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                    }

                });
            }
        });
    }


    public void firstLoad() {
        HttpUtils httpUtils = new HttpUtils();
        String url = GetHttp.getHttpConnetion() + "GetNews";
        RequestParams pre = new RequestParams();
        pre.addBodyParameter("page", "1");
        httpUtils.send(HttpMethod.POST, url, pre, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;

                Type type = new TypeToken<List<NewsTable>>() {
                }.getType();
                Gson gson = GetGson.mGetGson();
                List<NewsTable> list = gson.fromJson(String.valueOf(result), type);
                listOld = list;
                for (NewsTable i :
                        list) {
                    mAdapter.add(0, i);


                }
                //滑倒指定的位置
                _mRecyclerView.smoothScrollToPosition(0);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Toast.makeText(NewsActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();

            }

        });
    }

}
