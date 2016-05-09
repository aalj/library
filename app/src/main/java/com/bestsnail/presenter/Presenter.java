package com.bestsnail.presenter;

/**
 * 作者：liang on 2016/4/30 22:39
 * 作为一个总的控制器
 */
public interface Presenter<V> {

    //连接View（加载View）
    void attachView(V view);

    //分离View
    void detachView();


}
