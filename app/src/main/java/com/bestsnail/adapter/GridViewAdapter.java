package com.bestsnail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsnail.R;
import com.bestsnail.bean.PersonItemGridView;

import java.util.List;

/**
 * 作者：liang on 2016/4/29 19:37
 */
public class GridViewAdapter extends BaseAdapter {
    List<PersonItemGridView> list = null;
    LayoutInflater inflater = null;


    public GridViewAdapter(Context context, List<PersonItemGridView> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder viewHolder = null;
        if (convertView != null) {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        } else {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.gridview_item, null);
            viewHolder.image = (ImageView) view.findViewById(R.id.image);
            viewHolder.text = (TextView) view.findViewById(R.id.text);
            view.setTag(viewHolder);

        }

        viewHolder.image.setBackgroundResource(list.get(position).getImaRes());
        viewHolder.text.setText(list.get(position).getItemName());
        return view;
    }


    class ViewHolder {
        ImageView image;
        TextView text;
    }
}
