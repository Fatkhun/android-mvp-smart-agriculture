package com.fatkhun.agriculture.mvp.ui.fragmentswatering;

import com.fatkhun.agriculture.mvp.data.network.model.RelayResponse;
import com.fatkhun.agriculture.mvp.ui.base.MvpView;

public interface WateringFragmentMvpView extends MvpView {
    void setupUpdateRelay(RelayResponse relayResponse);
    void getRelays(RelayResponse relayResponse);
    void validateRelayState(String deviceId);
    void setRelayState(PumpState pumpState);
}
