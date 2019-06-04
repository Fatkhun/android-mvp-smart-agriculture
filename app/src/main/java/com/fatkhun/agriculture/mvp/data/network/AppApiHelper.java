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

package com.fatkhun.agriculture.mvp.data.network;

import com.fatkhun.agriculture.mvp.data.network.model.AverageDataResponse;
import com.fatkhun.agriculture.mvp.data.network.model.BlogResponse;
import com.fatkhun.agriculture.mvp.data.network.model.DataResponse;
import com.fatkhun.agriculture.mvp.data.network.model.LoginResponse;
import com.fatkhun.agriculture.mvp.data.network.model.LogoutResponse;
import com.fatkhun.agriculture.mvp.data.network.model.OpenSourceResponse;
import com.fatkhun.agriculture.mvp.data.network.model.RelayResponse;
import com.fatkhun.agriculture.mvp.data.prefs.PreferencesHelper;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by janisharali on 28/01/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;
    private Map<String, String> mHeader;

    @Inject
    PreferencesHelper preferencesHelper;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<LoginResponse> registerUser(String name, String email, String password) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_REGISTER_USER)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter("name",name)
                .addBodyParameter("email",email)
                .addBodyParameter("password",password)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<LoginResponse> loginUser(String email, String password) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN_USER)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter("email",email)
                .addBodyParameter("password",password)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<List<DataResponse>> getDataAll(int page) {
        return  Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_DATA_ALL + page)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectListSingle(DataResponse.class);
    }

    @Override
    public Single<List<AverageDataResponse>> getAverageDataAll() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_AVERAGE_DATA_ALL)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectListSingle(AverageDataResponse.class);
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall(String userId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_LOGOUT_USER + userId)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(LogoutResponse.class);
    }

    @Override
    public Single<RelayResponse> updateRelay(String deviceId, String pumpOn, String autoPumpOn) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_RELAY_ON_OFF + deviceId)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter("pumpOn", pumpOn)
                .addBodyParameter("autoPumpOn", autoPumpOn)
                .build()
                .getObjectSingle(RelayResponse.class);
    }

    @Override
    public Single<RelayResponse> updateRelayPump(String deviceId, String pumpOn) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_RELAY_PUMP + deviceId)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter("pumpOn", pumpOn)
                .build()
                .getObjectSingle(RelayResponse.class);
    }

    @Override
    public Single<RelayResponse> updateRelayAutoPump(String deviceId, String autoPumpOn) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_RELAY_AUTOPUMP + deviceId)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter("autoPumpOn", autoPumpOn)
                .build()
                .getObjectSingle(RelayResponse.class);
    }

    @Override
    public Single<RelayResponse> getRelay(String deviceId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_RELAY_CONFIG + deviceId)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(RelayResponse.class);
    }

    @Override
    public Single<BlogResponse> getBlogApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(BlogResponse.class);
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(OpenSourceResponse.class);
    }

    private Map<String, String> getHeader(){
        if (mHeader == null){
            mHeader = new HashMap<>();
            mHeader.put("x-access-token", mApiHeader.getProtectedApiHeader().getAccessToken());
        }
        return mHeader;
    }
}

