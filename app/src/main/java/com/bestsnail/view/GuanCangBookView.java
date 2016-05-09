package com.bestsnail.view;

import com.bestsnail.bean.BookTable;

import java.util.List;

/**
 * 作者：liang on 2016/5/4 16:15
 */
public interface GuanCangBookView {

    int ALLSOUSUO = 0;
    int ALLTIMING = 1;
    int ALLZUOZHE = 2;
    int ALLZHUTICI = 3;

    void showReMenBook(List<BookTable> list);


}
