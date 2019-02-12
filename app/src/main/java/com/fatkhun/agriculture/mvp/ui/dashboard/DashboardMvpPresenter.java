package com.fatkhun.agriculture.mvp.ui.dashboard;

import com.fatkhun.agriculture.mvp.di.PerActivity;
import com.fatkhun.agriculture.mvp.ui.base.MvpPresenter;
import com.fatkhun.agriculture.mvp.ui.register.RegisterMvpView;

@PerActivity
public interface DashboardMvpPresenter<V extends DashboardMvpView> extends MvpPresenter<V> {
}
