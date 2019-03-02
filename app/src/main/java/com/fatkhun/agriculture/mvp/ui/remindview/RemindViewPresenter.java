package com.fatkhun.agriculture.mvp.ui.remindview;

import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class RemindViewPresenter<V extends RemindViewMvpView> extends BasePresenter<V>
        implements RemindViewMvpPresenter<V> {

    @Inject
    public RemindViewPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
