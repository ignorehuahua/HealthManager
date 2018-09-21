package com.njzhikejia.echohealth.healthlife;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NetWorkUtils;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChangePwdActivity extends BaseActivity implements TextWatcher {

    private static final String TAG = "ChangePwdActivity";
    private Toolbar mToolbar;
    private EditText etOldPwd;
    private EditText etNewPwd;
    private EditText etConfirmPwd;
    private Button btnChangePwd;
    private ResetHandler mHandler;
    private static final int RESET_FAILURE = 40;
    private static final int RESET_SUCCESS = 41;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        Logger.d(TAG, "onCreate");
        mHandler = new ResetHandler(this);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etOldPwd = findViewById(R.id.et_previous_pwd);
        etNewPwd = findViewById(R.id.et_new_pwd);
        etConfirmPwd = findViewById(R.id.et_confirm_pwd);
        btnChangePwd = findViewById(R.id.btn_change_pwd);
        btnChangePwd.setEnabled(false);
        btnChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d(TAG, "button change pwd clicked!");
                checkInput();
            }
        });

        etOldPwd.addTextChangedListener(this);
        etNewPwd.addTextChangedListener(this);
        etConfirmPwd.addTextChangedListener(this);
    }

    private void checkInput() {
        String oldPwd = etOldPwd.getText().toString().trim();
        String newPwd = etNewPwd.getText().toString().trim();
        String confirmPwd = etConfirmPwd.getText().toString().trim();

        if (!oldPwd.equals(PreferenceUtil.getLoginUserPwd(this))) {
            ToastUtil.showShortToast(this, R.string.previous_pwd_wrong);
            clearEditText();
            return;
        }
        if (!newPwd.equals(confirmPwd)) {
            ToastUtil.showShortToast(this, R.string.two_password_is_inconsistent);
            clearEditText();
            return;
        }
        resetPwd();
    }

    private void clearEditText() {
        etOldPwd.setText("");
        etNewPwd.setText("");
        etConfirmPwd.setText("");
    }

    private void resetPwd() {
        if (!NetWorkUtils.isNetworkConnected(this)) {
            ToastUtil.showShortToast(this, R.string.net_work_error);
            return;
        }
        OKHttpClientManager.getInstance().postAsync(CommonRequest.postResetPwdRequest(PreferenceUtil.getLoginUserUID(this),
                etOldPwd.getText().toString(), etNewPwd.getText().toString()), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.d(TAG, "reset pwd failure");
                mHandler.sendEmptyMessage(RESET_FAILURE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                Logger.d(TAG, "onRespnse code = "+response.code() + "content = "+responseContent);
                if (response.code() == 200) {
                    mHandler.sendEmptyMessage(RESET_SUCCESS);
                    PreferenceUtil.putLoginUserPwd(ChangePwdActivity.this, etNewPwd.getText().toString());
                } else {
                    mHandler.sendEmptyMessage(RESET_FAILURE);
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(etOldPwd.getText().toString()) || TextUtils.isEmpty(etNewPwd.getText().toString() )
        || TextUtils.isEmpty(etConfirmPwd.getText().toString())) {
            btnChangePwd.setEnabled(false);
        } else {
            btnChangePwd.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

   class ResetHandler extends Handler{

        private WeakReference<ChangePwdActivity> weakReference;

        public ResetHandler(ChangePwdActivity activity) {
            weakReference = new WeakReference<ChangePwdActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESET_SUCCESS:
                    if (weakReference.get() != null) {
                        ToastUtil.showShortToast(ChangePwdActivity.this, R.string.reset_pwd_success);
                        finish();
                    }
                    break;
                case RESET_FAILURE:
                    if (weakReference.get() != null) {
                        ToastUtil.showShortToast(ChangePwdActivity.this, R.string.reset_pwd_failure);
                    }
                    break;
            }
        }
    }
}