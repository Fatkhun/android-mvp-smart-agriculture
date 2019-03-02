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

package com.fatkhun.agriculture.mvp.di.component;

import com.fatkhun.agriculture.mvp.di.PerActivity;
import com.fatkhun.agriculture.mvp.di.module.ActivityModule;
import com.fatkhun.agriculture.mvp.ui.about.AboutFragment;
import com.fatkhun.agriculture.mvp.ui.feed.FeedActivity;
import com.fatkhun.agriculture.mvp.ui.feed.blogs.BlogFragment;
import com.fatkhun.agriculture.mvp.ui.feed.opensource.OpenSourceFragment;
import com.fatkhun.agriculture.mvp.ui.login.LoginActivity;
import com.fatkhun.agriculture.mvp.ui.main.MainActivity;
import com.fatkhun.agriculture.mvp.ui.main.rating.RateUsDialog;
import com.fatkhun.agriculture.mvp.ui.register.RegisterActivity;
import com.fatkhun.agriculture.mvp.ui.reminder.RemindActivity;
import com.fatkhun.agriculture.mvp.ui.remindercrud.RemindCreateEditActivity;
import com.fatkhun.agriculture.mvp.ui.remindpreference.RemindPreferenceActivity;
import com.fatkhun.agriculture.mvp.ui.remindview.RemindViewActivity;
import com.fatkhun.agriculture.mvp.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by janisharali on 27/01/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    void inject(FeedActivity activity);

    void inject(AboutFragment fragment);

    void inject(OpenSourceFragment fragment);

    void inject(BlogFragment fragment);

    void inject(RateUsDialog dialog);

    void inject(RegisterActivity activity);

    void inject(RemindActivity activity);

    void inject(RemindCreateEditActivity activity);

    void inject(RemindViewActivity activity);

    void inject(RemindPreferenceActivity activity);
}
