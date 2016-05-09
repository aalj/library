package com.bestsnail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsnail.R;
import com.bestsnail.bean.BookTable;
import com.lidroid.xutils.BitmapUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookInfoActivity extends AppCompatActivity {


    @Bind(R.id.book_name)
    TextView _mBookName;
    @Bind(R.id.book_address_list)
    TextView _mBookAddressList;

    @Bind(R.id.zuozhe)
    TextView _mZuozhe;
    @Bind(R.id.zhiti)
    TextView _mZhiti;
    @Bind(R.id.chubanshe)
    TextView _mChubanshe;
    @Bind(R.id.chubanshijian)
    TextView _mChubanshijian;
    @Bind(R.id.isbn)
    TextView _mIsbn;
    @Bind(R.id.guancangshu)
    TextView _mGuancangshu;
    @Bind(R.id.guacangdi)
    TextView _mGuacangdi;
    @Bind(R.id.kejie)
    TextView _mKejie;
    @Bind(R.id.book_ima)
    ImageView _mBookIma;
    @Bind(R.id.suoshuhao)
    TextView _mSuoshuhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
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
                Intent intetn = new Intent(BookInfoActivity.this, MainActivity.class);
                startActivity(intetn);
                finish();

                return true;
            }
        });
        initData();
    }

    private void initData() {
        BookTable book = (BookTable) getIntent().getSerializableExtra("data");


        BitmapUtils bitmapUtils = new BitmapUtils(this);
        String book_image = book.getBook_image();
        if (book_image != null) {

            bitmapUtils.display(_mBookIma, book.getBook_image());
        }


        _mBookName.setText(book.getBook_name());
        _mZuozhe.setText(book.getBook_author());
        _mZhiti.setText(book.getBook_keywords());

        _mChubanshe.setText(book.getBook_publishing_house());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Date book_publishing_house_time =
                book.getBook_publishing_house_time();
        if (book_publishing_house_time != null) {

            _mChubanshijian.setText(simpleDateFormat.format(book.getBook_publishing_house_time()));
        } else {
            _mChubanshijian.setText("出版时间不详");

        }

        _mIsbn.setText(book.getBook_isbn() + "");
        _mSuoshuhao.setText(book.getBook_category_number());
        int book_numshu = book.getBook_num();
        _mGuancangshu.setText(book_numshu + "");

        if (book_numshu - book.getBook_borrow_num() > 0) {
            _mKejie.setText("图书可借");
        } else {
            _mKejie.setText("当前图书已经全部借出。");

        }
        _mKejie.setTextColor(0xffff0000);
        _mGuacangdi.setText(book.getLlLibraryAddressTable().getLc_name());
        String book_introduction = book.getBook_introduction();
        if (book_introduction != null && book_introduction.length() > 0) {
            _mBookAddressList.setText(book_introduction);

        } else {
            _mBookAddressList.setText("暂无书籍介绍");

        }


    }
}
