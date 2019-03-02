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
import com.fatkhun.agriculture.mvp.di.ActivityContext;
import com.fatkhun.agriculture.mvp.di.PerActivity;
import com.fatkhun.agriculture.mvp.ui.about.AboutMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.about.AboutMvpView;
import com.fatkhun.agriculture.mvp.ui.about.AboutPresenter;
import com.fatkhun.agriculture.mvp.ui.dashboard.DashboardMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.dashboard.DashboardMvpView;
import com.fatkhun.agriculture.mvp.ui.dashboard.DashboardPresenter;
import com.fatkhun.agriculture.mvp.ui.feed.FeedMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.feed.FeedMvpView;
import com.fatkhun.agriculture.mvp.ui.feed.FeedPagerAdapter;
import com.fatkhun.agriculture.mvp.ui.feed.FeedPresenter;
import com.fatkhun.agriculture.mvp.ui.feed.blogs.BlogAdapter;
import com.fatkhun.agriculture.mvp.ui.feed.blogs.BlogMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.feed.blogs.BlogMvpView;
import com.fatkhun.agriculture.mvp.ui.feed.blogs.BlogPresenter;
import com.fatkhun.agriculture.mvp.ui.feed.opensource.OpenSourceAdapter;
import com.fatkhun.agriculture.mvp.ui.feed.opensource.OpenSourceMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.feed.opensource.OpenSourceMvpView;
import com.fatkhun.agriculture.mvp.ui.feed.opensource.OpenSourcePresenter;
import com.fatkhun.agriculture.mvp.ui.login.LoginMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.login.LoginMvpView;
import com.fatkhun.agriculture.mvp.ui.login.LoginPresenter;
import com.fatkhun.agriculture.mvp.ui.main.MainMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.main.MainMvpView;
import com.fatkhun.agriculture.mvp.ui.main.MainPresenter;
import com.fatkhun.agriculture.mvp.ui.main.rating.RatingDialogMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.main.rating.RatingDialogMvpView;
import com.fatkhun.agriculture.mvp.ui.main.rating.RatingDialogPresenter;
import com.fatkhun.agriculture.mvp.ui.register.RegisterMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.register.RegisterMvpView;
import com.fatkhun.agriculture.mvp.ui.register.RegisterPresenter;
import com.fatkhun.agriculture.mvp.ui.reminder.RemindMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.reminder.RemindMvpView;
import com.fatkhun.agriculture.mvp.ui.reminder.RemindPresenter;
import com.fatkhun.agriculture.mvp.ui.remindercrud.RemindCreateEditMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.remindercrud.RemindCreateEditMvpView;
import com.fatkhun.agriculture.mvp.ui.remindercrud.RemindCreateEditPresenter;
import com.fatkhun.agriculture.mvp.ui.remindpreference.RemindPreferenceMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.remindpreference.RemindPreferenceMvpView;
import com.fatkhun.agriculture.mvp.ui.remindpreference.RemindPreferencePresenter;
import com.fatkhun.agriculture.mvp.ui.remindview.RemindViewMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.remindview.RemindViewMvpView;
import com.fatkhun.agriculture.mvp.ui.remindview.RemindViewPresenter;
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
    DashboardMvpPresenter<DashboardMvpView> provideDashboardPresenter(DashboardPresenter<DashboardMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    RemindMvpPresenter<RemindMvpView> provideRemindPresenter(RemindPresenter<RemindMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    RemindCreateEditMvpPresenter<RemindCreateEditMvpView> provideRemindCreateEditPresenter(RemindCreateEditPresenter<RemindCreateEditMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    RemindPreferenceMvpPresenter<RemindPreferenceMvpView> provideRemindPreferencePresenter(RemindPreferencePresenter<RemindPreferenceMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    RemindViewMvpPresenter<RemindViewMvpView> provideRemindViewPresenter(RemindViewPresenter<RemindViewMvpView> presenter){
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
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    RatingDialogMvpPresenter<RatingDialogMvpView> provideRateUsPresenter(
            RatingDialogPresenter<RatingDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    FeedMvpPresenter<FeedMvpView> provideFeedPresenter(
            FeedPresenter<FeedMvpView> presenter) {
        return presenter;
    }

    @Provides
    OpenSourceMvpPresenter<OpenSourceMvpView> provideOpenSourcePresenter(
            OpenSourcePresenter<OpenSourceMvpView> presenter) {
        return presenter;
    }

    @Provides
    BlogMvpPresenter<BlogMvpView> provideBlogMvpPresenter(
            BlogPresenter<BlogMvpView> presenter) {
        return presenter;
    }

    @Provides
    FeedPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter() {
        return new OpenSourceAdapter(new ArrayList<OpenSourceResponse.Repo>());
    }

    @Provides
    BlogAdapter provideBlogAdapter() {
        return new BlogAdapter(new ArrayList<BlogResponse.Blog>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }
}
