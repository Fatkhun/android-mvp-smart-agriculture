package com.fatkhun.agriculture.mvp.ui.fragmentswatering;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.data.network.model.RelayResponse;
import com.fatkhun.agriculture.mvp.data.network.model.User;
import com.fatkhun.agriculture.mvp.di.component.ActivityComponent;
import com.fatkhun.agriculture.mvp.ui.base.BaseFragment;
import com.fatkhun.agriculture.mvp.ui.fragmentshistory.HistoryFragmentMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.fragmentshistory.HistoryFragmentMvpView;
import com.gigamole.library.PulseView;

import java.util.List;

import javax.inject.Inject;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.ghyeok.stickyswitch.widget.StickySwitch;


public class WateringFragment extends BaseFragment implements WateringFragmentMvpView {

    @Inject
    WateringFragmentMvpPresenter<WateringFragmentMvpView> mPresenter;

    @BindView(R.id.pv_watering)
    PulseView pvWatering;

    @BindView(R.id.cb_finish_watering)
    CircleButton cbFinishWater;

    @BindView(R.id.sticky_switch)
    StickySwitch stickySwitch;

    Boolean isFinish =false;

    PumpState mPumpState;

    String users;

    Handler handler = new Handler();

    public static WateringFragment newInstance(String state_1, String state_2) {
        Bundle args = new Bundle();
        args.putSerializable("state_1", state_1);
        args.putSerializable("state_2", state_2);
        WateringFragment fragment = new WateringFragment();
        Log.d("Debug ", String.format("State_1  : %s, State_2 : %s",state_1,state_2));
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
        setPump();
        setOffRelay();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getRelay();
                handler.postDelayed(this, 5000);
            }
        }, 5000);
        mPresenter.getRelay();
        mPresenter.getUserAll();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void setupUpdateRelay(RelayResponse relayResponse) {

    }

    @Override
    public void setupUpdateRelayPump(RelayResponse relayResponse) {

    }

    @Override
    public void setupUpdateRelayAutoPump(RelayResponse relayResponse) {

    }

    @Override
    public void getRelays(RelayResponse relayResponse) {

    }

    @Override
    public void updateUser(String userList) {
        users = userList;
    }

    @Override
    public void setRelayState(PumpState pumpState) {
        mPumpState = pumpState;
        String currentUserId = mPresenter.getUserId();
        if (currentUserId.equals(users)){
            setRelayStatusTwo(pumpState);
        }else {
            setRelayStatusTwo(pumpState);
        }
    }

    private void setPump(){
        stickySwitch.setLeftIcon(R.drawable.ic_close_white_24dp);
        stickySwitch.setRightIcon(R.drawable.ic_flash_on_white_24dp);
        stickySwitch.setTypeFace(Typeface.DEFAULT_BOLD);
        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(StickySwitch.Direction direction, String s) {
                if (direction == StickySwitch.Direction.LEFT) {
                    pvWatering.finishPulse();
                    mPresenter.updateRelay(PumpState.PUMP_OFF.getText(), PumpState.AUTO_ON.getText());
                    Toast.makeText(getActivity(), "Pump Off & Auto Pump On", Toast.LENGTH_SHORT).show();
                } else {
                    pvWatering.startPulse();
                    mPresenter.updateRelay(PumpState.PUMP_ON.getText(), PumpState.AUTO_OFF.getText());
                    Toast.makeText(getActivity(), "Pump On", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setOffRelay(){
        cbFinishWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stickySwitch.setDirection(StickySwitch.Direction.LEFT);
                pvWatering.finishPulse();
                mPresenter.updateRelay(PumpState.PUMP_OFF.getText(), PumpState.AUTO_OFF.getText());
                Toast.makeText(getActivity(), "Relay Off", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void validateRelayState(String deviceId) {
        String id = mPresenter.getDeviceId();

        if (deviceId.equals(id) && mPumpState.equals(PumpState.PUMP_OFF)){
            setRelayState(PumpState.PUMP_OFF);
            setRelayState(PumpState.AUTO_ON);
        }else if (deviceId.equals(id) && mPumpState.equals(PumpState.PUMP_ON)){
            setRelayState(PumpState.PUMP_ON);
            setRelayState(PumpState.AUTO_OFF);
        }else {
            setRelayState(PumpState.PUMP_OFF);
            setRelayState(PumpState.AUTO_OFF);
        }
    }

    private void setRelayStatusOne(PumpState pumpState) {
        switch (pumpState){
            case PUMP_ON:
                Toast.makeText(getActivity(),"Pump is Running ...", Toast.LENGTH_SHORT).show();
                break;
            case PUMP_OFF:
                Toast.makeText(getActivity(), "Pump Off", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setRelayStatusTwo(PumpState pumpState) {
        String currentUserId = mPresenter.getUserId();
        switch (pumpState){
            case PUMP_ON:
//                if (currentUserId.equals(users)){
//                    stickySwitch.setDirection(StickySwitch.Direction.RIGHT);
//                    pvWatering.startPulse();
//                }else {
//                    stickySwitch.setDirection(StickySwitch.Direction.LEFT);
//                    pvWatering.startPulse();
//                }
                Toast.makeText(getActivity(), "Pump is Running ...", Toast.LENGTH_SHORT).show();
                break;
            case PUMP_OFF:
//                if (currentUserId.equals(users)){
//                    stickySwitch.setDirection(StickySwitch.Direction.LEFT);
//                    pvWatering.finishPulse();
//                }else {
//                    stickySwitch.setDirection(StickySwitch.Direction.RIGHT);
//                    pvWatering.finishPulse();
//                }
                Toast.makeText(getActivity(),"Pump Off", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
