package com.fatkhun.agriculture.mvp.ui.remindercrud;

import com.fatkhun.agriculture.mvp.di.PerActivity;
import com.fatkhun.agriculture.mvp.ui.base.MvpPresenter;
import com.fatkhun.agriculture.mvp.ui.reminder.RemindMvpView;

@PerActivity
public interface RemindCreateEditMvpPresenter<V extends RemindCreateEditMvpView> extends MvpPresenter<V> {
}
