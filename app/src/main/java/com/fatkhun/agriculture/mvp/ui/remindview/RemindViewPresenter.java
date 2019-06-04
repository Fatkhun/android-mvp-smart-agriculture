package com.fatkhun.agriculture.mvp.ui.remindview;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.utils.AppConstants;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RemindViewPresenter<V extends RemindViewMvpView> extends BasePresenter<V>
        implements RemindViewMvpPresenter<V> {

    @Inject
    public RemindViewPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
