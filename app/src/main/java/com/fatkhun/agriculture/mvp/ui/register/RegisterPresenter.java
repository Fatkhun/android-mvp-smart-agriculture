package com.fatkhun.agriculture.mvp.ui.register;

import com.androidnetworking.error.ANError;
import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.data.network.model.LoginResponse;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.ui.splash.SplashMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.splash.SplashMvpView;
import com.fatkhun.agriculture.mvp.utils.CommonUtils;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter<V extends RegisterMvpView> extends BasePresenter<V>
        implements RegisterMvpPresenter<V> {

    @Inject
    public RegisterPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void registerUser(String name, String email, String password) {
        //validate email and password
        if (name == null || name.isEmpty()){
            getMvpView().onError("Please provide a non empty name");
            return;
        }
        if (email == null || email.isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.invalid_email);
            return;
        }
        if (password == null || password.isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .registerUser(name, email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    if (loginResponse.getStatus() == false){
                        getMvpView().showMessage(loginResponse.getMessage());
                    }else {
                        getMvpView().openMainActivity();
                        getMvpView().showMessage(loginResponse.getMessage());
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
}
