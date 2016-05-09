package com.bestsnail.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.bean.BookTable;
import com.bestsnail.ui.SearchResultActivity;
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

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * 作者：liang on 2016/5/7 19:47
 */
public class GuanCangHeadAdapter extends BaseRecyclerAdapter<BookTable> {

    LayoutInflater _mLayoutInflater = null;
    ImageView _mImageView;

    Spinner _mSpinner;

    EditText _mEdGuanjianzi;
    Button _mSousuoBut;
    /**
     * 通过构造函数能够得到需要加载的数据
     *
     * @param ctx
     * @param list
     */
    Context ctx = null;
    int tempOld = -1;
    //用于标记当前使用那种搜索方式
    private int flag = 0;
    //标记去那个馆藏查书
    private int guancangflag = 0;
    private List<TextView> lv_textView; //tab名称列表

    public GuanCangHeadAdapter(Context ctx, List<BookTable> list) {
        super(ctx, list);
        tempOld = 0;
        this._mLayoutInflater = LayoutInflater.from(ctx);
        this.ctx = ctx;
    }

    @Override
    protected void bindHeader(RecyclerViewHolder holder, int position) {
        lv_textView = new ArrayList<>();
        ClickOnClickListener clickOnClickListener = new ClickOnClickListener();

        holder.setClickListener(R.id.all_tv, clickOnClickListener);
        holder.setClickListener(R.id.zuozh_tv, clickOnClickListener);
        holder.setClickListener(R.id.zhutici_tv, clickOnClickListener);
        holder.setClickListener(R.id.sousuo_but, clickOnClickListener);
        TextView all_tv = (TextView) holder.getView(R.id.all_tv);
        all_tv.setSelected(true);
        lv_textView.add((TextView) holder.getView(R.id.all_tv));
        lv_textView.add((TextView) holder.getView(R.id.zuozh_tv));
        lv_textView.add((TextView) holder.getView(R.id.zhutici_tv));
        lv_textView.add((TextView) holder.getView(R.id.sousuo_but));


        _mSpinner = (Spinner) holder.getView(R.id.spinner);

        _mEdGuanjianzi = (EditText) holder.getView(R.id.ed_guanjianzi);
        String[] mItems = {"西区", "中区", "东区"};
        ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item, mItems);

        _mSpinner.setAdapter(_Adapter);
        _mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                guancangflag = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected int getHeaderLayoutId() {
        return R.layout.guancanghand;
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.fragment_item;
    }

    @Override
    protected void bindData(RecyclerViewHolder holder, int position, BookTable item) {
        holder.setText(R.id.id, item.getBook_name());
        holder.setText(R.id.zuozhe, item.getBook_author());
        holder.setText(R.id.content, item.getBook_isbn());

    }

    private void jinxingSouSuo() {
        String name = _mEdGuanjianzi.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {
            //进行搜索处理
            //1、上传检索数据并返回相应的对象，然后条状页面显示
            HttpUtils httpUtils = new HttpUtils();
            String url = GetHttp.getHttpConnetion() + "SearchBook";
            RequestParams pra = new RequestParams();
            try {
                pra.addBodyParameter("guanjianzi", URLEncoder.encode(name, "utf-8"));
                pra.addBodyParameter("flag", URLEncoder.encode(flag + "", "utf-8"));
                pra.addBodyParameter("guancangflag", URLEncoder.encode(guancangflag + "", "utf-8"));
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
                    Intent intent = new Intent(ctx, SearchResultActivity.class);
                    intent.putExtra("data", (Serializable) o);
                    ctx.startActivity(intent);


                }

                @Override
                public void onFailure(HttpException error, String msg) {

                }
            });


        } else {
            Toast.makeText(ctx, "请输入搜索关键字", Toast.LENGTH_SHORT).show();
        }


    }

    class ClickOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int temp = -1;
            switch (v.getId()) {

                case R.id.all_tv://全部查询
                    temp = 0;
                    flag = 0;
                    break;

                case R.id.zuozh_tv://作者查询
                    temp = 1;
                    flag = 1;
                    break;
                case R.id.zhutici_tv://主题词查询
                    temp = 2;
                    flag = 2;
                    break;
                case R.id.sousuo_but://搜索按钮
                    jinxingSouSuo();


                    break;


            }
            if (temp != -1) {
                for (int j = 0; j < 3; j++) {
                    if (temp == j) {
                        lv_textView.get(j).setSelected(true);

                    }
                }
                lv_textView.get(tempOld).setSelected(false);
                tempOld = temp;
            }

        }
    }

}
