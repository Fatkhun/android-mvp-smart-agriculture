package com.fatkhun.agriculture.mvp.ui.watercontrol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.ui.base.BaseActivity;

import javax.inject.Inject;

public class WaterControlActivity extends BaseActivity implements WaterControlMvpView {

    @Inject
    WaterControlMvpPresenter<WaterControlMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, WaterControlActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_control);
    }

    @Override
    protected void setUp() {

    }
}
