package com.fatkhun.agriculture.mvp.ui.fragmentsdata;

import com.fatkhun.agriculture.mvp.data.network.model.AverageDataResponse;
import com.fatkhun.agriculture.mvp.ui.base.MvpView;

import java.util.List;

public interface DataFragmentMvpView extends MvpView {

    void setupAverageDataAll(List<AverageDataResponse> averageDataResponse);
}
