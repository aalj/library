package com.bestsnail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.bean.BookTable;
import com.bestsnail.bean.Student;
import com.bestsnail.utils.GetHttp;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookInfoActivity extends AppCompatActivity {


    public static final String CEOLLECTBOOK = "CeollectBook";
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


    Student student = null;
    BookTable book = null;
    @Bind(R.id.toolbar)
    Toolbar _mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
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
        //设置toolsbar上面左面的点击事件
        _mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                baoMing(item);
                return true;
            }
        });


        initData();
    }

    private void initData() {
        book = (BookTable) getIntent().getSerializableExtra("data");


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3, menu);

        return true;
    }


    private void baoMing(final MenuItem item) {

//TODO
        student = ((Library) getApplication()).getStudent();

        Log.d("tag", "student.getStu_id():" + student.getStu_id());
        if (student != null && student.getStu_id() > 0) {

            HttpUtils httpUtils = new HttpUtils();
            String url = GetHttp.getHttpConnetion() + "SetCeollectBook";
            RequestParams pre = new RequestParams();
            pre.addBodyParameter("stu_id", student.getStu_id() + "");
            pre.addBodyParameter("book_id", book.getBook_id() + "");
            httpUtils.send( HttpMethod.POST, url, pre, new RequestCallBack<String>() {


                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String result = responseInfo.result;
                    Toast.makeText(BookInfoActivity.this, result, Toast.LENGTH_SHORT).show();
                    item.setTitle("已收藏");
                    _mToolbar.setClickable(false);

                }

                @Override
                public void onFailure(HttpException error, String msg) {
                    Toast.makeText(BookInfoActivity.this, "网络故障", Toast.LENGTH_SHORT).show();
                }

            });


        } else {
            //跳转到登陆页面，进行登陆
            Intent intent = new Intent(BookInfoActivity.this, LoginActivity.class);
            intent.putExtra("flags", CEOLLECTBOOK);
            startActivityForResult(intent, 2);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //进行报名
            //访问网络处理
            Toast.makeText(this, "登陆成功请继续操作", Toast.LENGTH_SHORT).show();

        }
    }
}