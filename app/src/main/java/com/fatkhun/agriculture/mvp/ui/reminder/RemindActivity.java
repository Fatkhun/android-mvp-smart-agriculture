package com.fatkhun.agriculture.mvp.ui.reminder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.ui.about.AboutFragment;
import com.fatkhun.agriculture.mvp.ui.adapters.ReminderAdapter;
import com.fatkhun.agriculture.mvp.ui.adapters.ViewPageAdapter;
import com.fatkhun.agriculture.mvp.ui.base.BaseActivity;
import com.fatkhun.agriculture.mvp.ui.remindercrud.RemindCreateEditActivity;
import com.fatkhun.agriculture.mvp.ui.remindpreference.RemindPreferenceActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RemindActivity extends BaseActivity implements RemindMvpView, ReminderAdapter.RecyclerListener {

    @Inject
    RemindMvpPresenter<RemindMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RemindActivity.class);
        return intent;
    }

    @BindView(R.id.tabs)
    PagerSlidingTabStrip pagerSlidingTabStrip;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.fab_button)
    FloatingActionButton floatingActionButton;

    private boolean fabIsHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }

        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        pagerSlidingTabStrip.setViewPager(viewPager);
        int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        viewPager.setPageMargin(pageMargin);
    }

    @OnClick(R.id.fab_button)
    public void fabClicked() {
        Intent intent = new Intent(this, RemindCreateEditActivity.class);
        startActivity(intent);
    }

    @Override
    public void hideFab() {
        floatingActionButton.hide();
        fabIsHidden = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (fabIsHidden) {
            floatingActionButton.show();
            fabIsHidden = false;
        }
    }
}
