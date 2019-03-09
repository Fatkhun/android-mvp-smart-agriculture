package com.fatkhun.agriculture.mvp.ui.remindpreference;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.ui.base.BaseActivity;
import com.fatkhun.agriculture.mvp.ui.fragments.PreferenceFragment;
import com.fatkhun.agriculture.mvp.ui.remindview.RemindViewActivity;
import com.fatkhun.agriculture.mvp.ui.remindview.RemindViewMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.remindview.RemindViewMvpView;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class RemindPreferenceActivity extends BaseActivity implements RemindPreferenceMvpView {

    @Inject
    RemindPreferenceMvpPresenter<RemindPreferenceMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RemindPreferenceActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_preference);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.settings);
        setSupportActionBar(toolbar);
        if (toolbar != null) toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        if (getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, new PreferenceFragment()).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
