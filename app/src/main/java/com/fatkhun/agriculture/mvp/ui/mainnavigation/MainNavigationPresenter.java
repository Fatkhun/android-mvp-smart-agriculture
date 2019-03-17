package com.fatkhun.agriculture.mvp.ui.mainnavigation;

import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.ui.main.MainMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.main.MainMvpView;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainNavigationPresenter<V extends MainNavigationMvpView> extends BasePresenter<V>
        implements MainNavigationMvpPresenter<V> {

    @Inject
    public MainNavigationPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
