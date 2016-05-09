package com.bestsnail.presenter;

import com.bestsnail.bean.BookTable;
import com.bestsnail.model.GuanCangReMenBookModel;
import com.bestsnail.view.GuanCangBookView;

import java.util.List;

/**
 * 作者：liang on 2016/5/4 16:07
 */
public class GuanCangBookPL implements IRemenTuShuPresenter<BookTable>, Presenter<GuanCangBookView> {
    private GuanCangBookView view = null;
    private GuanCangReMenBookModel _mGuanCangReMenBookModel = null;

    public GuanCangBookPL(GuanCangBookView view) {
        attachView(view);
        _mGuanCangReMenBookModel = new GuanCangReMenBookModel(this);
    }

    @Override
    public void loadDataSuccess(List<BookTable> bookName) {
        view.showReMenBook(bookName);

    }

    @Override
    public void loadDataFailure() {

    }

    @Override
    public void attachView(GuanCangBookView view) {
        this.view = view;

    }

    @Override
    public void detachView() {

    }

    @Override
    public void loadData() {
        _mGuanCangReMenBookModel.getGuanCangReMenBook();
    }


}
