package com.fatkhun.agriculture.mvp.ui.fragmentsdata;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DataFragmentPresenter<V extends DataFragmentMvpView> extends BasePresenter<V>
        implements DataFragmentMvpPresenter<V> {

    @Inject
    public DataFragmentPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getAverageDataAll() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getAverageDataAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(averageData ->  {
                    if (!isViewAttached()){
                        return;
                    }
                    if (averageData != null && averageData.size() != 0){
                        getMvpView().setupAverageDataAll(averageData);
                    }
                    getMvpView().hideLoading();
                    Log.d("Debug",averageData.toString());
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
