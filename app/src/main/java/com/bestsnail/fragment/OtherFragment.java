package com.bestsnail.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bestsnail.R;
import com.bestsnail.ui.NewsActivity;
import com.bestsnail.ui.ZiYuanActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends Fragment {


    @Bind(R.id.news)
    ImageView _mNews;
    @Bind(R.id.news_card)
    CardView _mNewsCard;
    @Bind(R.id.ziyuan)
    ImageView _mZiyuan;
    @Bind(R.id.ziyuan_card)
    CardView _mZiyuanCard;
    @Bind(R.id.tinajia_card)
    CardView _mTinajiaCard;

    public OtherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.news_card, R.id.ziyuan_card, R.id.tinajia_card})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.news_card:
                Intent intent2 = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent2);
                break;
            case R.id.ziyuan_card:
                Intent intent = new Intent(getActivity(), ZiYuanActivity.class);
                startActivity(intent);
                break;
            case R.id.tinajia_card:
                Toast.makeText(getActivity(), "资源整合中尽情期待", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
