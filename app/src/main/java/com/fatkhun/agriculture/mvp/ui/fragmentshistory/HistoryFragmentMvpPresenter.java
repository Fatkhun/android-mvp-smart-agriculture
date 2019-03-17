package com.fatkhun.agriculture.mvp.ui.fragmentshistory;

import com.fatkhun.agriculture.mvp.ui.base.MvpPresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentsdata.DataFragmentMvpView;

public interface HistoryFragmentMvpPresenter<V extends HistoryFragmentMvpView>
        extends MvpPresenter<V> {

    void getDataAll();
}
