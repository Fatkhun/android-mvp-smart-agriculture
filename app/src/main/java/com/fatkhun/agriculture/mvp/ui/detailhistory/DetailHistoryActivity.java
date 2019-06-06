package com.fatkhun.agriculture.mvp.ui.detailhistory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.ui.base.BaseActivity;
import com.fatkhun.agriculture.mvp.ui.mainnavigation.MainNavigationActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class DetailHistoryActivity extends BaseActivity implements DetailHistoryMvpView {

    @Inject
    DetailHistoryMvpPresenter<DetailHistoryMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DetailHistoryActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {

    }
}
