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
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;


import com.dinuscxj.progressbar.CircleProgressBar;
import com.fatkhun.agriculture.mvp.BuildConfig;
import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.data.network.model.AverageDataResponse;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by janisharali on 27/01/17.
 */

public class MainActivity extends BaseActivity implements MainMvpView, SwipeRefreshLayout.OnRefreshListener {

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

    @BindView(R.id.tv_temperature)
    TextView tvTemp;

    @BindView(R.id.tv_water)
    TextView tvWater;

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

    @BindView(R.id.line_chart_water)
    ValueLineChart lineChartWater;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

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

    @Override
    public void setupAverageDataAll(List<AverageDataResponse> averageDataResponse) {
        List<String> time = new ArrayList<>();
        List<Integer> humidity = new ArrayList<>();
        List<Integer> soilMoisture = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        List<Integer> water = new ArrayList<>();


        time.add(dateConverter(averageDataResponse.get(0).getId()));
        time.add(dateConverter((averageDataResponse.get(1).getId())));
        time.add(dateConverter((averageDataResponse.get(2).getId())));
        time.add(dateConverter((averageDataResponse.get(3).getId())));
        time.add(dateConverter((averageDataResponse.get(4).getId())));
        time.add(dateConverter((averageDataResponse.get(5).getId())));
        time.add(dateConverter((averageDataResponse.get(6).getId())));
        time.add(dateConverter((averageDataResponse.get(7).getId())));
        time.add(dateConverter((averageDataResponse.get(8).getId())));
        time.add(dateConverter((averageDataResponse.get(9).getId())));
        time.add(dateConverter((averageDataResponse.get(10).getId())));
        time.add(dateConverter((averageDataResponse.get(11).getId())));


        humidity.add(Math.round(averageDataResponse.get(0).getAvgHumidity()));
        humidity.add(Math.round((averageDataResponse.get(1).getAvgHumidity())));
        humidity.add(Math.round((averageDataResponse.get(2).getAvgHumidity())));
        humidity.add(Math.round((averageDataResponse.get(3).getAvgHumidity())));
        humidity.add(Math.round((averageDataResponse.get(4).getAvgHumidity())));
        humidity.add(Math.round((averageDataResponse.get(5).getAvgHumidity())));
        humidity.add(Math.round((averageDataResponse.get(6).getAvgHumidity())));
        humidity.add(Math.round((averageDataResponse.get(7).getAvgHumidity())));
        humidity.add(Math.round((averageDataResponse.get(8).getAvgHumidity())));
        humidity.add(Math.round((averageDataResponse.get(9).getAvgHumidity())));
        humidity.add(Math.round((averageDataResponse.get(10).getAvgHumidity())));
        humidity.add(Math.round((averageDataResponse.get(11).getAvgHumidity())));

        soilMoisture.add(Math.round((averageDataResponse.get(0).getAvgSoilMoisture())));
        soilMoisture.add(Math.round((averageDataResponse.get(1).getAvgSoilMoisture())));
        soilMoisture.add(Math.round((averageDataResponse.get(2).getAvgSoilMoisture())));
        soilMoisture.add(Math.round((averageDataResponse.get(3).getAvgSoilMoisture())));
        soilMoisture.add(Math.round((averageDataResponse.get(4).getAvgSoilMoisture())));
        soilMoisture.add(Math.round((averageDataResponse.get(5).getAvgSoilMoisture())));
        soilMoisture.add(Math.round((averageDataResponse.get(6).getAvgSoilMoisture())));
        soilMoisture.add(Math.round((averageDataResponse.get(7).getAvgSoilMoisture())));
        soilMoisture.add(Math.round((averageDataResponse.get(8).getAvgSoilMoisture())));
        soilMoisture.add(Math.round((averageDataResponse.get(9).getAvgSoilMoisture())));
        soilMoisture.add(Math.round((averageDataResponse.get(10).getAvgSoilMoisture())));
        soilMoisture.add(Math.round((averageDataResponse.get(11).getAvgSoilMoisture())));

        temp.add(Math.round((averageDataResponse.get(0).getAvgTemp())));
        temp.add(Math.round((averageDataResponse.get(1).getAvgTemp())));
        temp.add(Math.round((averageDataResponse.get(2).getAvgTemp())));
        temp.add(Math.round((averageDataResponse.get(3).getAvgTemp())));
        temp.add(Math.round((averageDataResponse.get(4).getAvgTemp())));
        temp.add(Math.round((averageDataResponse.get(5).getAvgTemp())));
        temp.add(Math.round((averageDataResponse.get(6).getAvgTemp())));
        temp.add(Math.round((averageDataResponse.get(7).getAvgTemp())));
        temp.add(Math.round((averageDataResponse.get(8).getAvgTemp())));
        temp.add(Math.round((averageDataResponse.get(9).getAvgTemp())));
        temp.add(Math.round((averageDataResponse.get(10).getAvgTemp())));
        temp.add(Math.round((averageDataResponse.get(11).getAvgTemp())));

        water.add(Math.round((averageDataResponse.get(0).getAvgWater())));
        water.add(Math.round((averageDataResponse.get(1).getAvgWater())));
        water.add(Math.round((averageDataResponse.get(2).getAvgWater())));
        water.add(Math.round((averageDataResponse.get(3).getAvgWater())));
        water.add(Math.round((averageDataResponse.get(4).getAvgWater())));
        water.add(Math.round((averageDataResponse.get(5).getAvgWater())));
        water.add(Math.round((averageDataResponse.get(6).getAvgWater())));
        water.add(Math.round((averageDataResponse.get(7).getAvgWater())));
        water.add(Math.round((averageDataResponse.get(8).getAvgWater())));
        water.add(Math.round((averageDataResponse.get(9).getAvgWater())));
        water.add(Math.round((averageDataResponse.get(10).getAvgWater())));
        water.add(Math.round((averageDataResponse.get(11).getAvgWater())));


        tvTemp.setText(String.valueOf(temp.get(0)));
        tvWater.setText(String.valueOf(water.get(0)));

        swipeRefreshLayout.setRefreshing(false);

        Log.d("HUMIDITY", "VALUE" + averageDataResponse.get(0).getAvgHumidity());

        resultSensor(humidity, soilMoisture);
        chartSensor(time, humidity, soilMoisture, temp, water);
    }

    private String dateConverter(String dateInput){
        try {
            SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            spf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date newDate = null;
            newDate = spf.parse(dateInput);
            spf= new SimpleDateFormat("HH:mm");
            String returnDate = spf.format(newDate);
            return returnDate;

        }catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                if(swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(true);
                }
                // TODO Fetching data from server
                mPresenter.getAverageDataAll();
            }
        });
    }


    private void chartSensor(List<String> times, List<Integer> humiditys, List<Integer> soilMoistures,
                             List<Integer> temps, List<Integer> waters){
        ValueLineSeries humidity = new ValueLineSeries();
        humidity.setColor(0xFF55C8F0);

        humidity.addPoint(new ValueLinePoint(times.get(0), humiditys.get(0)));
        humidity.addPoint(new ValueLinePoint(times.get(1), humiditys.get(1)));
        humidity.addPoint(new ValueLinePoint(times.get(2), humiditys.get(2)));
        humidity.addPoint(new ValueLinePoint(times.get(3), humiditys.get(3)));
        humidity.addPoint(new ValueLinePoint(times.get(4), humiditys.get(4)));
        humidity.addPoint(new ValueLinePoint(times.get(5), humiditys.get(5)));
        humidity.addPoint(new ValueLinePoint(times.get(6), humiditys.get(6)));
        humidity.addPoint(new ValueLinePoint(times.get(7), humiditys.get(7)));
        humidity.addPoint(new ValueLinePoint(times.get(8), humiditys.get(8)));
        humidity.addPoint(new ValueLinePoint(times.get(9), humiditys.get(9)));
        humidity.addPoint(new ValueLinePoint(times.get(10), humiditys.get(10)));
        humidity.addPoint(new ValueLinePoint(times.get(11), humiditys.get(11)));

        lineChartHumidity.addSeries(humidity);
        lineChartHumidity.startAnimation();

        ValueLineSeries soilMoisture = new ValueLineSeries();
        soilMoisture.setColor(0xFF78DC96);

        soilMoisture.addPoint(new ValueLinePoint(times.get(0), soilMoistures.get(0)));
        soilMoisture.addPoint(new ValueLinePoint(times.get(1), soilMoistures.get(1)));
        soilMoisture.addPoint(new ValueLinePoint(times.get(2), soilMoistures.get(2)));
        soilMoisture.addPoint(new ValueLinePoint(times.get(3), soilMoistures.get(3)));
        soilMoisture.addPoint(new ValueLinePoint(times.get(4), soilMoistures.get(4)));
        soilMoisture.addPoint(new ValueLinePoint(times.get(5), soilMoistures.get(5)));
        soilMoisture.addPoint(new ValueLinePoint(times.get(6), soilMoistures.get(6)));
        soilMoisture.addPoint(new ValueLinePoint(times.get(7), soilMoistures.get(7)));
        soilMoisture.addPoint(new ValueLinePoint(times.get(8), soilMoistures.get(8)));
        soilMoisture.addPoint(new ValueLinePoint(times.get(9), soilMoistures.get(9)));
        soilMoisture.addPoint(new ValueLinePoint(times.get(10), soilMoistures.get(10)));
        soilMoisture.addPoint(new ValueLinePoint(times.get(11), soilMoistures.get(11)));

        lineChartSoilMoisture.addSeries(soilMoisture);
        lineChartSoilMoisture.startAnimation();

        ValueLineSeries temperature = new ValueLineSeries();
        temperature.setColor(0xFFFFA500);

        temperature.addPoint(new ValueLinePoint(times.get(0), temps.get(0)));
        temperature.addPoint(new ValueLinePoint(times.get(1), temps.get(1)));
        temperature.addPoint(new ValueLinePoint(times.get(2), temps.get(2)));
        temperature.addPoint(new ValueLinePoint(times.get(3), temps.get(3)));
        temperature.addPoint(new ValueLinePoint(times.get(4), temps.get(4)));
        temperature.addPoint(new ValueLinePoint(times.get(5), temps.get(5)));
        temperature.addPoint(new ValueLinePoint(times.get(6), temps.get(6)));
        temperature.addPoint(new ValueLinePoint(times.get(7), temps.get(7)));
        temperature.addPoint(new ValueLinePoint(times.get(8), temps.get(8)));
        temperature.addPoint(new ValueLinePoint(times.get(9), temps.get(9)));
        temperature.addPoint(new ValueLinePoint(times.get(10), temps.get(10)));
        temperature.addPoint(new ValueLinePoint(times.get(11), temps.get(11)));

        lineChartTemperature.addSeries(temperature);
        lineChartTemperature.startAnimation();

        ValueLineSeries water = new ValueLineSeries();
        water.setColor(0xFF56B7F1);

        water.addPoint(new ValueLinePoint(times.get(0), waters.get(0)));
        water.addPoint(new ValueLinePoint(times.get(1), waters.get(1)));
        water.addPoint(new ValueLinePoint(times.get(2), waters.get(2)));
        water.addPoint(new ValueLinePoint(times.get(3), waters.get(3)));
        water.addPoint(new ValueLinePoint(times.get(4), waters.get(4)));
        water.addPoint(new ValueLinePoint(times.get(5), waters.get(5)));
        water.addPoint(new ValueLinePoint(times.get(6), waters.get(6)));
        water.addPoint(new ValueLinePoint(times.get(7), waters.get(7)));
        water.addPoint(new ValueLinePoint(times.get(8), waters.get(8)));
        water.addPoint(new ValueLinePoint(times.get(9), waters.get(9)));
        water.addPoint(new ValueLinePoint(times.get(10), waters.get(10)));
        water.addPoint(new ValueLinePoint(times.get(11), waters.get(11)));

        lineChartWater.addSeries(water);
        lineChartWater.startAnimation();
    }

    private void resultSensor(List<Integer> humidity, List<Integer> soilMoisture){
        ValueAnimator humiditys = ValueAnimator.ofInt(0, humidity.get(0));
        ValueAnimator soilMoistures = ValueAnimator.ofInt(0, soilMoisture.get(0));

        humiditys.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
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
        humiditys.setDuration(2000);
        humiditys.start();
        soilMoistures.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
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
        soilMoistures.setDuration(2000);
        soilMoistures.start();
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
                            case R.id.nav_item_history:
                                mPresenter.onDrawerHistoryClick();
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
    public void openHistoryActivity() {
        startActivity(RemindPreferenceActivity.getStartIntent(this));
    }

    @Override
    public void closeNavigationDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawer(Gravity.START);
        }
    }


    @Override
    public void onRefresh() {
        mPresenter.getAverageDataAll();
    }
}
