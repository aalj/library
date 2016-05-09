package com.bestsnail.recyclerviewrefesh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @Bind(R.id.webView)
    WebView _mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        //设置WebView属性，能够执行Javascript脚本

        _mWebView.loadUrl("http://www.cnblogs.com/mengdd/archive/2013/03/01/2938295.html");
    }
}
