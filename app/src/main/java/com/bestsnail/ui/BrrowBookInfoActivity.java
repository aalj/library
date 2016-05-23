package com.bestsnail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.bean.BorrowTable;
import com.bestsnail.bean.NewsTable;
import com.bestsnail.utils.DateUtils;
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
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrrowBookInfoActivity extends AppCompatActivity {
    BorrowTable falg = null;
    @Bind(R.id.book_ima)
    ImageView _mBookIma;

    @Bind(R.id.zuozhe)
    TextView _mZuozhe;
    @Bind(R.id.chubanshe)
    TextView _mChubanshe;
    @Bind(R.id.isbn)
    TextView _mIsbn;
    @Bind(R.id.guancangshu)
    TextView _mGuancangshu;
    @Bind(R.id.chubanshijian)
    TextView _mChubanshijian;
    @Bind(R.id.book_name)
    TextView _mBookName;
    @Bind(R.id.toolbar)
    Toolbar _mToolbar;
    @Bind(R.id.huanshustianshu)
    TextView _mHuanshustianshu;
    @Bind(R.id.fab)
    FloatingActionButton _mFab;

    int betweenDay = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_book_activity);


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


        falg = (BorrowTable) getIntent().getSerializableExtra("falg");
        initView();
    }

    private void initView() {
        SimpleDateFormat
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

//        ImageView _mBookIma;
        _mBookName.setText(falg.getBook().getBook_name());
        _mZuozhe.setText(falg.getBook().getBook_author());

        _mChubanshe.setText(simpleDateFormat.format(falg.getBorrow_time()));

        _mIsbn.setText(falg.getBook().getBook_isbn() + "");
        _mGuancangshu.setText(falg.getBook().getLlLibraryAddressTable().getLc_address());
        betweenDay = DateUtils.getBetweenDay(new Date(System.currentTimeMillis()), falg.getBorrow_time());
        _mChubanshijian.setText(betweenDay + "天");
//        Log.i("tag", "bindData: -----"+item.getBorrow_num()*30);
        _mHuanshustianshu.setText(falg.getBorrow_num() * 30 + (30 - betweenDay) + " 天,已经续借 " + falg.getBorrow_num() + " 次。");
    }




    @OnClick({R.id.book_name, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.book_name:

                Intent intetn = new Intent(this, BookInfoActivity.class);
                intetn.putExtra("data", falg.getBook());
                startActivity(intetn);
                finish();
                break;
            case R.id.fab://进行续借
                _mFab.setClickable(false);

                HttpUtils httpUtils = new HttpUtils();
                String url = GetHttp.getHttpConnetion() + "XuJieBorrowbBookservlet";
                RequestParams pre = new RequestParams();
                pre.addBodyParameter("xunum", (falg.getBorrow_num() + 1) + "");
                pre.addBodyParameter("stu_id", ((Library) getApplication()).getStudent().getStu_id() + "");
                pre.addBodyParameter("borroe_id", falg.getBorrow_id() + "");
                httpUtils.send(HttpMethod.POST, url, pre, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;
                        if ("0".equals(result)) {
                            falg.setBorrow_num(falg.getBorrow_num() + 1);
                            _mHuanshustianshu.setText(falg.getBorrow_num() * 30 + (30 - betweenDay) + " 天,已经续借 " + falg.getBorrow_num() + " 次。");
                            Toast.makeText(BrrowBookInfoActivity.this, "续借成功", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(BrrowBookInfoActivity.this, "网络异常", Toast.LENGTH_SHORT).show();

                        }

                        _mFab.setClickable(true);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        _mFab.setClickable(true);
                        Toast.makeText(BrrowBookInfoActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    }

                });


                break;
        }
    }
}
