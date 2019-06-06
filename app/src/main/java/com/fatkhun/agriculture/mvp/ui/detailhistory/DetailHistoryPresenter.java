package com.fatkhun.agriculture.mvp.ui.detailhistory;

import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.ui.mainnavigation.MainNavigationMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.mainnavigation.MainNavigationMvpView;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DetailHistoryPresenter<V extends DetailHistoryMvpView> extends BasePresenter<V>
        implements DetailHistoryMvpPresenter<V> {

    @Inject
    public DetailHistoryPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
