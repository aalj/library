package com.bestsnail.presenter;

import com.bestsnail.bean.BookTable;
import com.bestsnail.model.ReMenTuShuModel;
import com.bestsnail.view.SousuoFragmentView;

import java.util.List;

/**
 * 作者：liang on 2016/4/30 19:39
 */
public class ReMenTuShu implements IRemenTuShuPresenter<BookTable>, Presenter<SousuoFragmentView> {

    private SousuoFragmentView sousuoFragmentView = null;
    private ReMenTuShuModel reMenTuShuModel = null;


    public ReMenTuShu(SousuoFragmentView sousuoFragmentView) {
        attachView(sousuoFragmentView);
        this.reMenTuShuModel = new ReMenTuShuModel(this);

    }


    @Override
    public void attachView(SousuoFragmentView view) {
        this.sousuoFragmentView = view;

    }

    @Override
    public void detachView() {
        this.sousuoFragmentView = null;

    }

    /**
     * 通过网络加载数据
     */
    public void loadData() {

        reMenTuShuModel.getRemenTushu();


    }


    @Override
    public void loadDataSuccess(List<BookTable> bookName) {

        sousuoFragmentView.getRemenTuShu(bookName);
//        saveSharePre(bookName);

    }

    @Override
    public void loadDataFailure() {
        sousuoFragmentView.getRemenTuShu(null);
    }


    /**
     * 对数据进行本地化处理
     * @param bookName
     */
//   protected abstract void saveSharePre(List<BookTable> bookName);
}
