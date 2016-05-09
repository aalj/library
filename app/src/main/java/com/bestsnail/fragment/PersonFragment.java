package com.bestsnail.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.adapter.GridViewAdapter;
import com.bestsnail.bean.PersonItemGridView;
import com.bestsnail.view.PersonFragmentView;
import com.bestsnail.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PersonFragment extends Fragment implements PersonFragmentView {


    View view = null;
    @Bind(R.id.grid_item)
    GridView gridItem;
    @Bind(R.id.touxiang)
    CircleImageView touxiang;
    @Bind(R.id.xiaoxi_ima)
    ImageView xiaoxiIma;
    @Bind(R.id.bianji_ima)
    ImageView bianjiIma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_person, container, false);

        ButterKnife.bind(this, view);
        initView();
        MyOnItemClickListener myOnItemClickListener = new MyOnItemClickListener();
        gridItem.setOnItemClickListener(myOnItemClickListener);
        return view;
    }


    @Override
    public void initView() {
        int[] imaRes = {R.mipmap.yijie, R.mipmap.pxujie,
                R.mipmap.jiangou, R.mipmap.lishi,
                R.mipmap.baoming, R.mipmap.shoucang,
                R.mipmap.qingli, R.mipmap.lixianxiazai,
                R.mipmap.guanyuwomen};
        int[] imaRes_s = {R.mipmap.yijie_s, R.mipmap.pxujie_s,
                R.mipmap.jiangou_s, R.mipmap.lishi_s,
                R.mipmap.baoming_s, R.mipmap.shoucang_s,
                R.mipmap.qingli_s, R.mipmap.lixianxiazai_s,
                R.mipmap.guanyuwomen_s};
        String[] itemName = {"已借图书", "图书续借"
                , "图书荐购", "借阅历史", "报名讲座", "收藏", "缓存清理"
                , "离线下载", "关于我们"};
        List<PersonItemGridView> list = new ArrayList<>();
        for (int i = 0; i < imaRes.length; i++) {
            list.add(new PersonItemGridView(imaRes_s[i], itemName[i]));
        }


        gridItem.setAdapter(new GridViewAdapter(getActivity(), list));

    }

    @Override
    public void intentActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);
        getActivity().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.touxiang, R.id.xiaoxi_ima, R.id.bianji_ima})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.touxiang://点击头像
                Toast.makeText(getActivity(), "touxiang", Toast.LENGTH_SHORT).show();
                break;
            case R.id.xiaoxi_ima://点击消息
                Toast.makeText(getActivity(), "xiaoxi_ima", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bianji_ima://点击编辑
                Toast.makeText(getActivity(), "bianji_ima", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0://已借图书
                    Toast.makeText(getActivity(), "已借图书", Toast.LENGTH_SHORT).show();
                    break;
                case 1://图书续借
                    Toast.makeText(getActivity(), "图书续借", Toast.LENGTH_SHORT).show();
                    break;
                case 2://图书荐购
                    Toast.makeText(getActivity(), "图书荐购", Toast.LENGTH_SHORT).show();
                    break;
                case 3://借阅历史
                    Toast.makeText(getActivity(), "借阅历史", Toast.LENGTH_SHORT).show();
                    break;
                case 4://报名讲座
                    Toast.makeText(getActivity(), "报名讲座", Toast.LENGTH_SHORT).show();
                    break;
                case 5://收藏
                    Toast.makeText(getActivity(), "收藏", Toast.LENGTH_SHORT).show();
                    break;
                case 6://缓存清理
                    Toast.makeText(getActivity(), "缓存清理", Toast.LENGTH_SHORT).show();
                    break;
                case 7://离线下载
                    Toast.makeText(getActivity(), "离线下载", Toast.LENGTH_SHORT).show();
                    break;
                case 8://关于我们
                    Toast.makeText(getActivity(), "关于我们", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    }


}
