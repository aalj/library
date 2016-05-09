package com.bestsnail.view;

/**
 * Created by liang on 2016/4/29.
 * 首页Fragment的View
 */
public interface HomeFragmentView {
    /*
     *需要实现额功能有，
     * 1、图片轮播，以及图片的轮播不同图片不同的点击事件
     * 2、每个功能不同的页面跳转按钮
     */

    /**
     * 实现页面跳转
     */
    void setIntent(Class<?> cls);

    /**
     * 实现页面轮播功能
     */
    void lunBo();

    /**
     * 实现图片轮播不同图片不同点击事件
     */
    void onclickLunBo();


}
