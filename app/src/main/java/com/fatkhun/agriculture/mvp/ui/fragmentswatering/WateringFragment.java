package com.fatkhun.agriculture.mvp.ui.fragmentswatering;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.di.component.ActivityComponent;
import com.fatkhun.agriculture.mvp.ui.base.BaseFragment;
import com.fatkhun.agriculture.mvp.ui.fragmentshistory.HistoryFragmentMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentshistory.HistoryFragmentMvpView;
import com.gigamole.library.PulseView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WateringFragment extends BaseFragment implements WateringFragmentMvpView {

    @Inject
    WateringFragmentMvpPresenter<WateringFragmentMvpView> mPresenter;

    @BindView(R.id.pv_watering)
    PulseView pvWatering;

    Boolean isFinish =false;

    public static WateringFragment newInstance() {
        Bundle args = new Bundle();
        WateringFragment fragment = new WateringFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watering, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        return view;
    }


    @Override
    protected void setUp(View view) {
        if (isFinish == true){
            Toast.makeText(getActivity(), "Dont forget Turn Off Water", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.cb_start_watering)
    void btnStartWatering(View v){
        pvWatering.startPulse();
        isFinish = false;
    }

    @OnClick(R.id.cb_finish_watering)
    void btnFinishWatering(View v){
        pvWatering.finishPulse();
        isFinish = true;
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }


}
