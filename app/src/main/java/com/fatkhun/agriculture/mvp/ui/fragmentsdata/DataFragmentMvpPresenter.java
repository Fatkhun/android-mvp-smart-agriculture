package com.fatkhun.agriculture.mvp.ui.fragmentsdata;

import com.fatkhun.agriculture.mvp.ui.base.MvpPresenter;

public interface DataFragmentMvpPresenter<V extends DataFragmentMvpView>
        extends MvpPresenter<V> {

    void getAverageDataAll();
}
