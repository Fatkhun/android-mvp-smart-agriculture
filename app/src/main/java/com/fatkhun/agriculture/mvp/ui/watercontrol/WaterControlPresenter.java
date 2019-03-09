package com.fatkhun.agriculture.mvp.ui.watercontrol;

import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class WaterControlPresenter<V extends WaterControlMvpView> extends BasePresenter<V>
        implements WaterControlMvpPresenter<V> {

    @Inject
    public WaterControlPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
