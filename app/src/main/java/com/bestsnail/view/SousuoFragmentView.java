package com.bestsnail.view;

import com.bestsnail.bean.BookTable;

import java.util.List;

/**
 * 作者：liang on 2016/4/30 18:03
 * 使用在快速搜索上面
 */
public interface SousuoFragmentView {
    /*
     *1、处理按钮店家查询结果
     * 2、加载服务器返回的热门搜索的书记类型
     * 3、处理点击加载热门图书文字的相关事件
     *
     */

    /**
     * 处理按钮点击查询输入框的关键字，
     */
    void buttonClick();


    /**
     * 加载热门图书到TextView上，
     */
    void getRemenTuShu(List<BookTable> bookName);

    /**
     * 设置不同数目的点击事件s
     */
    void setTextViewClick();


}
