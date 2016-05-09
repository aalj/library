package com.bestsnail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bestsnail.R;
import com.bestsnail.adapter.BaseRecyclerAdapter;
import com.bestsnail.adapter.RecyclerViewHolder;
import com.bestsnail.bean.BookTable;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity {

    @Bind(R.id.title_actionbar)
    TextView _mTitleActionbar;
    @Bind(R.id.toolbar)
    Toolbar _mToolbar;
    @Bind(R.id.listView)
    RecyclerView _mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
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
                Intent intetn = new Intent(SearchResultActivity.this, MainActivity.class);
                startActivity(intetn);
                finish();

                return true;
            }
        });

        initData();
    }

    private void initData() {

        final List<BookTable> list = (List<BookTable>) getIntent().getSerializableExtra("data");
        _mListView.setLayoutManager(new LinearLayoutManager(this));
        _mListView.setItemAnimator(new DefaultItemAnimator());
        BaseRecyclerAdapter<BookTable> adapter = new BaseRecyclerAdapter<BookTable>(this, list) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.fragment_item;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, BookTable item) {
                holder.setText(R.id.id, item.getBook_name());
                holder.setText(R.id.zuozhe, item.getBook_author());
                holder.setText(R.id.content, item.getBook_isbn());

            }

        };
        _mListView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Intent intent = new Intent(SearchResultActivity.this, BookInfoActivity.class);
                intent.putExtra("data", list.get(pos));
                startActivity(intent);

            }
        });


    }


}
