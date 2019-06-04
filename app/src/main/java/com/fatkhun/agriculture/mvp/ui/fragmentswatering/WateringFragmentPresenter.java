package com.fatkhun.agriculture.mvp.ui.fragmentswatering;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentshistory.HistoryFragmentMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentshistory.HistoryFragmentMvpView;
import com.fatkhun.agriculture.mvp.utils.AppConstants;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WateringFragmentPresenter<V extends WateringFragmentMvpView> extends BasePresenter<V>
        implements WateringFragmentMvpPresenter<V> {

    @Inject
    public WateringFragmentPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void updateRelay(String pumpOn, String autoPumpOn) {
        getMvpView().showLoading();
        String deviceId = getDeviceId();
        getCompositeDisposable().add(getDataManager()
                .updateRelay(deviceId, pumpOn, autoPumpOn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(updateRelay ->  {
                    if (!isViewAttached()){
                        return;
                    }
                    if (updateRelay != null && !updateRelay.equals(0)){
                        getMvpView().validateRelayState(deviceId);
                        getMvpView().setupUpdateRelay(updateRelay);
                    }
                    getMvpView().hideLoading();
                    Log.d("Debug",updateRelay.toString());
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

    @Override
    public void updateRelayPump(String pumpOn) {
        getMvpView().showLoading();
        String deviceId = getDeviceId();
        getCompositeDisposable().add(getDataManager()
                .updateRelayPump(deviceId, pumpOn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(updateRelay ->  {
                    if (!isViewAttached()){
                        return;
                    }
                    if (updateRelay != null && !updateRelay.equals(0)){
                        getMvpView().validateRelayState(deviceId);
                        getMvpView().setupUpdateRelayPump(updateRelay);
                    }
                    getMvpView().hideLoading();
                    Log.d("Debug",updateRelay.toString());
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

    @Override
    public void updateRelayAutoPump(String autoPumpOn) {
        getMvpView().showLoading();
        String deviceId = getDeviceId();
        getCompositeDisposable().add(getDataManager()
                .updateRelayAutoPump(deviceId, autoPumpOn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(updateRelay ->  {
                    if (!isViewAttached()){
                        return;
                    }
                    if (updateRelay != null && !updateRelay.equals(0)){
                        getMvpView().validateRelayState(deviceId);
                        getMvpView().setupUpdateRelayAutoPump(updateRelay);
                    }
                    getMvpView().hideLoading();
                    Log.d("Debug",updateRelay.toString());
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

    @Override
    public void getRelay() {
        getMvpView().showLoading();
        String deviceId = getDeviceId();
        getCompositeDisposable().add(getDataManager()
                .getRelay(deviceId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getRelay ->  {
                    if (!isViewAttached()){
                        return;
                    }
                    if (getRelay != null && !getRelay.equals(0)){
                        if (getRelay.getisPumpOn().equals("ON")){
                            getMvpView().setRelayState(PumpState.PUMP_OFF);
                        }else{
                            getMvpView().setRelayState(PumpState.PUMP_ON);
                        }
                        getMvpView().getRelays(getRelay);
                    }else {
                        getMvpView().validateRelayState(deviceId);
                    }
                    getMvpView().hideLoading();
                    Log.d("Debug",getRelay.toString());
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

    @Override
    public String getDeviceId() {
        return AppConstants.DEVICEID;
    }
}
