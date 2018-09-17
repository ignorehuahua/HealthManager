package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.entity.FollowMeData;
import com.njzhikejia.echohealth.healthlife.entity.MyFollowsData;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;

public class UserApplyActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "UserApplyActivity";
    private Toolbar mToolbar;
    private TextView tvName;
    private TextView tvNumber;
    private TextView tvSource;
    private TextView tvTime;
    private Button btnAccept;
    private Button btnRefuse;
    public static final int CONCERN_TYPE_QR_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_apply);
        Logger.d(TAG, "onCreate");
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvName = findViewById(R.id.tv_apply_name);
        tvNumber = findViewById(R.id.tv_apply_number);
        tvSource = findViewById(R.id.tv_soure_value);
        tvTime = findViewById(R.id.tv_time_value);
        btnAccept = findViewById(R.id.btn_accept);
        btnRefuse = findViewById(R.id.btn_refuse);
        btnAccept.setOnClickListener(this);
        btnRefuse.setOnClickListener(this);
        setInfo();
    }

    private void setInfo() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(ConstantValues.KEY_FOLLOW_ME_USER)) {
            FollowMeData.Data.Concerneds concernd = intent.getParcelableExtra(ConstantValues.KEY_FOLLOW_ME_USER);
            if (concernd != null) {
                if (concernd.getStatus() == ConstantValues.STATUS_APPLY) {
                    btnRefuse.setVisibility(View.VISIBLE);
                    btnAccept.setVisibility(View.VISIBLE);
                } else {
                    btnAccept.setVisibility(View.GONE);
                    btnRefuse.setVisibility(View.GONE);
                }
                tvName.setText(concernd.getName());
                tvNumber.setText(concernd.getPhone());
                tvTime.setText(concernd.getCreate_time());
                if (concernd.getConcern_type() == CONCERN_TYPE_QR_CODE) {
                    tvSource.setText(R.string.scan_qr_code);
                }
            }
        } else if (intent != null && intent.hasExtra(ConstantValues.KEY_MY_FOLLOW_USER)) {
            MyFollowsData.Data.Concerns concern = intent.getParcelableExtra(ConstantValues.KEY_MY_FOLLOW_USER);
            btnAccept.setVisibility(View.GONE);
            btnRefuse.setVisibility(View.GONE);
            if (concern != null) {
                tvName.setText(concern.getName());
                tvNumber.setText(concern.getPhone());
                tvTime.setText(concern.getCreate_time());
                if (concern.getConcern_type() == CONCERN_TYPE_QR_CODE) {
                    tvSource.setText(R.string.scan_qr_code);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accept:
                break;
            case R.id.btn_refuse:
                break;
        }
    }
}