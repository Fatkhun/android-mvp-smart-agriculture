package com.fatkhun.agriculture.mvp.ui.detailhistory;

import com.fatkhun.agriculture.mvp.data.network.model.DataResponse;
import com.fatkhun.agriculture.mvp.ui.base.MvpView;

public interface DetailHistoryMvpView extends MvpView {
    void setFieldHistory(DataResponse dataResponse);
}
