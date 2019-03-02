package com.fatkhun.agriculture.mvp.ui.reminder;

import com.fatkhun.agriculture.mvp.di.PerActivity;
import com.fatkhun.agriculture.mvp.ui.base.MvpPresenter;

@PerActivity
public interface RemindMvpPresenter<V extends RemindMvpView> extends MvpPresenter<V> {
}
