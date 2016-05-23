package com.bestsnail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.adapter.BaseRecyclerAdapter;
import com.bestsnail.adapter.RecyclerViewHolder;
import com.bestsnail.bean.RecommendedBookTable;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookRecommendLiShiActivity extends AppCompatActivity {

    @Bind(R.id.title_actionbar)
    TextView _mTitleActionbar;
    @Bind(R.id.toolbar)
    Toolbar _mToolbar;
    @Bind(R.id.vp_FindFragment_pager)
    RecyclerView _mVpFindFragmentPager;


    BaseRecyclerAdapter<RecommendedBookTable> recyclerAdapter = null;
    List<RecommendedBookTable> value = new ArrayList<>();
    List<RecommendedBookTable> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guan_cang);
        ButterKnife.bind(this);
        _mTitleActionbar.setText("推荐历史");
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


    Handler _mHandler
            = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<RecommendedBookTable> obj = (List<RecommendedBookTable>) msg.obj;
            for (RecommendedBookTable i : obj) {
                recyclerAdapter.add(0, i);

            }

        }
    };


    /**
     * 加载页面。
     */
    private void initView() {
        getValue();
        List<RecommendedBookTable> value = new ArrayList<>();
        _mVpFindFragmentPager.setItemAnimator(new DefaultItemAnimator());
        _mVpFindFragmentPager.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new BaseRecyclerAdapter<RecommendedBookTable>(BookRecommendLiShiActivity.this, value) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.lectures_cardview;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, RecommendedBookTable item) {
                holder.setText(R.id.tv_num, item.getRebo_book_name());
                holder.setText(R.id.author, item.getRebo_anthor());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                holder.setText(R.id.time, simpleDateFormat.format(item.getCreate_time()));
            }
        };
        if (recyclerAdapter != null) {
            _mVpFindFragmentPager.setAdapter(recyclerAdapter);

        }
        recyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Intent intent = new Intent(BookRecommendLiShiActivity.this,BookRecommedInfoActivity.class);
                intent.putExtra("info",list.get(pos));
                startActivity(intent);
                Toast.makeText(BookRecommendLiShiActivity.this, "点击item", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getValue() {

        HttpUtils httpUtils = new HttpUtils();
        String url = GetHttp.getHttpConnetion() + "BookRecommendLiShiServlet";
        RequestParams pre = new RequestParams();
        pre.addBodyParameter("stu_id", ((Library) getApplication()).getStudent().getStu_id() + "");


        httpUtils.send( HttpMethod.POST, url, pre, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String book = responseInfo.result;
                Log.i("tag",book);
                Gson gson = GetGson.mGetGson();
                Type type
                        = new TypeToken<List<RecommendedBookTable>>() {
                }.getType();
                  list = gson.fromJson(book, type);
                Message message = Message.obtain();
                message.obj = list;
                _mHandler.sendMessage(message);

            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });


    }
}
