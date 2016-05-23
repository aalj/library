package com.bestsnail.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.bean.BookTable;
import com.bestsnail.utils.GetGson;
import com.bestsnail.utils.GetHttp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.karics.library.zxing.android.CaptureActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EbookActivity extends AppCompatActivity {

    @Bind(R.id.et_guanjianzi)
    EditText _mEtGuanjianzi;
    @Bind(R.id.but_kuaisusousuo)
    Button _mButKuaisusousuo;
    @Bind(R.id.all_rabut)
    RadioButton _mAllRabut;
    @Bind(R.id.isbn_rabut)
    RadioButton _mIsbnRabut;
    @Bind(R.id.radioGroup)
    RadioGroup _mRadioGroup;
    @Bind(R.id.fab)
    FloatingActionButton _mFab;

    //用于标记使用那种搜索方式，默认为0使用全部搜索，为1使用isbn搜索
    private int temp = 0;
    public static int FLAG = 12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);
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
//        //设置toolsbar上面左面的点击事件
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Intent intetn = new Intent(EbookActivity.this, MainActivity.class);
//                startActivity(intetn);
//                finish();
//
//                return true;
//            }
//        });
        //RadioGroup监听事件
        _mRadioGroup.setOnCheckedChangeListener(new MeOnCheckedChangeListener());


    }

    @OnClick({R.id.but_kuaisusousuo, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_kuaisusousuo:
                if (temp == 0) {//跳转到查询列表
//                    Intent intent = new Intent(ctx, SearchResultActivity.class);
//                    intent.putExtra("data", (Serializable) o);
//                    ctx.startActivity(intent);
                } else {//跳转到ibsn具体书记信息

                }


                break;
            case R.id.fab:
                Intent intent
                        = new Intent(this, CaptureActivity.class);
                intent.putExtra("temp", FLAG);
                startActivity
                        (intent);


                break;
        }
    }


    //RadioGroup的选择监听
    class MeOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.all_rabut://搜索全部
                    _mEtGuanjianzi.setText("");
                    temp = 0;
                    _mEtGuanjianzi.setHint("请输入图书关键字");
                    _mFab.setVisibility(View.GONE);

                    break;
                case R.id.isbn_rabut://通过输入isbn搜索
                    _mEtGuanjianzi.setText("");

                    temp = 1;
                    _mEtGuanjianzi.setHint("请输入图书isbn号");
                    _mFab.setVisibility(View.VISIBLE);
                    break;
            }


        }
    }

    //扫描返回只接受方法
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            String isbn = data.getStringExtra("isbn");
//            _mEtGuanjianzi.setText(isbn);
//            temp = 1;
//            _mIsbnRabut.setClickable(true);
//            getBookData();
//
//        }
//    }

    public void getBookData() {
        //实现访问网络的到数据
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        String url = GetHttp.getHttpConnetion() + "SendReMenSousuo";
        Log.i("tag", url);

        httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                Type type = new TypeToken<List<BookTable>>() {
                }.getType();
                BookTable list = gson.fromJson(responseInfo.result, type);


                Intent intent = new Intent(EbookActivity.this, BookInfoActivity.class);
                intent.putExtra("data", list);
                startActivity(intent);


            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });


    }


    private void jinxingSouSuo() {
        String name = _mEtGuanjianzi.getText().toString();
        if (!TextUtils.isEmpty(name)) {
            //进行搜索处理
            //1、上传检索数据并返回相应的对象，然后条状页面显示
            HttpUtils httpUtils = new HttpUtils();
            String url = GetHttp.getHttpConnetion() + "KuaisuSearchBook";
            RequestParams pra = new RequestParams();
            try {
                pra.addBodyParameter("guanjianzi", URLEncoder.encode(name, "utf-8"));
                pra.addBodyParameter("temp", 12+"");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //通过POST请求查询图书，然后返回查询到的数据
            httpUtils.send(HttpMethod.POST, url, pra, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    Type type = new TypeToken<List<BookTable>>() {
                    }.getType();
                    Gson gson =
                            GetGson.mGetGson();
                    List<BookTable> o = gson.fromJson(responseInfo.result, type);
                    Intent intent = new Intent(EbookActivity.this, SearchResultActivity.class);
                    intent.putExtra("data", (Serializable) o);
                    startActivity(intent);


                }

                @Override
                public void onFailure(HttpException error, String msg) {

                }
            });


        } else {
            Toast.makeText(this, "请输入搜索关键字", Toast.LENGTH_SHORT).show();
        }


    }

}
