package com.bestsnail.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bestsnail.R;
import com.bestsnail.bean.RecommendedBookTable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookRecommedInfoActivity extends AppCompatActivity {

    @Bind(R.id.title_actionbar)
    TextView _mTitleActionbar;
    @Bind(R.id.toolbar)
    Toolbar _mToolbar;


    @Bind(R.id.zhiti)
    TextView _mZhiti;
    @Bind(R.id.chubanshe)
    TextView _mChubanshe;
    @Bind(R.id.isbn)
    TextView _mIsbn;
    @Bind(R.id.book_name)
    TextView _mBookName;

    RecommendedBookTable info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_recommed_info);
        ButterKnife.bind(this);
        setSupportActionBar(_mToolbar);
        _mTitleActionbar.setText("推荐图书详情");
        //加载右面的突变
        _mToolbar.setNavigationIcon(R.mipmap.back);
        info = (RecommendedBookTable) getIntent().getSerializableExtra("info");
        //设置右面的点击事件
        _mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        inintView();

    }

    private void inintView() {
        _mZhiti
                .setText(info.getRebo_anthor());
        _mBookName
                .setText(info.getRebo_book_name());

        _mChubanshe.setText(info.getRebo_press());

        _mIsbn.setText(info.getRebo_isbn());


    }
}
