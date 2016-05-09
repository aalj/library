package com.bestsnail.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bestsnail.R;
import com.bestsnail.adapter.MyBasesadapter;
import com.bestsnail.adapter.ViewHodle;
import com.bestsnail.bean.BookTable;
import com.bestsnail.utils.GetHttp;
import com.bestsnail.widget.MyListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.lang.reflect.Type;
import java.util.List;


public class RementushuFragment extends Fragment {


    private MyListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        this.list = (MyListView) view.findViewById(R.id.list);
        initView();

        return view;
    }

    private void initView() {
        HttpUtils httpUtils = new HttpUtils();
        String url = GetHttp.getHttpConnetion() + "SearchBook";
        httpUtils.send(HttpMethod.POST, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("tag", "RementushuFragment------->>>>>>" + responseInfo.result);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                Type type = new TypeToken<List<BookTable>>() {
                }.getType();
                List<BookTable> lisshujut = gson.fromJson(responseInfo.result, type);

                list.setAdapter(new MyBasesadapter<BookTable>(getActivity(), lisshujut, R.layout.fragment_item) {
                    @Override
                    public void convert(ViewHodle viewHolder, BookTable item) {
                        viewHolder.setText(R.id.id, item.getBook_name());
                        viewHolder.setText(R.id.content, item.getBook_isbn());
                    }
                });


            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });


    }


}
