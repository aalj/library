package com.bestsnail.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.bestsnail.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AdverView extends RelativeLayout {

	public AdverView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initData();
		initView(context, attrs);
		if (isAutoPlay) {
			startPlay();
		}
	}

	// 轮播图图片数量
	private final static int IMAGE_COUNT = 5;
	// 自动轮播的时间间隔
	private final static int TIME_INTERVAL = 5;
	// 自动轮播启用开关
	private final static boolean isAutoPlay = true;

	// 自定义轮播图的资源ID
	private int[] imagesResIds;
	// 放轮播图片的ImageView 的list
	private List<ImageView> imageViewsList;
	// 放圆点的View的list
	private List<View> dotViewsList;

	private ViewPager viewPager;
	// 当前轮播页
	private int currentItem = 0;
	// 定时任务
	private ScheduledExecutorService scheduledExecutorService;
	// Handler
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			viewPager.setCurrentItem(currentItem);
		}

	};

	/**
	 * 开始轮播图切换
	 */
	private void startPlay() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new AdverShowTask(), 1, 4,
				TimeUnit.SECONDS);// (command：执行线程,initialDelay：初始化延时,period：两次开始执行最小间隔时间,unit：计时单位)
	}

	/**
	 * 停止轮播图切换
	 */
	private void stopPlay() {
		scheduledExecutorService.shutdown();
	}

	/**
	 * 执行轮播图切换任务
	 * 
	 * @author QuanMinJiaKao
	 */
	private class AdverShowTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (viewPager) {
				currentItem = (currentItem + 1) % imageViewsList.size();// 取模的循环
				handler.obtainMessage().sendToTarget();
			}
		}

	}

	public void initData() {
		imagesResIds = new int[] { R.mipmap.adver_1, R.mipmap.adver_2,
				R.mipmap.adver_3, R.mipmap.adver_4, R.mipmap.adver_5

		};
		imageViewsList = new ArrayList<ImageView>();
	
		dotViewsList = new ArrayList<View>();
	}

	public void initView(Context context, AttributeSet attrs) {
		RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(
				context).inflate(R.layout.adver_view_pager, this, true);
		// 设置参数
//		TypedArray array = context.obtainStyledAttributes(attrs,
//				R.styleable.AdverView);
//		int height = (int) array.getDimension(
//				R.styleable.AdverView_AdeverView_height, 100);
//		int width = (int) array.getDimension(
//				R.styleable.AdverView_AdeverView_height, 100);
//		LayoutParams params = new LayoutParams(width, height);
//		relativeLayout.setLayoutParams(params);

		for (int imageID : imagesResIds) {
			ImageView view = new ImageView(context);
			view.setImageResource(imageID);
			view.setScaleType(ScaleType.FIT_XY);
			imageViewsList.add(view);
		}
		//小点点
		dotViewsList.add(findViewById(R.id.v_dot1));
		dotViewsList.add(findViewById(R.id.v_dot2));
		dotViewsList.add(findViewById(R.id.v_dot3));
		dotViewsList.add(findViewById(R.id.v_dot4));
		dotViewsList.add(findViewById(R.id.v_dot5));

		viewPager = (ViewPager) findViewById(R.id.adViewPager);
		viewPager.setFocusable(true);

		viewPager.setAdapter(new MyPagerAdapter());
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
	}

	// ViewPager适配器
	public class MyPagerAdapter extends PagerAdapter implements OnClickListener{
		
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageViewsList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager) container).removeView((View) imageViewsList
					.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub

			((ViewPager) container).addView(imageViewsList.get(position));
			imageViewsList.get(position).setOnClickListener(this);
			return imageViewsList.get(position);
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	}

	/**
	 * ViewPager的监听器 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author QuanMinJiaKao
	 */
	public class MyPageChangeListener implements OnPageChangeListener {

		boolean isAutoPlay = false;

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 1:// 手势滑动，空闲中
				isAutoPlay = false;
				break;
			case 2:// 界面切换中
				isAutoPlay = true;
				break;
			case 0:// 滑动结束，即切换完毕或者加载完毕
					// 当前为最后一张，此时从右向左滑，则切换到第一张
				if (viewPager.getCurrentItem() == viewPager.getAdapter()
						.getCount() - 1 && !isAutoPlay) {
					destoryBitmaps();
					viewPager.setCurrentItem(0);
				}
				// 当前为第一张，此时从左向右滑，则切换到最后一张
				else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
					viewPager
							.setCurrentItem(viewPager.getAdapter().getCount() - 1);
				}
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}
//小点点
		@Override
		public void onPageSelected(int pos) {
			// TODO Auto-generated method stub

			currentItem = pos;
			for (int i = 0; i < dotViewsList.size(); i++) {
				if (i == pos) {
					((View) dotViewsList.get(pos))
							.setBackgroundResource(R.mipmap.yin_dao_dot_selected);
				} else {
					((View) dotViewsList.get(i))
							.setBackgroundResource(R.mipmap.yin_dao_dot);
				}
			}
		}
	}

	/**
	 * 销毁ImageView资源，回收内存
	 * 
	 * @author QuanMinJiaKao
	 */
	private void destoryBitmaps() {

		for (int i = 0; i < IMAGE_COUNT; i++) {
			ImageView imageView = imageViewsList.get(i);
			Drawable drawable = imageView.getDrawable();
			if (drawable != null) {
				// 解除drawable对view的引用
				drawable.setCallback(null);
			}
		}
	}
}
