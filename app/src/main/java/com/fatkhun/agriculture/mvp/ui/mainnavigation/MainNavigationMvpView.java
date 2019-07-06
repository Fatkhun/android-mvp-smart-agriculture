package com.fatkhun.agriculture.mvp.ui.mainnavigation;

import com.fatkhun.agriculture.mvp.data.network.model.RelayResponse;
import com.fatkhun.agriculture.mvp.ui.base.MvpView;
import com.fatkhun.agriculture.mvp.ui.fragmentswatering.PumpState;

public interface MainNavigationMvpView extends MvpView {

    void openLoginActivity();

    void openAboutFragment();

    void getRelays(RelayResponse relayResponse);

    void setRelayState(PumpState pumpState);

    void setupUpdateRelay(RelayResponse relayResponse);
}
