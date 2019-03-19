package com.fatkhun.agriculture.mvp.ui.mainnavigation;

import com.androidnetworking.error.ANError;
import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.data.network.model.LogoutResponse;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.ui.main.MainMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.main.MainMvpView;
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
    public void onSettingClick() {
        getMvpView().openSettingActivity();
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

                    getDataManager().setUserAsLoggedOut();
                    getMvpView().hideLoading();
                    getMvpView().openLoginActivity();
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
    public String getUserId() {
        return getDataManager().getCurrentUserId();
    }
}
