package com.fatkhun.agriculture.mvp.ui.dashboard;

import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.ui.register.RegisterMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.register.RegisterMvpView;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DashboardPresenter<V extends DashboardMvpView> extends BasePresenter<V>
        implements DashboardMvpPresenter<V> {

    @Inject
    public DashboardPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
