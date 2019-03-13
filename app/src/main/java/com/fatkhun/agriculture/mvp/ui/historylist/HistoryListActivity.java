package com.fatkhun.agriculture.mvp.ui.historylist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.data.network.model.SensorResponse;
import com.fatkhun.agriculture.mvp.ui.base.BaseActivity;
import com.fatkhun.agriculture.mvp.ui.feed.FeedActivity;
import com.fatkhun.agriculture.mvp.ui.feed.FeedMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.feed.FeedMvpView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryListActivity extends BaseActivity implements HistoryListMvpView {

    @Inject
    HistoryListMvpPresenter<HistoryListMvpView> mPresenter;

    @Inject
    HistoryListAdapter mHistoryListAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.rv_data_all)
    RecyclerView rvDataAll;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    List<SensorResponse> dataResponseList;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HistoryListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
        toolbar.setTitle(R.string.history);
        setSupportActionBar(toolbar);
        if (toolbar != null) toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        if (getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);



        mHistoryListAdapter = new HistoryListAdapter(new ArrayList<>(), this);

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDataAll.setLayoutManager(mLayoutManager);
        rvDataAll.setItemAnimator(new DefaultItemAnimator());
        rvDataAll.setAdapter(mHistoryListAdapter);

        mPresenter.getDataAll();
    }

    @Override
    public void updateData(List<SensorResponse> dataResponseLists) {
        mHistoryListAdapter.addItems(dataResponseLists);
        dataResponseList = dataResponseLists;
        mHistoryListAdapter.notifyDataSetChanged();
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
