package com.bestsnail.view;


import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by liang on 2016/4/28.
 */
public interface HomeView {
    /*
    *主页负责的了内容有：
    * 页面的切换，导航状态的改变
    *个人中心，个人数据的获取，登陆功能，
    * 首页全部的页面跳转
    *
    */

    /**
     * 页面的切换，
     *
     * @param hideFragment 隐藏当前的fragment
     * @param addFragment  需要显示的fragment
     */
    void switchTab(Fragment hideFragment, Fragment addFragment);

    /**
     * 切换页面的时候改变底部导航栏的状态
     *
     * @param selectorText   选中状态的textview
     * @param noSelectorText 没有选中的TextView
     * @param selectorIma    选中状态的ImageView
     * @param noSelectorIma  没有选中的ImageView
     */
    void changeStatus(TextView selectorText, TextView noSelectorText, ImageView selectorIma, ImageView noSelectorIma);

    /**
     * 提交登陆，
     * 登陆的执行在persenter里面执行
     */
    void submitLogin();

    /**
     * 改变actionBar的内容
     */
    void changeTitleText(int titleName);


}
