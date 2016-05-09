package com.bestsnail.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * Created by stone on 16-3-8.
 */
public abstract class MyBasesadapter<T> extends BaseAdapter {
    List<T> list = null;
    Context context;

    int reasouId = 0;

    public MyBasesadapter(Context context, List<T> list, int reasouId) {
        this.list = list;
        this.context = context;
        this.reasouId = reasouId;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHodle viewHodle = ViewHodle.getViewHodle(context, parent, reasouId, convertView);

        convert(viewHodle, getItem(position));


        return viewHodle.getConvertView();
    }


    public abstract void convert(ViewHodle viewHolder, T item);


}
