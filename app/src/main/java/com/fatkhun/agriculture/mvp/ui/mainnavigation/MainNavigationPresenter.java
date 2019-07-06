package com.fatkhun.agriculture.mvp.ui.mainnavigation;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.data.network.model.LogoutResponse;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentswatering.PumpState;
import com.fatkhun.agriculture.mvp.utils.AppConstants;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainNavigationPresenter<V extends MainNavigationMvpView> extends BasePresenter<V>
        implements MainNavigationMvpPresenter<V> {

    @Inject
    public MainNavigationPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onLogoutClick() {
        getMvpView().showLoading();
        String userId = getUserId();
        getCompositeDisposable().add(getDataManager()
                .doLogoutApiCall(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(logoutResponse ->  {
                    if (!isViewAttached()) {
                        return;
                    }
                    if (!logoutResponse.getStatus()){
                        getMvpView().showMessage(logoutResponse.getMessage());
                    }else {
                        getDataManager().setUserAsLoggedOut();
                        getMvpView().hideLoading();
                        getMvpView().openLoginActivity();
                        getMvpView().showMessage(logoutResponse.getMessage());
                    }
                }, throwable ->  {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();

                    // handle the login error here
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        baseHandleError(anError);
                    }
                }));
    }

    @Override
    public void getCheckRelay() {
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
                            getMvpView().setRelayState(PumpState.PUMP_ON);
                        }else{
                            getMvpView().setRelayState(PumpState.PUMP_OFF);
                        }
                        getMvpView().getRelays(getRelay);
                    }
                    Log.d("Debug",getRelay.toString());
                }, throwable ->  {
                    if (!isViewAttached()) {
                        return;
                    }

                    // handle the error here
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        baseHandleError(anError);
                    }
                }));
    }

    @Override
    public void getRefreshRelay() {
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
                        getMvpView().getRelays(getRelay);
                    }
                    Log.d("Debug",getRelay.toString());
                }, throwable ->  {
                    if (!isViewAttached()) {
                        return;
                    }

                    // handle the error here
                    if (throwable instanceof ANError) {
                        ANError anError = (ANError) throwable;
                        baseHandleError(anError);
                    }
                }));
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
                        getMvpView().setupUpdateRelay(updateRelay);
                    }
                    getMvpView().hideLoading();
                    getMvpView().showMessage("Relay Off");
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
    public String getDeviceId() {
        return AppConstants.DEVICEID;
    }

    @Override
    public String updateUserName() {
        return getDataManager().getCurrentUserName();
    }

    @Override
    public String getUserId() {
        return getDataManager().getCurrentUserId();
    }
}
