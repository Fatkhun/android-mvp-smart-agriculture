package com.fatkhun.agriculture.mvp.ui.fragmentshistory;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentsdata.DataFragmentMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentsdata.DataFragmentMvpView;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HistoryFragmentPresenter<V extends HistoryFragmentMvpView> extends BasePresenter<V>
        implements HistoryFragmentMvpPresenter<V> {

    @Inject
    public HistoryFragmentPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getDataAll(int page) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getDataAll(page)
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
