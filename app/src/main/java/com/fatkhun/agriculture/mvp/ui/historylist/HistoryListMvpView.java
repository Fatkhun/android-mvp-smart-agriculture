package com.fatkhun.agriculture.mvp.ui.historylist;

import com.fatkhun.agriculture.mvp.data.network.model.SensorResponse;
import com.fatkhun.agriculture.mvp.ui.base.MvpView;

import java.util.List;

public interface HistoryListMvpView extends MvpView {

    void updateData(List<SensorResponse> dataResponseList);

}
