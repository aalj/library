package com.bestsnail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsnail.R;
import com.bestsnail.bean.BorrowTable;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BorrowHistroyInfoActivity extends AppCompatActivity {

    @Bind(R.id.book_ima)
    ImageView _mBookIma;
    @Bind(R.id.book_name)
    TextView _mBookName;
    @Bind(R.id.zuozhe)
    TextView _mZuozhe;

    @Bind(R.id.chubanshe)
    TextView _mChubanshe;
    @Bind(R.id.chubanshijian)
    TextView _mChubanshijian;
    @Bind(R.id.isbn)
    TextView _mIsbn;
    @Bind(R.id.guancangshu)
    TextView _mGuancangshu;


    BorrowTable falg = null;
    @Bind(R.id.toolbar)
    Toolbar _mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_histroy_info);
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

        falg =
                (BorrowTable) getIntent().getSerializableExtra("falg");
        initView();
    }

    private void initView() {
        SimpleDateFormat
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

//        ImageView _mBookIma;
        _mBookName.setText(falg.getBook().getBook_name());
        _mZuozhe.setText(falg.getBook().getBook_author());

        _mChubanshe.setText(simpleDateFormat.format(falg.getBorrow_time()));
        _mChubanshijian.setText(simpleDateFormat.format(falg.getRemand_time()));
        _mIsbn.setText(falg.getBook().getBook_isbn() + "");
        _mGuancangshu.setText(falg.getBook().getLlLibraryAddressTable().getLc_address());


    }

    @OnClick(R.id.book_name)
    public void onClick() {
        Intent intetn = new Intent(this, BookInfoActivity.class);
        intetn.putExtra("data", falg.getBook());
        startActivity(intetn);
        finish();


    }


}
