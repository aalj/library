package com.example.qr_codescan;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private final static int SCANNIN_GREQUEST_CODE2 = 2;
    @Bind(R.id.dizhi)
    EditText _mDizhi;
    @Bind(R.id.suiji)
    Button _mSuiji;
    @Bind(R.id.qingkong)
    Button _mQingkong;


    private TextView isbn_info;
    private TextView suoshuhao_info;

    private EditText name;
    private EditText zuozhe;
    private EditText chubanshe_et;

//    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        isbn_info = (TextView) findViewById(R.id.isbn_info);
        suoshuhao_info = (TextView) findViewById(R.id.suoshuhao_info);
        name = (EditText) findViewById(R.id.name);
        zuozhe = (EditText) findViewById(R.id.zuozhe);
        chubanshe_et = (EditText) findViewById(R.id.chubanshe_et);


        Button mButton = (Button) findViewById(R.id.isbn);
        Button mButton2 = (Button) findViewById(R.id.suoshuhao);
        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String isbn_infos = isbn_info.getText().toString();
                String suoshuhao_infos = suoshuhao_info.getText().toString();
                String names = name.getText().toString();
                String zuozhes = zuozhe.getText().toString();
                String chubanshe_ets = chubanshe_et.getText().toString();
                String dizhi = _mDizhi.getText().toString();

                if (!TextUtils.isEmpty(isbn_infos) &&
                        !TextUtils.isEmpty(suoshuhao_infos) &&
                        !TextUtils.isEmpty(names) &&
                        !TextUtils.isEmpty(zuozhes) &&
                        !TextUtils.isEmpty(chubanshe_ets) &&
                        !TextUtils.isEmpty(dizhi)) {


                    HttpUtils
                            httpUtils = new HttpUtils();

//                    String url = "http://192.168.1.104:8080/LibraryWeb/Savebook";
                String url = "http://www.bestsnail.com:8965/LibraryWeb/Savebook";
                    RequestParams par = new RequestParams();
                    try {
                        par.addBodyParameter("isbn_infos", URLEncoder.encode(isbn_infos, "utf-8"));
                        par.addBodyParameter("suoshuhao_infos", URLEncoder.encode(suoshuhao_infos, "utf-8"));
                        par.addBodyParameter("names", URLEncoder.encode(names, "utf-8"));
                        par.addBodyParameter("zuozhes", URLEncoder.encode(zuozhes, "utf-8"));
                        par.addBodyParameter("chubanshe_ets", URLEncoder.encode(chubanshe_ets, "utf-8"));
                        par.addBodyParameter("dizhi", URLEncoder.encode(dizhi, "utf-8"));

                        httpUtils.send(HttpMethod.POST, url, par, new RequestCallBack<String>() {

                            @Override
                            public void onFailure(HttpException arg0, String arg1) {
                                Toast.makeText(getApplicationContext(), arg1, Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onSuccess(ResponseInfo<String> arg0) {
                                Toast.makeText(getApplicationContext(), arg0.result, Toast.LENGTH_SHORT).show();


                            }
                        });

                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(MainActivity.this, "不能有空值", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
            }
        });
        mButton2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE2);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();

                    isbn_info.setText(bundle.getString("result"));
                }
                break;
            case SCANNIN_GREQUEST_CODE2:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();

                    suoshuhao_info.setText(bundle.getString("result"));
                }
                break;
        }
    }


    @OnClick({R.id.suiji, R.id.qingkong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.suiji://随机产生地址代码
                Random random = new Random();
                int i = random.nextInt(3) + 1;

                _mDizhi.setText(i + "");

                break;
            case R.id.qingkong://清空所有数据
                _mDizhi.setText("");


                isbn_info.setText("ISBN号");
                suoshuhao_info.setText("索书号");

                name.setText("");
                zuozhe.setText("");
                chubanshe_et.setText("");


                break;
        }
    }
}
