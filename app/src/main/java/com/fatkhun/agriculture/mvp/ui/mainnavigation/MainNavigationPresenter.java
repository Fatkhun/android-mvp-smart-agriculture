package com.fatkhun.agriculture.mvp.ui.mainnavigation;

import com.androidnetworking.error.ANError;
import com.fatkhun.agriculture.mvp.data.DataManager;
import com.fatkhun.agriculture.mvp.data.network.model.LogoutResponse;
import com.fatkhun.agriculture.mvp.ui.base.BasePresenter;
import com.fatkhun.agriculture.mvp.ui.main.MainMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.main.MainMvpView;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

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

        getCompositeDisposable().add(getDataManager().doLogoutApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LogoutResponse>() {
                    @Override
                    public void accept(LogoutResponse response) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getDataManager().setUserAsLoggedOut();
                        getMvpView().hideLoading();
                        getMvpView().openLoginActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            baseHandleError(anError);
                        }
                    }
                }));
    }
}
