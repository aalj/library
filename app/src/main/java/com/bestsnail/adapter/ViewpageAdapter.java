package com.bestsnail.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ViewpageAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_fragment; //fragment列表


    public ViewpageAdapter(FragmentManager fm, List<Fragment> list_fragment) {
        super(fm);
        this.list_fragment = list_fragment;

    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }


}