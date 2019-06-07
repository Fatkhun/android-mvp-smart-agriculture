/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.fatkhun.agriculture.mvp.di.module;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.fatkhun.agriculture.mvp.data.network.model.BlogResponse;
import com.fatkhun.agriculture.mvp.data.network.model.OpenSourceResponse;
import com.fatkhun.agriculture.mvp.data.network.model.DataResponse;
import com.fatkhun.agriculture.mvp.di.ActivityContext;
import com.fatkhun.agriculture.mvp.di.PerActivity;
import com.fatkhun.agriculture.mvp.ui.about.AboutMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.about.AboutMvpView;
import com.fatkhun.agriculture.mvp.ui.about.AboutPresenter;
import com.fatkhun.agriculture.mvp.ui.detailhistory.DetailHistoryMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.detailhistory.DetailHistoryMvpView;
import com.fatkhun.agriculture.mvp.ui.detailhistory.DetailHistoryPresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentsdata.DataFragmentMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentsdata.DataFragmentMvpView;
import com.fatkhun.agriculture.mvp.ui.fragmentsdata.DataFragmentPresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentshistory.HistoryFragmentAdapter;
import com.fatkhun.agriculture.mvp.ui.fragmentshistory.HistoryFragmentMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentshistory.HistoryFragmentMvpView;
import com.fatkhun.agriculture.mvp.ui.fragmentshistory.HistoryFragmentPresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentswatering.WateringFragmentMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentswatering.WateringFragmentMvpView;
import com.fatkhun.agriculture.mvp.ui.fragmentswatering.WateringFragmentPresenter;
import com.fatkhun.agriculture.mvp.ui.mainnavigation.MainNavigationMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.mainnavigation.MainNavigationMvpView;
import com.fatkhun.agriculture.mvp.ui.mainnavigation.MainNavigationPresenter;
import com.fatkhun.agriculture.mvp.ui.login.LoginMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.login.LoginMvpView;
import com.fatkhun.agriculture.mvp.ui.login.LoginPresenter;
import com.fatkhun.agriculture.mvp.ui.register.RegisterMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.register.RegisterMvpView;
import com.fatkhun.agriculture.mvp.ui.register.RegisterPresenter;
import com.fatkhun.agriculture.mvp.ui.splash.SplashMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.splash.SplashMvpView;
import com.fatkhun.agriculture.mvp.ui.splash.SplashPresenter;
import com.fatkhun.agriculture.mvp.utils.rx.AppSchedulerProvider;
import com.fatkhun.agriculture.mvp.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by janisharali on 27/01/17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;
    Context context;
    Cursor cursor;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    RegisterMvpPresenter<RegisterMvpView> provideRegisterPresenter(RegisterPresenter<RegisterMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    AboutMvpPresenter<AboutMvpView> provideAboutPresenter(
            AboutPresenter<AboutMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainNavigationMvpPresenter<MainNavigationMvpView> provideMainNavigationPresenter(MainNavigationPresenter<MainNavigationMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    DetailHistoryMvpPresenter<DetailHistoryMvpView> provideDetailHistoryPresenter(DetailHistoryPresenter<DetailHistoryMvpView> presenter){
        return presenter;
    }

    @Provides
    DataFragmentMvpPresenter<DataFragmentMvpView> provideDataFragmentPresenter(DataFragmentPresenter<DataFragmentMvpView> presenter){
        return presenter;
    }

    @Provides
    HistoryFragmentMvpPresenter<HistoryFragmentMvpView> provideHistoryFragmentPresenter(HistoryFragmentPresenter<HistoryFragmentMvpView> presenter){
        return presenter;
    }

    @Provides
    WateringFragmentMvpPresenter<WateringFragmentMvpView> provideWaterFragmentPresenter(WateringFragmentPresenter<WateringFragmentMvpView> presenter){
        return presenter;
    }

    @Provides
    HistoryFragmentAdapter provideHistoryFragmentAdapter(){
        return new HistoryFragmentAdapter(new ArrayList<>(), context);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
