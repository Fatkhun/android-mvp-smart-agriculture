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

package com.fatkhun.agriculture.mvp.utils;

/**
 * Created by amitshekhar on 08/01/17.
 */

public final class AppConstants {

    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String BASE_URL_API = "http://168.63.232.214:8080/";

    public static final String DB_NAME = "agriculture_mvp.db";
    public static final String PREF_NAME = "agriculture_pref";

    public static final String NULL_INDEX = "-1L";

    public static final String SEED_DATABASE_OPTIONS = "seed/options.json";
    public static final String SEED_DATABASE_QUESTIONS = "seed/questions.json";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public static final String CHANNEL_ID = "channel_1";
    public static final String CHANNEL_NAME = "Channel Name";
    public static final String CHANNEL_DESCRIPTION = "www.smart.id";

    public static final String DEVICEID = "5c7ebb4267722562b4cc4395";
    public static final String ISONLINE = "ONLINE";

    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
