package com.bestsnail.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.bean.BookTable;
import com.bestsnail.ui.BorrowBookXuJieActivityo;
import com.bestsnail.ui.EbookActivity;
import com.bestsnail.ui.GuanCangActivity;
import com.bestsnail.ui.LecturesActivity;
import com.bestsnail.ui.Library;
import com.bestsnail.ui.LoginActivity;
import com.bestsnail.ui.SearchResultActivity;
import com.bestsnail.utils.GetHttp;
import com.bestsnail.view.HomeFragmentView;
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
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends Fragment implements HomeFragmentView {
    private final static int SCANNIN_GREQUEST_CODE = 1;
    public static String XUJIE = "XUEJIE";
    View view = null;
    @Bind(R.id.guancangsea)
    LinearLayout guancangsea;
    @Bind(R.id.xujie)
    LinearLayout xujie;
    @Bind(R.id.erweima)
    LinearLayout erweima;
    @Bind(R.id.jiangzuo)
    LinearLayout jiangzuo;
    @Bind(R.id.tuijian)
    LinearLayout tuijian;
    @Bind(R.id.ebook)
    LinearLayout ebook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.guancangsea, R.id.xujie, R.id.erweima, R.id.jiangzuo, R.id.tuijian, R.id.ebook})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.guancangsea://跳转到馆藏查询
                Intent intent = new Intent(getActivity(), GuanCangActivity.class);
                getActivity().startActivity(intent);
//                Toast.makeText(getActivity(), "guancangsea", Toast.LENGTH_SHORT).show();
                break;
            case R.id.xujie://跳转到图书续借
                Intent intent1 = new
                        Intent();
                if(((Library)getActivity().getApplication()).getStudent().getStu_id()>0){
                    intent1.setClass(getActivity(), BorrowBookXuJieActivityo.class);
                }else{

                    intent1.setClass(getActivity(), LoginActivity.class);
                    intent1.putExtra("flags", XUJIE);

                }


                startActivity(intent1);
//                startActivity(intent1);
//                Toast.makeText(getActivity(), "xujie", Toast.LENGTH_SHORT).show();
                break;
            case R.id.erweima://跳转到二维码查书
                Intent intent2 = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent2, 0);
//                Toast.makeText(getActivity(), "erweima", Toast.LENGTH_SHORT).show();
                break;

            case R.id.jiangzuo://跳转到图书馆讲座列表页面
                Intent intent3 = new Intent(getActivity(), LecturesActivity.class);
                startActivity(intent3);
                Toast.makeText(getActivity(), "jiangzuo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tuijian://跳转到图书推荐页面
                Toast.makeText(getActivity(), "请稍等正在准备数据", Toast.LENGTH_SHORT).show();
                getRemenTushu();
                break;
            case R.id.ebook://跳转到电子资源
                Intent intent4 = new Intent(getActivity(), EbookActivity.class);
                startActivity(intent4);

//                Toast.makeText(getActivity(), "ebook", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void setIntent(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        getActivity().startActivity(intent);
    }

    @Override
    public void lunBo() {
        

    }

    @Override
    public void onclickLunBo() {

    }


    public void getRemenTushu() {

        //实现访问网络的到数据
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        String url = GetHttp.getHttpConnetion() + "SendReMenSousuo";
        Log.i("tag", url);

        httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("TAG", responseInfo.result);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                Type type = new TypeToken<List<BookTable>>() {
                }.getType();
                List<BookTable> list = gson.fromJson(responseInfo.result, type);
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("data", (Serializable) list);
                getActivity().startActivity(intent);


            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });


    }


}
