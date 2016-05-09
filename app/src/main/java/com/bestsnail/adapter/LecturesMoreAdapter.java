package com.bestsnail.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.bestsnail.R;
import com.bestsnail.bean.LecturesTable;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 作者：liang on 2016/5/9 16:10
 */
public class LecturesMoreAdapter extends BaseRecyclerAdapter<LecturesTable> {
    public static final int PULL_TO_LOAD_MORE = 0;
    public static final int LOADING = 1;
    public static final int NO_MORE = 2;
    private int mFooterStatus = PULL_TO_LOAD_MORE;

    public LecturesMoreAdapter(Context ctx, List<LecturesTable> list) {
        super(ctx, list);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_cardview;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, LecturesTable item) {
        //调用holder.getView(),getXXX()方法根据id得到控件实例，进行数据绑定即可
        holder.setText(R.id.tv_num, item.getLec_title());
        holder.setText(R.id.author_tv, "主讲人：");
        holder.setText(R.id.textView5, "开讲时间：");

        holder.setText(R.id.author, item.getLec_person());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        holder.setText(R.id.time, simpleDateFormat.format(item.getLec_time()));
    }

    @Override
    protected int getFooterLayoutId() {
        return R.layout.footer_load_more;
    }

    @Override
    protected void bindFooter(RecyclerViewHolder holder, int position) {
        ProgressBar progressBar = (ProgressBar) holder.getView(R.id.progressBar);
        switch (mFooterStatus) {
            case PULL_TO_LOAD_MORE:
                progressBar.setVisibility(View.VISIBLE);
                holder.setText(R.id.tv_footer, "上拉加载更多...");
                break;
            case LOADING:
                progressBar.setVisibility(View.VISIBLE);
                holder.setText(R.id.tv_footer, "正在拼命加载...");
                break;
            case NO_MORE:
                holder.setText(R.id.tv_footer, "没有更多了！");
                progressBar.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void setFooterStatus(int status) {
        mFooterStatus = status;
        notifyDataSetChanged();
    }
}
