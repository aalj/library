package com.bestsnail.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsnail.BuildConfig;
import com.bestsnail.R;
import com.bestsnail.bean.LecturesTable;
import com.bestsnail.bean.Student;
import com.bestsnail.utils.GetHttp;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LecturesInfoActivity extends AppCompatActivity {

    public static String BAOMING = "baoming";
    @Bind(R.id.toolbar)
    Toolbar _mToolbar;
    @Bind(R.id.jaingzuo_name)
    TextView _mJaingzuoName;
    @Bind(R.id.zhujiangren)
    TextView _mZhujiangren;
    @Bind(R.id.address)
    TextView _mAddress;
    @Bind(R.id.jiangzuotime)
    TextView _mJiangzuotime;
    @Bind(R.id.timechang)
    TextView _mTimechang;
    @Bind(R.id.person)
    TextView _mPerson;
    @Bind(R.id.book_address_list)
    TextView _mBookAddressList;
    @Bind(R.id.zongrenhus)
    TextView _mZongrenhus;
    @Bind(R.id.personnum)
    TextView _mPersonnum;
    LecturesTable lecturesTable = null;

    Student student = null;


    HttpUtils httpUtils = new HttpUtils();
    android.os.Handler _mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            _mPersonnum.setTextColor(0xff990000);
            _mPersonnum.setText((String) msg.obj);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures_info);
        ButterKnife.bind(this);
//          stu_id = ((Library) getApplication()).getStudent().getStu_id();

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

                    baoMing();



                return true;
            }
        });
        initView();

    }


    @Override
    protected void onResume() {
        super.onResume();
          student = ((Library)getApplication()).getStudent();
    }

    private void initView() {
//        info
        lecturesTable = (LecturesTable) getIntent().getSerializableExtra("info");
        getBaoMingRenshu(lecturesTable.getLec_id());
        _mJaingzuoName.setText(lecturesTable.getLec_title());
        _mZhujiangren.setText(lecturesTable.getLec_person());
        _mZongrenhus.setText(lecturesTable.getLec_per_num() + "");
        _mAddress.setText(lecturesTable.getLec_address());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm");
        _mJiangzuotime.setText(simpleDateFormat.format(lecturesTable.getLec_time()));
        _mTimechang.setText(lecturesTable.getLec_longtime() + "分钟");
        _mPerson.setText("无");
        _mPerson.setTextColor(0xff990000);
        _mBookAddressList.setText(lecturesTable.getLec_info());


    }

    //的到报名改讲座的人数
    private void getBaoMingRenshu(int id) {
        HttpUtils httpUtils = new HttpUtils();
        String url = GetHttp.getHttpConnetion() + "getLecRegistrationNum";
        RequestParams pre = new RequestParams();
        pre.addBodyParameter("id", id + "");
        httpUtils.send(HttpMethod.POST, url, pre, new RequestCallBack<String>() {


            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Log.i("tag", "onSuccess: "+result);
                Message obtain = Message.obtain();
                obtain.obj = result;
                _mHandler.sendMessage(obtain);


            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);

        return true;
    }


    /**
     * 进行讲座报名
     */
    private void baoMing() {

//TODO
          student = ((Library) getApplication()).getStudent();

            Log.d("tag", "student.getStu_id():" + student.getStu_id());
        if (student!=null&&student.getStu_id() > 0) {


            String url = GetHttp.getHttpConnetion() + "SetLecturesRegistration";
            RequestParams pre = new RequestParams();
            pre.addBodyParameter("stu_id", student.getStu_id() + "");
            pre.addBodyParameter("lec_id", lecturesTable.getLec_id()+"");
            httpUtils.send(HttpMethod.POST, url, pre, new RequestCallBack<String>() {


                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String result = responseInfo.result;
                   Toast.makeText(LecturesInfoActivity.this, result, Toast.LENGTH_SHORT).show();
                    getBaoMingRenshu(lecturesTable.getLec_id());



                }

                @Override
                public void onFailure(HttpException error, String msg) {
                    Toast.makeText(LecturesInfoActivity.this, "网络故障", Toast.LENGTH_SHORT).show();
                }

            });





        } else {
            //跳转到登陆页面，进行登陆
            Intent intent = new Intent(LecturesInfoActivity.this, LoginActivity.class);
            intent.putExtra("flags", BAOMING);
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
