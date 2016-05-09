package com.bestsnail.presenter;

import java.util.List;

/**
 * 作者：liang on 2016/4/30 19:35
 */
public interface IRemenTuShuPresenter<T> {
    /*
     *访问网络得到服务器上搜集的热门图书，
     */

    //访问网络成功
    void loadDataSuccess(List<T> bookName);

    //访问网络失败
    void loadDataFailure();

    /**
     * 加载数据
     */
    void loadData();


}
