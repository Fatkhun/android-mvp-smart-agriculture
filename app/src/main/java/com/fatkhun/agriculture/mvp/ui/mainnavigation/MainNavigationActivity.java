package com.fatkhun.agriculture.mvp.ui.mainnavigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.data.network.model.RelayResponse;
import com.fatkhun.agriculture.mvp.ui.base.BaseActivity;
import com.fatkhun.agriculture.mvp.ui.fragmentsdata.DataFragment;
import com.fatkhun.agriculture.mvp.ui.fragmentshistory.HistoryFragment;
import com.fatkhun.agriculture.mvp.ui.fragmentswatering.PumpState;
import com.fatkhun.agriculture.mvp.ui.fragmentswatering.WateringFragment;
import com.fatkhun.agriculture.mvp.ui.login.LoginActivity;
import com.fatkhun.agriculture.mvp.ui.main.MainActivity;
import com.fatkhun.agriculture.mvp.ui.main.MainMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.main.MainMvpView;
import com.fatkhun.agriculture.mvp.ui.remindpreference.RemindPreferenceActivity;
import com.fatkhun.agriculture.mvp.utils.BottomNavigationBehavior;
import com.fatkhun.agriculture.mvp.utils.BottomNavigationViewHelper;
import com.gigamole.library.PulseView;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainNavigationActivity extends BaseActivity implements MainNavigationMvpView {

    @Inject
    MainNavigationMvpPresenter<MainNavigationMvpView> mPresenter;


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.navigation)
    BottomNavigationView navigationView;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainNavigationActivity.class);
        return intent;
    }

    PumpState mPumpState;

    RelayResponse response;

    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        mToolbar.setTitle("Hai, " + mPresenter.updateUserName());
        setSupportActionBar(mToolbar);

        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigationView);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        mPresenter.getCheckRelay();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getRefreshRelay();
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            if (response.getisPumpOn().equals("OFF")){
                if (item.getItemId() == R.id.nav_item_data){
                    mToolbar.setTitle("Hai, " + mPresenter.updateUserName());
                    fragment = new DataFragment();
                    loadFragment(fragment);
                    return true;
                }else if (item.getItemId() == R.id.nav_item_history){
                    mToolbar.setTitle("History");
                    fragment = new HistoryFragment();
                    loadFragment(fragment);
                    return true;
                }else{
                    mToolbar.setTitle("Watering");
                    fragment = new WateringFragment();
                    loadFragment(fragment);
                    return true;
                }
            }else {
                showMessage("Turn off water");
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable drawable = item.getIcon();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
        switch (item.getItemId()) {
            case R.id.nav_item_setting:
                mPresenter.onSettingClick();
                return true;
            case R.id.nav_item_logout:
                mPresenter.onLogoutClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.disallowAddToBackStack();
        transaction.commit();
    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void openSettingActivity() {
        startActivity(RemindPreferenceActivity.getStartIntent(this));
    }

    @Override
    public void getRelays(RelayResponse relayResponse) {
        this.response = relayResponse;
    }

    @Override
    public void setRelayState(PumpState pumpState) {
        this.mPumpState = pumpState;
        setRelayStatus(pumpState);
    }

    private void setRelayStatus(PumpState pumpState) {
        switch (pumpState){
            case PUMP_ON:
                mToolbar.setTitle("Watering");
                loadFragment(new WateringFragment());
                break;
            case PUMP_OFF:
                mToolbar.setTitle("Hai, " + mPresenter.updateUserName());
                loadFragment(new DataFragment());
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }else {
            if (response.getisPumpOn().equals("ON")){
                showMessage("Water on, turn off please!");
            }else {
                super.onBackPressed();
            }
        }
    }

}
