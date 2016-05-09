package com.bestsnail.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.bean.BookTable;
import com.bestsnail.presenter.IRemenTuShuPresenter;
import com.bestsnail.presenter.ReMenTuShu;
import com.bestsnail.ui.SearchResultActivity;
import com.bestsnail.utils.GetGson;
import com.bestsnail.utils.GetHttp;
import com.bestsnail.view.SousuoFragmentView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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


public class SousuoFragment extends Fragment implements SousuoFragmentView {

    private static final String TAG = "tag";
    View view = null;

    @Bind(R.id.et_guanjianzi)
    EditText etGuanjianzi;
    @Bind(R.id.but_kuaisusousuo)
    Button butKuaisusousuo;
    @Bind(R.id.tv_remensousuo)
    TextView tvRemensousuo;


    SharedPreferences sp = null;
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    IRemenTuShuPresenter<BookTable> reMenTuShu = null;
    List<BookTable> bookName = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sousuo, container, false);
        ButterKnife.bind(this, view);
//        reMenTuShu = new MyRe(this);
        tvRemensousuo.setClickable(false);
        reMenTuShu = new ReMenTuShu(this);
        sp = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {

            reMenTuShu.loadData();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.et_guanjianzi, R.id.but_kuaisusousuo, R.id.tv_remensousuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_guanjianzi://输入点击
                Toast.makeText(getActivity(), "输入点击", Toast.LENGTH_SHORT).show();
                break;
            case R.id.but_kuaisusousuo://搜索按钮
                jinxingSouSuo();
                Toast.makeText(getActivity(), "搜索按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_remensousuo://热门搜索点击
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("data", (Serializable) bookName);
                getActivity().startActivity(intent);

                Toast.makeText(getActivity(), "热门搜索点击", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void buttonClick() {//处理按钮点击查询输入框的关键字

        jinxingSouSuo();
    }

    private void jinxingSouSuo() {
        String name = etGuanjianzi.getText().toString();
        if (!TextUtils.isEmpty(name)) {
            //进行搜索处理
            //1、上传检索数据并返回相应的对象，然后条状页面显示
            HttpUtils httpUtils = new HttpUtils();
            String url = GetHttp.getHttpConnetion() + "KuaisuSearchBook";
            RequestParams pra = new RequestParams();
            try {
                pra.addBodyParameter("guanjianzi", URLEncoder.encode(name, "utf-8"));
//                pra.addBodyParameter("flag", URLEncoder.encode(flag + "", "utf-8"));
//                pra.addBodyParameter("guancangflag", URLEncoder.encode(guancangflag + "", "utf-8"));
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
                    Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                    intent.putExtra("data", (Serializable) o);
                    getActivity().startActivity(intent);


                }

                @Override
                public void onFailure(HttpException error, String msg) {

                }
            });


        } else {
            Toast.makeText(getActivity(), "请输入搜索关键字", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void getRemenTuShu(List<BookTable> bookName) {//处理的到的数据用于显示在页面上
        this.bookName = bookName;
        Log.i(TAG, "getRemenTuShu: zhixing ");
        String n = "";


        if (bookName != null && bookName.size() > 0) {
            for (int i = 0; i < bookName.size(); i++) {

                //把集合中的文字拼接成要显示的文字
                n = n + bookName.get(i).getBook_name() + "\n";
                if (i == 3) {
                    break;
                }


            }
            tvRemensousuo.setText(n);
            tvRemensousuo.setClickable(true);
        } else {

            tvRemensousuo.setText("没有热门图书");
            tvRemensousuo.setClickable(false);
        }


    }

    @Override
    public void setTextViewClick() {

    }

    //通过实现
    class MyRe extends ReMenTuShu {

        public MyRe(SousuoFragmentView sousuoFragmentView) {
            super(sousuoFragmentView);
        }

//        @Override
//        protected void saveSharePre(List<String> bookName) {//把网络获取到的数据保存到本地
//
//            String s = gson.toJson(bookName);
//            SharedPreferences.Editor edit =
//                    sp.edit();
//            edit.putString("rementushu", s);
//            edit.commit();
//
//
//        }
    }


}
