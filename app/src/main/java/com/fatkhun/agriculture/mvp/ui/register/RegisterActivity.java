package com.fatkhun.agriculture.mvp.ui.register;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fatkhun.agriculture.mvp.R;
import com.fatkhun.agriculture.mvp.data.network.model.LoginResponse;
import com.fatkhun.agriculture.mvp.ui.base.BaseActivity;
import com.fatkhun.agriculture.mvp.ui.base.MvpView;
import com.fatkhun.agriculture.mvp.ui.login.LoginActivity;
import com.fatkhun.agriculture.mvp.ui.main.MainActivity;
import com.fatkhun.agriculture.mvp.ui.splash.SplashActivity;
import com.fatkhun.agriculture.mvp.ui.splash.SplashMvpPresenter;
import com.fatkhun.agriculture.mvp.ui.splash.SplashMvpView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterMvpView {

    @Inject
    RegisterMvpPresenter<RegisterMvpView> mPresenter;

    @BindView(R.id.tv_to_login)
    TextView tvToLogin;

    @BindView(R.id.et_email)
    TextView etEmail;

    @BindView(R.id.et_name)
    TextView etName;

    @BindView(R.id.et_password)
    TextView etPassword;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(RegisterActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginActivity.getStartIntent(RegisterActivity.this);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void openMainActivity() {
        Intent intent = LoginActivity.getStartIntent(RegisterActivity.this);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_register)
    void registerUser(View v){
        mPresenter.registerUser(etName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
