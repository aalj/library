package com.bestsnail.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.bestsnail.bean.BookTable;
import com.bestsnail.recyclerviewrefesh.R;

import java.util.List;

/**
 * Created by tlh on 2016/2/18.
 */
public class FooterLoadMoreAdapter extends BaseRecyclerAdapter<BookTable> {
    public static final int PULL_TO_LOAD_MORE = 0;
    public static final int LOADING = 1;
    public static final int NO_MORE = 2;
    private int mFooterStatus = PULL_TO_LOAD_MORE;

    //    public FooterLoadMoreAdapter(Context ctx, List<String> list, int y) {
//        super(ctx, list,y);
//    }
    public FooterLoadMoreAdapter(Context ctx, List<BookTable> list) {
        super(ctx, list);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_cardview;
    }

    @Override
    protected int getHeaderLayoutId() {
        return R.layout.header;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, BookTable item) {
        //调用holder.getView(),getXXX()方法根据id得到控件实例，进行数据绑定即可
        holder.setText(R.id.tv_num, item.getBook_name());
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

    @Override
    protected void bindHeader(RecyclerViewHolder holder, int position) {
//       holder.setText(R.id.tv_header,"woshi头部");
//        holder.setClickListener(R.id.tv_header, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "我能点你吗！！", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
