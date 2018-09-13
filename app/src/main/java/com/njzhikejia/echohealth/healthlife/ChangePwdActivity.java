package com.njzhikejia.echohealth.healthlife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

public class ChangePwdActivity extends BaseActivity {

    private static final String TAG = "ChangePwdActivity";
    private Toolbar mToolbar;
    private EditText etOldPwd;
    private EditText etNewPwd;
    private EditText etConfirmPwd;
    private Button btnChangePwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        Logger.d(TAG, "onCreate");
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
        btnChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d(TAG, "button change pwd clicked!");
                checkInput();
            }
        });
    }

    private void checkInput() {
        String oldPwd = etOldPwd.getText().toString().trim();
        String newPwd = etNewPwd.getText().toString().trim();
        String confirmPwd = etConfirmPwd.getText().toString().trim();

        if (TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd) || TextUtils.isEmpty(confirmPwd)) {
            btnChangePwd.setEnabled(false);
            return;
        }
        btnChangePwd.setEnabled(true);

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
    }

    private void clearEditText() {
        etOldPwd.setText("");
        etNewPwd.setText("");
        etConfirmPwd.setText("");
    }
}