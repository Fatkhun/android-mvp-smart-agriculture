package com.fatkhun.agriculture.mvp.ui.remindpreference;

import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class RemindPreferencePresenter<V extends RemindPreferenceMvpView> extends BasePresenter<V>
        implements RemindPreferenceMvpPresenter<V> {

    @Inject
    public RemindPreferencePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
