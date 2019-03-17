package com.fatkhun.agriculture.mvp.ui.fragmentshistory;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.data.network.model.SensorResponse;
import com.fatkhun.agriculture.mvp.di.component.ActivityComponent;
import com.fatkhun.agriculture.mvp.ui.base.BaseFragment;
import com.fatkhun.agriculture.mvp.ui.fragmentsdata.DataFragment;
import com.fatkhun.agriculture.mvp.ui.fragmentsdata.DataFragmentMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentsdata.DataFragmentMvpView;
import com.fatkhun.agriculture.mvp.ui.historylist.HistoryListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HistoryFragment extends BaseFragment implements HistoryFragmentMvpView, HistoryFragmentAdapter.Callback {

    @Inject
    HistoryFragmentMvpPresenter<HistoryFragmentMvpView> mPresenter;

    @Inject
    HistoryFragmentAdapter mHistoryFragmentAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.rv_data_all)
    RecyclerView rvDataAll;

    public static HistoryFragment newInstance() {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            mHistoryFragmentAdapter.setCallback(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        mHistoryFragmentAdapter = new HistoryFragmentAdapter(new ArrayList<>(), getActivity());

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDataAll.setLayoutManager(mLayoutManager);
        rvDataAll.setItemAnimator(new DefaultItemAnimator());
        rvDataAll.setAdapter(mHistoryFragmentAdapter);

        mPresenter.getDataAll();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void updateData(List<SensorResponse> dataResponseList) {
        mHistoryFragmentAdapter.addItems(dataResponseList);
    }

    @Override
    public void onBlogEmptyViewRetryClick() {
        mPresenter.getDataAll();
    }
}
