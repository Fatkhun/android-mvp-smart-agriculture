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
import com.fatkhun.agriculture.mvp.data.network.model.LoginResponse;
import com.fatkhun.agriculture.mvp.data.network.model.LogoutResponse;
import com.fatkhun.agriculture.mvp.data.network.model.OpenSourceResponse;
import com.fatkhun.agriculture.mvp.data.network.model.DataResponse;
import com.fatkhun.agriculture.mvp.data.network.model.RelayResponse;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by janisharali on 27/01/17.
 */

public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<LoginResponse> registerUser(String name, String email, String password);

    Single<LoginResponse> loginUser(String email, String password);

    Single<List<DataResponse>> getDataAll(int page);

    Single<List<AverageDataResponse>> getAverageDataAll();

    Single<LogoutResponse> doLogoutApiCall(String userId);

    Single<RelayResponse> updateRelay(String deviceId, String pumpOn, String autoPumpOn);

    Single<RelayResponse> getRelay(String deviceId);

    Single<BlogResponse> getBlogApiCall();

    Single<OpenSourceResponse> getOpenSourceApiCall();
}
