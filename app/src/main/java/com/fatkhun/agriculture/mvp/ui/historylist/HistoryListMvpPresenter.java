package com.fatkhun.agriculture.mvp.ui.historylist;

import com.fatkhun.agriculture.mvp.di.PerActivity;
import com.fatkhun.agriculture.mvp.ui.base.MvpPresenter;
import com.fatkhun.agriculture.mvp.ui.base.MvpView;

@PerActivity
public interface HistoryListMvpPresenter<V extends HistoryListMvpView> extends MvpPresenter<V> {

    void getDataAll();
}
