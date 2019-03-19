package com.fatkhun.agriculture.mvp.ui.historylist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.data.network.model.DataResponse;
import com.fatkhun.agriculture.mvp.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryListActivity extends BaseActivity implements HistoryListMvpView, SwipeRefreshLayout.OnRefreshListener, HistoryListAdapter.Callback {

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

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    List<DataResponse> dataResponseList;

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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);



        mHistoryListAdapter = new HistoryListAdapter(new ArrayList<>(), this);

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDataAll.setLayoutManager(mLayoutManager);
        rvDataAll.setItemAnimator(new DefaultItemAnimator());
        rvDataAll.setAdapter(mHistoryListAdapter);
        mHistoryListAdapter.setCallback(this);

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
                mPresenter.getDataAll();
            }
        });
    }

    @Override
    public void updateData(List<DataResponse> dataResponseLists) {
        mHistoryListAdapter.addItems(dataResponseLists);
        swipeRefreshLayout.setRefreshing(false);
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

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }

    @Override
    public void onBlogEmptyViewRetryClick() {
        Log.d("LOAD DATA", "onBlogEmptyViewRetryClick: ");
        mPresenter.getDataAll();
    }

    @Override
    public void onRefresh() {
        mPresenter.getDataAll();
    }
}
