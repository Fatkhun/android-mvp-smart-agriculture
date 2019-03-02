package com.fatkhun.agriculture.mvp.ui.remindpreference;

import com.fatkhun.agriculture.mvp.di.PerActivity;
import com.fatkhun.agriculture.mvp.ui.base.MvpPresenter;
import com.fatkhun.agriculture.mvp.ui.remindview.RemindViewMvpView;

@PerActivity
public interface RemindPreferenceMvpPresenter<V extends RemindPreferenceMvpView> extends MvpPresenter<V> {
}
