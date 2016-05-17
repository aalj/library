package com.example.qr_codescan;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends Activity {
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private final static int SCANNIN_GREQUEST_CODE2 = 2;


    private TextView isbn_info;
    private TextView suoshuhao_info;

    private EditText name;
    private EditText zuozhe;
    private EditText chubanshe_et;

    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                HttpUtils
                        httpUtils = new HttpUtils();

                String url = "http://www.bestsnail.com:8965/LibraryWeb/Savebook";
                RequestParams par = new RequestParams();
                try {
                    par.addBodyParameter("isbn_infos", URLEncoder.encode(isbn_infos, "utf-8"));
                    par.addBodyParameter("suoshuhao_infos", URLEncoder.encode(suoshuhao_infos, "utf-8"));
                    par.addBodyParameter("names", URLEncoder.encode(names, "utf-8"));
                    par.addBodyParameter("zuozhes", URLEncoder.encode(zuozhes, "utf-8"));
                    par.addBodyParameter("chubanshe_ets", URLEncoder.encode(chubanshe_ets, "utf-8"));

                    httpUtils.send(HttpMethod.POST, url, par, new RequestCallBack<String>() {

                        @Override
                        public void onFailure(HttpException arg0, String arg1) {
                            Toast.makeText(getApplicationContext(), arg1, 1).show();

                        }

                        @Override
                        public void onSuccess(ResponseInfo<String> arg0) {
                            Toast.makeText(getApplicationContext(), arg0.result, 1).show();


                        }
                    });

                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
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

}
