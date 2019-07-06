package com.fatkhun.agriculture.mvp.ui.about;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.ui.base.BaseActivity;
import com.fatkhun.agriculture.mvp.ui.detailhistory.DetailHistoryActivity;
import com.fatkhun.agriculture.mvp.ui.detailhistory.DetailHistoryMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.detailhistory.DetailHistoryMvpView;
import com.fatkhun.agriculture.mvp.ui.mainnavigation.MainNavigationActivity;
import com.fatkhun.agriculture.mvp.utils.ScreenUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;

public class AboutActivity extends BaseActivity implements AboutMvpView {

    @Inject
    AboutMvpPresenter<AboutMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        changeStatusBarColor("#2a2a2a");
    }

    private void changeStatusBarColor(String color){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }

    @OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        startActivity(MainNavigationActivity.getStartIntent(this));
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
