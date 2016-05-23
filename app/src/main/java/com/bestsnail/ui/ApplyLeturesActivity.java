package com.bestsnail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.adapter.BaseRecyclerAdapter;
import com.bestsnail.adapter.BorrowHistroyLoadMoreAdapter;
import com.bestsnail.adapter.FootLoadScrollListener;
import com.bestsnail.adapter.LecturesRegisMoreAdapter;
import com.bestsnail.adapter.LecturesRegisMoreAdapter;
import com.bestsnail.bean.LecturesRegistrationTable;
import com.bestsnail.bean.LecturesTable;
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

public class ApplyLeturesActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar _mToolbar;
    @Bind(R.id.recyclerView)
    RecyclerView _mRecyclerView;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout _mSwipeRefresh;

    List<LecturesRegistrationTable> mlist = new ArrayList<>();
    List<LecturesRegistrationTable> listOld = new ArrayList<>();
    private int page = 1;
    private BaseRecyclerAdapter mAdapter;

    int stu_id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);
        ButterKnife.bind(this);
          stu_id = ((Library) getApplication()).getStudent().getStu_id();
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
        mAdapter = new LecturesRegisMoreAdapter(ApplyLeturesActivity.this, mlist);

        _mRecyclerView.setLayoutManager(new LinearLayoutManager(ApplyLeturesActivity.this));
        _mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Intent intent = new Intent(ApplyLeturesActivity.this, LecturesInfoActivity.class);
                intent.putExtra("info", mlist.get(pos).getLecturesTable());
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
                    ((LecturesRegisMoreAdapter) mAdapter).setFooterStatus(LecturesRegisMoreAdapter.LOADING);//更新正在拼命加载文字
                    HttpUtils httpUtils = new HttpUtils();
                    String url = GetHttp.getHttpConnetion() + "GetLecRegistrationListservlet";
                    RequestParams pre = new RequestParams();
                    pre.addBodyParameter("stu_id",  stu_id + "");
                    pre.addBodyParameter("page",  page+"");
                    httpUtils.send( HttpMethod.POST, url, pre, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            String result = responseInfo.result;
                            if ("no".equals(result)) {
                                ((LecturesRegisMoreAdapter) mAdapter).setFooterStatus(LecturesRegisMoreAdapter.NO_MORE);//更新文字没有更多了
                            } else {
                                Type type = new TypeToken<List<LecturesRegistrationTable>>() {
                                }.getType();
                                Gson gson = GetGson.mGetGson();
                                List<LecturesRegistrationTable> list = gson.fromJson(String.valueOf(result), type);
                                    Log.i("tag", "onSuccess: ----"+list.size());
                                if(list.size()<6){
                                    Log.i("tag", "onSuccess: 是否执行");
                                    ((LecturesRegisMoreAdapter) mAdapter).setFooterStatus(LecturesRegisMoreAdapter.NOMORE);//更新文字没有更多了
//                                    ((LecturesRegisMoreAdapter) mAdapter).setFooterStatus(LecturesRegisMoreAdapter.NOMORE);
                                }
                                for (LecturesRegistrationTable i :
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
                String url = GetHttp.getHttpConnetion() + "GetLecRegistrationListservlet";
                RequestParams pre = new RequestParams();
                pre.addBodyParameter("stu_id", stu_id+"");
                pre.addBodyParameter("page",  "1");
                httpUtils.send( HttpMethod.POST, url, pre, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;

                        Type type = new TypeToken<List<LecturesRegistrationTable>>() {
                        }.getType();
                        Gson gson = GetGson.mGetGson();
                        List<LecturesRegistrationTable> list = gson.fromJson(String.valueOf(result), type);

                        for (int i = 0; i < listOld.size(); i++) {
                            if (!listOld.contains(list.get(i))) {


                                mAdapter.add(0, list.get(i));
//                                mlist.add(0, list.get(i));

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
        String url = GetHttp.getHttpConnetion() + "GetLecRegistrationListservlet";
        RequestParams pre = new RequestParams();
        pre.addBodyParameter("stu_id", stu_id+"");
        pre.addBodyParameter("page",  "1");
        httpUtils.send( HttpMethod.POST, url, pre, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;

                Type type = new TypeToken<List<LecturesRegistrationTable>>() {
                }.getType();
                Gson gson = GetGson.mGetGson();
                List<LecturesRegistrationTable> list = gson.fromJson(String.valueOf(result), type);
                listOld = list;
                if(list.size()<6){
                    Log.i("tag", "onSuccess: 是否执行");
                    ((LecturesRegisMoreAdapter) mAdapter).setFooterStatus(LecturesRegisMoreAdapter.NOMORE);//更新文字没有更多了
//                                    ((LecturesRegisMoreAdapter) mAdapter).setFooterStatus(LecturesRegisMoreAdapter.NOMORE);
                }
                for (LecturesRegistrationTable i :
                        list) {
                    mAdapter.add(0, i);


                }
                //滑倒指定的位置
                _mRecyclerView.smoothScrollToPosition(0);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Toast.makeText(ApplyLeturesActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();

            }

        });
    }
}
