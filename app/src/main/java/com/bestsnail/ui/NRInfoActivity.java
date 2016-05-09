package com.bestsnail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bestsnail.R;
import com.bestsnail.bean.NewsTable;
import com.bestsnail.bean.ResourceDynamicsTable;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NRInfoActivity extends AppCompatActivity {

    @Bind(R.id.title_tv)
    TextView _mTitleTv;
    @Bind(R.id.info_tv)
    TextView _mInfoTv;
    @Bind(R.id.author)
    TextView _mAuthor;
    @Bind(R.id.time_tv)
    TextView _mTimeTv;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nrinfo);
        ButterKnife.bind(this);
        flag = getIntent().getIntExtra("flag", 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //加载右面的突变
        toolbar.setNavigationIcon(R.mipmap.back);
        //设置右面的点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置toolsbar上面左面的点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intetn = null;
                if (flag == NewsActivity.FLAG) {
                    intetn = new Intent(NRInfoActivity.this, NewsActivity.class);
                } else if (flag == ZiYuanActivity.FLAG) {
                    intetn = new Intent(NRInfoActivity.this, ZiYuanActivity.class);

                }


                startActivity(intetn);
                finish();

                return true;
            }
        });


        if (flag == NewsActivity.FLAG) {
            NewsTable news = (NewsTable) getIntent().getSerializableExtra("info");
            initViewNew(news);
        } else {
            ResourceDynamicsTable resourceDynamicsTable = (ResourceDynamicsTable) getIntent().getSerializableExtra("info");
            initViewRes(resourceDynamicsTable);
        }


    }

    //加载新闻
    public void initViewNew(NewsTable news) {
        _mTitleTv.setText(news.getNews_title());

        _mInfoTv.setText(news.getNews_info());
        _mAuthor.setText(news.getNews_author());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        _mTimeTv.setText(simpleDateFormat.format(news.getNews_time()));


    }
    //加载资源动态

    public void initViewRes(ResourceDynamicsTable resourceDynamicsTable) {
        _mTitleTv.setText(resourceDynamicsTable.getRdt_title());

        _mInfoTv.setText(resourceDynamicsTable.getRdt_info());
        _mAuthor.setText(resourceDynamicsTable.getRdt_author());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        _mTimeTv.setText(simpleDateFormat.format(resourceDynamicsTable.getRdt_time()));
    }


}
