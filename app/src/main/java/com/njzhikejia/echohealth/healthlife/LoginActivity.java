package com.njzhikejia.echohealth.healthlife;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.entity.LoginResponse;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.service.UpdateDeviceInfoService;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PhoneUtil;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 16222 on 2018/6/5.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private TextView tvRegister;
    private Button btnLogin;
    private EditText etNumber;
    private EditText etPwd;
    private TextView tvForgetPwd;
    private TextInputLayout numberLayout;
    private TextInputLayout pwdLayout;
    private LoginHandler mHandler;
    private static final int LOGIN_SUCCESS = 100;
    private static final int LOGIN_FAILURE = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        setContentView(R.layout.activity_login);
        skipLogin();
        initView();
        mHandler = new LoginHandler(this);
    }

    private void initView() {
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        etNumber = findViewById(R.id.et_mobile_phone_number);
        etPwd = findViewById(R.id.et_login_password);
        tvForgetPwd = findViewById(R.id.tv_forget_password);
        numberLayout = findViewById(R.id.input_mobile_phone_number);
        pwdLayout = findViewById(R.id.input_login_password);
        tvRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvForgetPwd.setOnClickListener(this);
    }

    private void skipLogin() {
        if (PreferenceUtil.getSecKey(LoginActivity.this, null) != null) {
            if (HealthLifeApplication.isMultiUser) {
                Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentMain);
                finish();
            } else {
                Intent intentMain = new Intent(LoginActivity.this, MeasureDataActivity.class);
                startActivity(intentMain);
                finish();
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d(TAG, "onDestroy");
    }

    class LoginHandler extends Handler {

        private WeakReference<LoginActivity> loginActivityWeakReference;

        public LoginHandler(LoginActivity loginActivity) {
            loginActivityWeakReference = new WeakReference<LoginActivity>(loginActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOGIN_SUCCESS:
                    if (loginActivityWeakReference.get() != null) {
                        if (HealthLifeApplication.isMultiUser) {
                            Intent intentSuccess = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intentSuccess);
                            finish();
                        } else {
                            Intent intentSuccess = new Intent(LoginActivity.this, MeasureDataActivity.class);
                            startActivity(intentSuccess);
                            finish();
                        }
                    }
                    break;
                case LOGIN_FAILURE:
                    handleLoginFailure();
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
                break;

            case R.id.btn_login:
                String inputNumber = etNumber.getText().toString().trim();
                String inputPwd = etPwd.getText().toString().trim();
                if (checkInput(inputNumber, inputPwd)) {
                    login(etNumber.getText().toString().trim(), etPwd.getText().toString().trim());
                }
                break;
        }

    }

    private boolean checkInput(String inputNumber, String inputPwd) {
        if (!PhoneUtil.checkPhoneNumber(inputNumber)) {
//            numberLayout.setError(getString(R.string.input_number_pattern_error));
            return true;
        }
        return true;
    }

    private void login(final String name, String password) {
        OKHttpClientManager.getInstance().postAsync(CommonRequest.postLoginRequest(name, password), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.d(TAG,"onFailure "+call.toString());
                mHandler.sendEmptyMessage(LOGIN_FAILURE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                Logger.d(TAG, "code = "+response.code() + " content = "+content);
                if (response.code() != 200) {
                    mHandler.sendEmptyMessage(LOGIN_FAILURE);
                    return;
                }

                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(content, LoginResponse.class);
                if (loginResponse.getData() == null || loginResponse.getData().getLogin() == null) {
                    mHandler.sendEmptyMessage(LOGIN_FAILURE);
                    return;
                }
                mHandler.sendEmptyMessage(LOGIN_SUCCESS);
                int uid = loginResponse.getData().getLogin().getUid();
                String secKey = loginResponse.getData().getLogin().getSec_key();
                String userName = loginResponse.getData().getLogin().getName();
                Logger.d(TAG, "UID = "+uid+" sec_key = "+secKey);
                PreferenceUtil.putLoginUserUID(LoginActivity.this, uid);
                PreferenceUtil.putSecKey(LoginActivity.this, secKey);
                PreferenceUtil.putLoginUserPhone(LoginActivity.this, name);
                PreferenceUtil.putLoginUserPwd(LoginActivity.this, etPwd.getText().toString());
                PreferenceUtil.putLoginUserName(LoginActivity.this, userName);
                startUdateDeviceInfoService();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startUdateDeviceInfoService() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(this, UpdateDeviceInfoService.class));
        builder.setMinimumLatency(10 * 1000);
        builder.setOverrideDeadline(30 * 1000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        jobScheduler.schedule(builder.build());
    }

    private void handleLoginFailure() {
        ToastUtil.showLongToast(LoginActivity.this, R.string.login_failure);
        etNumber.setText("");
        etPwd.setText("");
    }
}
