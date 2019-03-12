package com.fatkhun.agriculture.mvp.ui.historylist;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.data.network.model.DataResponse;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.ui.base.MvpView;
import com.fatkhun.agriculture.mvp.ui.feed.FeedMvpPresenter;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HistoryListPresenter<V extends HistoryListMvpView> extends BasePresenter<V> implements
        HistoryListMvpPresenter<V> {

    @Inject
    public HistoryListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getDataAll() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getDataAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataResponseList ->  {
                    if (!isViewAttached()){
                        return;
                    }
                    if (dataResponseList != null && dataResponseList.size() != 0 ){
                        getMvpView().updateData(dataResponseList);
                    }
                    getMvpView().hideLoading();
                    Log.d("Debug",dataResponseList.toString());
                }, throwable ->  {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    // handle the error here
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        baseHandleError(anError);
                    }
                }));
    }
}
