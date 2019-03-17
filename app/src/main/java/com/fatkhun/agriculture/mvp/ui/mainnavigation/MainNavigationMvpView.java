package com.fatkhun.agriculture.mvp.ui.mainnavigation;

import com.fatkhun.agriculture.mvp.ui.base.MvpView;

public interface MainNavigationMvpView extends MvpView {

    void openLoginActivity();

    void updateUserName(String currentUserName);

    void updateUserEmail(String currentUserEmail);

    void openSettingActivity();
}
