package com.bestsnail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.utils.GetHttp;
import com.karics.library.zxing.android.CaptureActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import org.apache.http.params.HttpParams;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 图书荐购
 */
public class BookRecommendActivity extends AppCompatActivity {

    public static final int RECOMMENDERWEIMA = 26;


    @Bind(R.id.toolbar)
    Toolbar _mToolbar;
    @Bind(R.id.zuozhe)
    EditText _mZuozhe;
    @Bind(R.id.zhiti)
    EditText _mZhiti;
    @Bind(R.id.chubanshe)
    EditText _mChubanshe;
    @Bind(R.id.isbn)
    EditText _mIsbn;
    @Bind(R.id.button)
    Button _mButton;
    @Bind(R.id.submit)
    Button _mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_recommend);
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


        _mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(BookRecommendActivity.this, "点击菜单", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(BookRecommendActivity.this,BookRecommendLiShiActivity.class);
                startActivity(intent);
                return false;
            }
        });

        initView();
    }

    private void initView() {


    }

    @OnClick({R.id.button, R.id.submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button://调用二维码扫描

                Intent intend = new Intent(BookRecommendActivity.this, CaptureActivity.class);
                intend.putExtra("falg", RECOMMENDERWEIMA);
                startActivityForResult(intend, RECOMMENDERWEIMA);

                break;
            case R.id.submit://提交推荐
                _mSubmit.setClickable(false);
                submitValue();

                break;
        }
    }

    /**
     * 提交所有需要的参数
     */
    private void submitValue() {
        try {
            HttpUtils httpUtils = new HttpUtils();
            String url = GetHttp.getHttpConnetion()+"BookRecommendServlet";
            RequestParams pre = new RequestParams();
            //书名
            String bookName = _mZuozhe.getText().toString();

            //作者
            String bookAuthor = _mZhiti.getText().toString();
            //出版社
            String chubanshe = _mChubanshe.getText().toString();
            //isbn号
            String ISBNnum = _mIsbn.getText().toString();
            if (!TextUtils.isEmpty(bookName) && !TextUtils.isEmpty(bookAuthor)) {
                pre.addBodyParameter("bookname", URLEncoder.encode(bookName, "utf-8"));
                pre.addBodyParameter("stu_id", ((Library)getApplication()).getStudent().getStu_id()+"");

                pre.addBodyParameter("bookzuthor", URLEncoder.encode(bookAuthor, "utf-8"));

                pre.addBodyParameter("chuabanshe",URLEncoder.encode(chubanshe, "utf-8"));
                pre.addBodyParameter("bookisbn",URLEncoder.encode(ISBNnum, "utf-8"));
            }


            httpUtils.send(HttpMethod.POST, url, pre, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    _mSubmit.setClickable(true);
                    Toast.makeText(BookRecommendActivity.this, responseInfo.result, Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onFailure(HttpException error, String msg) {
                    _mSubmit.setClickable(true);
                    Toast.makeText(BookRecommendActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();

                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tuijainlishi, menu);

        return true;
    }


    //扫描返回只接受方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String isbn = data.getStringExtra("isbn");
            _mIsbn.setText(isbn);


        }
    }
}
