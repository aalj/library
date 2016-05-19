package com.bestsnail.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public abstract class InmerseLayoutBaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }

    /**
     * 所有子类都继承的推出Activity
     */
//   public  void finishActivity(){
//       finish();
//       overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
//   }

    /**
     *  设置状态栏透明(即设置沉浸式布局)  判断是否是一个背景图片还是一个最上边的状态栏为标准
     * @param view
     */
//    protected void setImmerseLayout(View view)
//    {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
//        {
//            Window window =getWindow();
//            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            /**
//             * 设置 如果有view  获取状态栏的高度，并且设置padding  （内边距）
//             */
//            if(view!=null)
//            {
//                int statusBarHeight = ScreenUtil.getStatusBarHeight(this.getBaseContext());
//                view.setPadding(0, statusBarHeight, 0, 0);
//            }
//
//        }
//    }
	/**
	 * 切换界面的方法
	 * 
	 * @param clazz
	 */
//	public void jumpActivity(Context clazz1,Class<?> clazz2)
//	{
//		Intent intent = new Intent(clazz1, clazz2);
//		startActivity(intent);
//		finishActivity();
//
//	}
    /**
     * 返回的点击事件
     * @param view
     */
//    public void back(View view)
//    {
//        setBackListener();
//    }
    /**
     * 设置标题的方法
     */
//    public  abstract void  setBackListener();
//    public  void setHeadTitle(String title){
//    	TextView tvTitle=(TextView)findViewById(R.id.tv_head_title);
//    	tvTitle.setText(title);
////    	tvTitle.setTextColor(Color.RED);
//    }
//    public boolean exit_flag = false;

	/**
	 * 返回键捕捉
	 */
//	@Override
//	public void onBackPressed() {
//		/**
//		 * 调用退出的方法
//		 */
//		exit();
//	}

	/**
	 * 返回键 退出的方法
	 */
//	public void exit() {
//		if (!exit_flag) {
//			Toast.makeText(getApplicationContext(), "再按一次退出程序",
//					Toast.LENGTH_SHORT).show();
//			myHandler.sendEmptyMessageDelayed(0, 2000);
//			exit_flag=true;
//		} else {
//			finishActivity();
//		}
//	}

	/**
	 * 返回键的handler
	 */
//	public Handler myHandler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			exit_flag = false;
//		}
//	};
}
