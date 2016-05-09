package com.bestsnail.view;

/**
 * 作者：liang on 2016/4/29 18:31
 * 加载个人中内容及其页面
 */
public interface PersonFragmentView {


    /**
     * 初始化页面，加载GradView
     */
    void initView();

    /**
     * 跳转到对应的功能页
     */
    void intentActivity(Class<?> cls);

}
