package com.fatkhun.agriculture.mvp.ui.remindercrud;

import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.ui.reminder.RemindMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.reminder.RemindMvpView;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class RemindCreateEditPresenter<V extends RemindCreateEditMvpView> extends BasePresenter<V>
        implements RemindCreateEditMvpPresenter<V> {

    @Inject
    public RemindCreateEditPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
