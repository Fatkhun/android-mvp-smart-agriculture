package com.fatkhun.agriculture.mvp.ui.detailhistory;

import com.fatkhun.agriculture.mvp.di.PerActivity;
import com.fatkhun.agriculture.mvp.ui.base.MvpPresenter;
import com.fatkhun.agriculture.mvp.ui.mainnavigation.MainNavigationMvpView;

@PerActivity
public interface DetailHistoryMvpPresenter<V extends DetailHistoryMvpView> extends MvpPresenter<V> {
}
