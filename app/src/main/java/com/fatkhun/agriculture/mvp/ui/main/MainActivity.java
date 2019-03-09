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

package com.fatkhun.agriculture.mvp.ui.main;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;


import com.dinuscxj.progressbar.CircleProgressBar;
import com.fatkhun.agriculture.mvp.BuildConfig;
import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.data.db.model.Question;
import com.fatkhun.agriculture.mvp.ui.about.AboutFragment;
import com.fatkhun.agriculture.mvp.ui.base.BaseActivity;
import com.fatkhun.agriculture.mvp.ui.custom.RoundedImageView;
import com.fatkhun.agriculture.mvp.ui.feed.FeedActivity;
import com.fatkhun.agriculture.mvp.ui.login.LoginActivity;
import com.fatkhun.agriculture.mvp.ui.main.rating.RateUsDialog;
import com.fatkhun.agriculture.mvp.ui.reminder.RemindActivity;
import com.fatkhun.agriculture.mvp.ui.remindpreference.RemindPreferenceActivity;
import com.fatkhun.agriculture.mvp.ui.watercontrol.WaterControlActivity;
import com.fatkhun.agriculture.mvp.utils.ScreenUtils;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by janisharali on 27/01/17.
 */

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_view)
    DrawerLayout mDrawer;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.tv_app_version)
    TextView mAppVersionTextView;

    @BindView(R.id.cp_humidity)
    CircleProgressBar cpHumidity;

    @BindView(R.id.cp_soil_moisture)
    CircleProgressBar cpSoilMoisture;

    @BindView(R.id.line_chart_humidity)
    ValueLineChart lineChartHumidity;

    @BindView(R.id.line_chart_soil_moisture)
    ValueLineChart lineChartSoilMoisture;

    @BindView(R.id.line_chart_temperature)
    ValueLineChart lineChartTemperature;

    private TextView mNameTextView;

    private TextView mEmailTextView;

    private RoundedImageView mProfileImageView;

    private ActionBarDrawerToggle mDrawerToggle;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            onFragmentDetached(AboutFragment.TAG);
        }
    }

    @Override
    public void updateAppVersion() {
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        mAppVersionTextView.setText(version);
    }

    @Override
    public void updateUserName(String currentUserName) {
        mNameTextView.setText(currentUserName);
    }

    @Override
    public void updateUserEmail(String currentUserEmail) {
        mEmailTextView.setText(currentUserEmail);
    }

    @Override
    public void updateUserProfilePic(String currentUserProfilePicUrl) {
        //load profile pic url into ANImageView
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();
        }
    }

    @Override
    public void showAboutFragment() {
        lockDrawer();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();
    }

    @Override
    public void lockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Drawable drawable = item.getIcon();
//        if (drawable instanceof Animatable) {
//            ((Animatable) drawable).start();
//        }
//        switch (item.getItemId()) {
//            case R.id.action_cut:
//                return true;
//            case R.id.action_copy:
//                return true;
//            case R.id.action_share:
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    protected void setUp() {
        mToolbar.setTitle("Agriculture");
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();
        mPresenter.onNavMenuCreated();

        //result sensor
        resultSensor();
        // chart
        chartSensor();
    }

    private void chartSensor(){
        ValueLineSeries humidity = new ValueLineSeries();
        humidity.setColor(0xFF56B7F1);

        humidity.addPoint(new ValueLinePoint("Jan", 2.4f));
        humidity.addPoint(new ValueLinePoint("Feb", 3.4f));
        humidity.addPoint(new ValueLinePoint("Mar", .4f));
        humidity.addPoint(new ValueLinePoint("Apr", 1.2f));
        humidity.addPoint(new ValueLinePoint("Mai", 2.6f));
        humidity.addPoint(new ValueLinePoint("Jun", 1.0f));
        humidity.addPoint(new ValueLinePoint("Jul", 3.5f));
        humidity.addPoint(new ValueLinePoint("Aug", 2.4f));
        humidity.addPoint(new ValueLinePoint("Sep", 2.4f));
        humidity.addPoint(new ValueLinePoint("Oct", 3.4f));
        humidity.addPoint(new ValueLinePoint("Nov", .4f));
        humidity.addPoint(new ValueLinePoint("Dec", 1.3f));

        lineChartHumidity.addSeries(humidity);
        lineChartHumidity.startAnimation();

        ValueLineSeries soilMoisture = new ValueLineSeries();
        soilMoisture.setColor(0xFF56B7F1);

        soilMoisture.addPoint(new ValueLinePoint("Jan", 2.4f));
        soilMoisture.addPoint(new ValueLinePoint("Feb", 3.4f));
        soilMoisture.addPoint(new ValueLinePoint("Mar", .4f));
        soilMoisture.addPoint(new ValueLinePoint("Apr", 1.2f));
        soilMoisture.addPoint(new ValueLinePoint("Mai", 2.6f));
        soilMoisture.addPoint(new ValueLinePoint("Jun", 1.0f));
        soilMoisture.addPoint(new ValueLinePoint("Jul", 3.5f));
        soilMoisture.addPoint(new ValueLinePoint("Aug", 2.4f));
        soilMoisture.addPoint(new ValueLinePoint("Sep", 2.4f));
        soilMoisture.addPoint(new ValueLinePoint("Oct", 3.4f));
        soilMoisture.addPoint(new ValueLinePoint("Nov", .4f));
        soilMoisture.addPoint(new ValueLinePoint("Dec", 1.3f));

        lineChartSoilMoisture.addSeries(soilMoisture);
        lineChartSoilMoisture.startAnimation();

        ValueLineSeries temperature = new ValueLineSeries();
        temperature.setColor(0xFF56B7F1);

        temperature.addPoint(new ValueLinePoint("Jan", 2.4f));
        temperature.addPoint(new ValueLinePoint("Feb", 3.4f));
        temperature.addPoint(new ValueLinePoint("Mar", .4f));
        temperature.addPoint(new ValueLinePoint("Apr", 1.2f));
        temperature.addPoint(new ValueLinePoint("Mai", 2.6f));
        temperature.addPoint(new ValueLinePoint("Jun", 1.0f));
        temperature.addPoint(new ValueLinePoint("Jul", 3.5f));
        temperature.addPoint(new ValueLinePoint("Aug", 2.4f));
        temperature.addPoint(new ValueLinePoint("Sep", 2.4f));
        temperature.addPoint(new ValueLinePoint("Oct", 3.4f));
        temperature.addPoint(new ValueLinePoint("Nov", .4f));
        temperature.addPoint(new ValueLinePoint("Dec", 1.3f));

        lineChartTemperature.addSeries(temperature);
        lineChartTemperature.startAnimation();
    }

    private void resultSensor(){
        ValueAnimator humidity = ValueAnimator.ofInt(0, 30);
        ValueAnimator soilMoisture = ValueAnimator.ofInt(0, 46);

        humidity.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer progress = (Integer) animation.getAnimatedValue();
                try{
                    if ( progress != null) {
                        cpHumidity.setProgress(progress);
                    }else {
                        cpHumidity.setProgress(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        humidity.setDuration(5000);
        humidity.start();
        soilMoisture.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer progress = (Integer) animation.getAnimatedValue();
                try{
                    if ( progress != null) {
                        cpSoilMoisture.setProgress(progress);
                    }else {
                        cpSoilMoisture.setProgress(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        soilMoisture.setDuration(5000);
        soilMoisture.start();
    }

    void setupNavMenu() {
        View headerLayout = mNavigationView.getHeaderView(0);
        mProfileImageView = (RoundedImageView) headerLayout.findViewById(R.id.iv_profile_pic);
        mNameTextView = (TextView) headerLayout.findViewById(R.id.tv_name);
        mEmailTextView = (TextView) headerLayout.findViewById(R.id.tv_email);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        mDrawer.closeDrawer(GravityCompat.START);
                        switch (item.getItemId()) {
                            case R.id.nav_item_about:
                                mPresenter.onDrawerOptionAboutClick();
                                return true;
                            case R.id.nav_item_watering:
                                mPresenter.onDrawerWaterControlClick();
                                return true;
                            case R.id.nav_item_reminder:
                                mPresenter.onDrawerReminderClick();
                                return true;
                            case R.id.nav_item_setting:
                                mPresenter.onDrawerSettingClick();
                                return true;
                            case R.id.nav_item_logout:
                                mPresenter.onDrawerOptionLogoutClick();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void openWaterControlActivity() {
        startActivity(WaterControlActivity.getStartIntent(this));
    }

    @Override
    public void openReminderActivity() {
        startActivity(RemindActivity.getStartIntent(this));
    }

    @Override
    public void openSettingActivity() {
        startActivity(RemindPreferenceActivity.getStartIntent(this));
    }

    @Override
    public void closeNavigationDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawer(Gravity.START);
        }
    }
}
