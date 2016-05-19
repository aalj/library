package com.bestsnail.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bestsnail.R;
import com.bestsnail.bean.BorrowTable;
import com.bestsnail.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 作者：liang on 2016/5/18 14:42
 */
public class BorrowBookLoadMoreAdapter extends BaseRecyclerAdapter<BorrowTable> {
    public static final int PULL_TO_LOAD_MORE = 0;
    public static final int LOADING = 1;
    public static final int NO_MORE = 2;
    public static final int NOMORE = 3;
    private int mFooterStatus = PULL_TO_LOAD_MORE;
    Context ctx;

    /**
     * 通过构造函数能够得到需要加载的数据
     *
     * @param ctx
     * @param list
     */
    public BorrowBookLoadMoreAdapter(Context ctx, List<BorrowTable> list) {
        super(ctx, list);
        this.ctx = ctx;
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.borrow_cardview_item;
    }

    @Override
    protected void bindData(RecyclerViewHolder holder, int position, BorrowTable item) {
        //图片加载数据存在问题，所以延后实现图片
        //TODO
//        holder.SetUrlImage(R.id.imageView3,item.getBook().getBook_image());

        holder.setText(R.id.id, item.getBook().getBook_name());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        holder.setText(R.id.borrowTime, simpleDateFormat.format(item.getBorrow_time()));
//        holder.setText(R.id.remainTime, simpleDateFormat.format(item.getRemand_time()));
        holder.setText(R.id.content, item.getBook().getBook_isbn());
        holder.getView(R.id.textView10).setVisibility(View.GONE);
        //用于计算借阅是否超期
        int betweenDay = DateUtils.getBetweenDay(item.getRemand_time(), item.getBorrow_time());
        holder.getView(R.id.ischaqi).setVisibility(View.VISIBLE);
        holder.setText(R.id.ischaqi, betweenDay + " 天");

    }


    //加载脚布局
    @Override
    protected int getFooterLayoutId() {
        return R.layout.footer_load_more;
    }

    //加载脚部数据
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
            case NOMORE:
                holder.setText(R.id.tv_footer, " ");
                progressBar.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void setFooterStatus(int status) {
        mFooterStatus = status;
        notifyDataSetChanged();
    }
}
