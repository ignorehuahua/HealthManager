package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.entity.LoginResponse;
import com.njzhikejia.echohealth.healthlife.entity.MeasureData;
import com.njzhikejia.echohealth.healthlife.fragment.MeasureDataFragment;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PhoneUtil;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 16222 on 2018/6/5.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

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

    private void login(String name, String password) {
        OKHttpClientManager.getInstance().postAsync(CommonRequest.postLoginRequest("66666666", "123456"), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.d(TAG,"onFailure "+call.toString());
                mHandler.sendEmptyMessage(LOGIN_FAILURE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                Logger.d(TAG, "code = "+response.code() + " content = "+content);
                mHandler.sendEmptyMessage(LOGIN_SUCCESS);
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(content, LoginResponse.class);
                int uid = loginResponse.getData().getLogin().getUid();
                String secKey = loginResponse.getData().getLogin().getSec_key();
                Logger.d(TAG, "UID = "+uid+" sec_key = "+secKey);
                PreferenceUtil.putUID(LoginActivity.this, uid);
                PreferenceUtil.putSecKey(LoginActivity.this, secKey);
            }
        });
    }
}
