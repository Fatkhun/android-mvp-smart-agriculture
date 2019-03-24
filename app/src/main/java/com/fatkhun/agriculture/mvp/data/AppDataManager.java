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

package com.fatkhun.agriculture.mvp.data;


import android.content.Context;

import com.fatkhun.agriculture.mvp.data.network.model.AverageDataResponse;
import com.fatkhun.agriculture.mvp.data.network.model.DataResponse;
import com.fatkhun.agriculture.mvp.data.network.ApiHeader;
import com.fatkhun.agriculture.mvp.data.network.ApiHelper;
import com.fatkhun.agriculture.mvp.data.network.model.BlogResponse;
import com.fatkhun.agriculture.mvp.data.network.model.LoginResponse;
import com.fatkhun.agriculture.mvp.data.network.model.LogoutResponse;
import com.fatkhun.agriculture.mvp.data.network.model.OpenSourceResponse;
import com.fatkhun.agriculture.mvp.data.prefs.PreferencesHelper;
import com.fatkhun.agriculture.mvp.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by janisharali on 27/01/17.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Single<LoginResponse> registerUser(String name, String email, String password) {
        return mApiHelper.registerUser(name, email, password);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public Single<LoginResponse> loginUser(String email, String password) {
        return mApiHelper.loginUser(email, password);
    }

    @Override
    public Single<List<DataResponse>> getDataAll(int page) {
        return mApiHelper.getDataAll(page);
    }

    @Override
    public Single<List<AverageDataResponse>> getAverageDataAll() {
        return mApiHelper.getAverageDataAll();
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall(String userId) {
        return mApiHelper.doLogoutApiCall(userId);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public void updateApiHeader(String userId, String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode,
            String userName,
            String email) {

        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(userName);
        setCurrentUserEmail(email);

        updateApiHeader(userId, accessToken);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null);
    }

    @Override
    public Single<BlogResponse> getBlogApiCall() {
        return mApiHelper.getBlogApiCall();
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return mApiHelper.getOpenSourceApiCall();
    }
}
