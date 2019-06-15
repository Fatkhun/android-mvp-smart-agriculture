package com.fatkhun.agriculture.mvp.ui.detailhistory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.data.network.model.DataResponse;
import com.fatkhun.agriculture.mvp.ui.base.BaseActivity;
import com.fatkhun.agriculture.mvp.ui.mainnavigation.MainNavigationActivity;
import com.fatkhun.agriculture.mvp.utils.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailHistoryActivity extends BaseActivity implements DetailHistoryMvpView {

    @Inject
    DetailHistoryMvpPresenter<DetailHistoryMvpView> mPresenter;

    @BindView(R.id.tv_detail_data_humidity)
    TextView tvDetailHumidity;

    @BindView(R.id.tv_detail_data_soil)
    TextView tvDetailSoil;

    @BindView(R.id.tv_detail_data_temp)
    TextView tvDetailTemp;

    @BindView(R.id.tv_detail_data_water)
    TextView tvDetailWater;

    @BindView(R.id.tv_detail_time)
    TextView tvDetailTime;

    @BindView(R.id.tv_detail_rule_humidity)
    TextView tvDetailRuleHumidity;

    @BindView(R.id.tv_detail_rule_soil)
    TextView tvDetailRuleSoil;

    @BindView(R.id.tv_detail_rule_temp)
    TextView tvDetailRuleTemp;

    @BindView(R.id.tv_detail_rule_water)
    TextView tvDetailRuleWater;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DetailHistoryActivity.class);
        return intent;
    }

    DataResponse dataResponse;

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
        mToolbar.setTitle("Detail History");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        if (getActionBar() != null) getActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(mToolbar);
        getIntentDetail();
    }

    private void getIntentDetail(){
        if (getIntent().getSerializableExtra("detail") != null){
            dataResponse = (DataResponse) getIntent().getSerializableExtra("detail");
            setFieldHistory(dataResponse);
        }
    }


    @Override
    public void setFieldHistory(DataResponse dataResponse) {
        tvDetailWater.setText(String.valueOf(dataResponse.getWaterVolume()));
        tvDetailTemp.setText(String.valueOf(dataResponse.getTemp()));
        tvDetailSoil.setText(String.valueOf(dataResponse.getSoilMoisture()));
        tvDetailHumidity.setText(String.valueOf(dataResponse.getHumidity()));
        tvDetailRuleWater.setText(dataResponse.getRuleWater());
        tvDetailRuleTemp.setText(dataResponse.getRuleTemp());
        tvDetailRuleSoil.setText(dataResponse.getRuleSoil());
        tvDetailRuleHumidity.setText(dataResponse.getRuleHum());
        tvDetailTime.setText(CommonUtils.getDateConverter(dataResponse.getTime()));
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
