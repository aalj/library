package com.bestsnail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bestsnail.R;
import com.bestsnail.adapter.BaseRecyclerAdapter;
import com.bestsnail.adapter.GuanCangHeadAdapter;
import com.bestsnail.bean.BookTable;
import com.bestsnail.presenter.GuanCangBookPL;
import com.bestsnail.presenter.IRemenTuShuPresenter;
import com.bestsnail.view.GuanCangBookView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GuanCangActivity extends AppCompatActivity implements GuanCangBookView {

    //    TextView _mTitleActionbar;
//    Toolbar _mToolbar;
//    ImageView _mImageView2;
//    TextView _mAllTv;
//
//    TextView _mZuozhTv;
//    TextView _mZhuticiTv;
//    EditText _mEdGuanjianzi;
//    Button _mSousuoBut;
//    TextView _mTabFindFragmentTitle;
//
//
//    Spinner _mSpinner;


    @Bind(R.id.vp_FindFragment_pager)
    RecyclerView _mVpFindFragmentPager;
    BaseRecyclerAdapter<BookTable> adapter = null;
    private List<TextView> lv_textView; //tab名称列表
    //用于标记当前使用那种搜索方式
    private int flag = 0;
    //标记去那个馆藏查书
    private int guancangflag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guan_cang);
        ButterKnife.bind(this);
        IRemenTuShuPresenter guanCangBookPL = new GuanCangBookPL(this);
        guanCangBookPL.loadData();
        initView();


    }

    private void initView() {
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
                Intent intetn = new Intent(GuanCangActivity.this, MainActivity.class);
                startActivity(intetn);
                finish();

                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public void showReMenBook(final List<BookTable> list) {

        adapter = new GuanCangHeadAdapter(this, list);

        LinearLayoutManager layout = new LinearLayoutManager(this);

        _mVpFindFragmentPager.setLayoutManager(layout);
        _mVpFindFragmentPager.setItemAnimator(new DefaultItemAnimator());

        //点击热门图书到达图书详情页面
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                Log.i("tag", "onItemClick: pos==============" + pos);
                Intent intent = new Intent(GuanCangActivity.this, BookInfoActivity.class);
                intent.putExtra("data", list.get(pos + 1));
                startActivity(intent);


            }
        });
        _mVpFindFragmentPager.setAdapter(adapter);

    }

//    int tempOld = -1;


}
