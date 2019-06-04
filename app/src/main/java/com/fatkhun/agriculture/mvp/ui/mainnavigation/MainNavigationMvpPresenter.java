package com.fatkhun.agriculture.mvp.ui.mainnavigation;

import com.fatkhun.agriculture.mvp.di.PerActivity;
import com.fatkhun.agriculture.mvp.ui.base.MvpPresenter;
import com.fatkhun.agriculture.mvp.ui.main.MainMvpView;

@PerActivity
public interface MainNavigationMvpPresenter<V extends MainNavigationMvpView> extends MvpPresenter<V> {

    void onSettingClick();

    void onLogoutClick();

    void getCheckRelay();

    void getRefreshRelay();

    String getDeviceId();

    String updateUserName();

    void updateRelay(String pumpOn, String autoPumpOn);

    String getUserId();
}
