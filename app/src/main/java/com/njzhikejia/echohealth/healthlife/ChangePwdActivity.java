package com.njzhikejia.echohealth.healthlife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

public class ChangePwdActivity extends BaseActivity implements TextWatcher {

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
    }

    private void clearEditText() {
        etOldPwd.setText("");
        etNewPwd.setText("");
        etConfirmPwd.setText("");
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
}