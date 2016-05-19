package com.bestsnail.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.adapter.BaseRecyclerAdapter;
import com.bestsnail.adapter.BorrowBookLoadMoreAdapter;
import com.bestsnail.adapter.FootLoadScrollListener;
import com.bestsnail.bean.BorrowTable;
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

public class BorrowBookActivityo extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar _mToolbar;
    @Bind(R.id.recyclerView)
    RecyclerView _mRecyclerView;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout _mSwipeRefresh;


    List<BorrowTable> borrowTables = new ArrayList<>();
    List<BorrowTable> mlist = new ArrayList<>();
    List<BorrowTable> listOld = new ArrayList<>();
    private int page = 1;
    BaseRecyclerAdapter madapter;
    int stu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book_activityo);
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
    }
    @Override
    protected void onResume() {
        stu_id = ((Library) (getApplication())).getStudent().getStu_id();
        if (stu_id > 0) {
            initView();
        } else {
            Intent
                    intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        super.onResume();
    }


    private void initView() {
        //下拉刷新的旋转的颜色变化
        _mSwipeRefresh.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);


        //网络加载数据


        madapter = new BorrowBookLoadMoreAdapter(this, borrowTables);
        _mRecyclerView.setAdapter(madapter);
        _mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        _mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        _mRecyclerView.setAdapter(madapter);
        //每一个Item的点击事件
        madapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Intent  intent = new Intent(BorrowBookActivityo.this,BorrowHistroyInfoActivity.class);
                intent.putExtra("falg",mlist.get(pos));
                startActivity(intent);
                finish();
                //TODO 每个item的点击时间
                Toast.makeText(BorrowBookActivityo.this, "pos:" + pos, Toast.LENGTH_SHORT).show();
            }
        });

        fistLoad();

        //上拉加载
        _mRecyclerView.addOnScrollListener(
                new FootLoadScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (isScrollEnd(recyclerView, newState)) {
                            page++;
                            ((BorrowBookLoadMoreAdapter) madapter).setFooterStatus(BorrowBookLoadMoreAdapter.LOADING);//更新正在拼命加载文字
                            HttpUtils httpUtils = new HttpUtils();
                            String url = GetHttp.getHttpConnetion() + "BorrowingBookServlet";
                            RequestParams pre = new RequestParams();
                            pre.addBodyParameter("page", page + "");
                            pre.addBodyParameter("stu_id",stu_id+"");
                            httpUtils.send( HttpMethod.POST, url, pre, new RequestCallBack<String>() {
                                @Override
                                public void onSuccess(ResponseInfo<String> responseInfo) {
                                    String result = responseInfo.result;
                                    if ("no".equals(result)) {
                                        ((BorrowBookLoadMoreAdapter) madapter).setFooterStatus(BorrowBookLoadMoreAdapter.NO_MORE);//更新文字没有更多了
                                    } else {
                                        Type type = new TypeToken<List<BorrowTable>>() {
                                        }.getType();
                                        Gson gson = GetGson.mGetGson();
                                        List<BorrowTable> list = gson.fromJson(String.valueOf(result), type);
                                        if(list.size()<5){
                                            ((BorrowBookLoadMoreAdapter) madapter).setFooterStatus(BorrowBookLoadMoreAdapter.NOMORE);
                                        }
                                        for (BorrowTable i :
                                                list) {
                                            madapter.add(madapter.getItemCount() - 1, i);
                                            mlist.add(madapter.getItemCount() - 1, i);
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(HttpException error, String msg) {
                                }
                            });
                        } else {
                            Toast.makeText(BorrowBookActivityo.this, "没有更多数据", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


//下拉刷新
        _mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                HttpUtils httpUtils = new HttpUtils();
                String url = GetHttp.getHttpConnetion() + "BorrowingBookServlet";
                RequestParams pre = new RequestParams();
                pre.addBodyParameter("page", "1");
                pre.addBodyParameter("stu_id", stu_id + "");
                httpUtils.send( HttpMethod.POST, url, pre, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;
                        Type type = new TypeToken<List<BorrowTable>>() {
                        }.getType();
                        Gson gson = GetGson.mGetGson();
                        List<BorrowTable> list = gson.fromJson(String.valueOf(result), type);
                        if(list.size()<5){
                            ((BorrowBookLoadMoreAdapter) madapter).setFooterStatus(BorrowBookLoadMoreAdapter.NOMORE);
                        }
                        for (int i = 0; i < listOld.size(); i++) {
                            if (!listOld.contains(list.get(i))) {
                                madapter.add(0, list.get(i));
                                mlist.add(0, list.get(i));
                            }
                        }
                        _mSwipeRefresh.setRefreshing(false);
                        //滑倒指定的位置
                        _mRecyclerView.smoothScrollToPosition(0);

                        listOld = list;
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        Toast.makeText(BorrowBookActivityo.this, "请检查网络", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 第一次进入页面加载数据
     */
    private void fistLoad() {
        HttpUtils httpUtils = new HttpUtils();
        String url = GetHttp.getHttpConnetion() + "BorrowingBookServlet";
        RequestParams pre = new RequestParams();
        pre.addBodyParameter("page", "1");
        pre.addBodyParameter("stu_id", stu_id + "");
        httpUtils.send( HttpMethod.POST, url, pre, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;

                Type type = new TypeToken<List<BorrowTable>>() {
                }.getType();
                Gson gson = GetGson.mGetGson();
                List<BorrowTable> list = gson.fromJson(String.valueOf(result), type);
                if(list.size()<5){
                    ((BorrowBookLoadMoreAdapter) madapter).setFooterStatus(BorrowBookLoadMoreAdapter.NOMORE);
                }
                mlist=list;
                listOld = list;
                for (BorrowTable i :
                        list) {
                    madapter.add(0, i);


                }
                //滑倒指定的位置
                _mRecyclerView.smoothScrollToPosition(0);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Toast.makeText(BorrowBookActivityo.this, "请检查网络", Toast.LENGTH_SHORT).show();

            }

        });
    }
}
