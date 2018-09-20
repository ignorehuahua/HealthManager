package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.entity.concern.Concerneds;
import com.njzhikejia.echohealth.healthlife.entity.concern.Concerns;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NetWorkUtils;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
    private int concernId;

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
            Concerneds concernd = intent.getParcelableExtra(ConstantValues.KEY_FOLLOW_ME_USER);
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
                concernId = concernd.getConcern_id();
            }
        } else if (intent != null && intent.hasExtra(ConstantValues.KEY_MY_FOLLOW_USER)) {
            Concerns concern = intent.getParcelableExtra(ConstantValues.KEY_MY_FOLLOW_USER);
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

    private void handleConcern(int userId, int concernId, int status) {

        if (!NetWorkUtils.isNetworkConnected(UserApplyActivity.this)) {
            ToastUtil.showShortToast(UserApplyActivity.this, R.string.net_work_error);
            return;
        }
        OKHttpClientManager.getInstance().postAsync(CommonRequest.postHandleConcernRequest(userId, concernId, status), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "handle concern failure !!!");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();

                Logger.d(TAG, "onResponse code = "+response.code() + "content = "+responseContent);
                setResult(RESULT_OK);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accept:
                handleConcern(PreferenceUtil.getLoginUserUID(this), concernId, ConstantValues.STATUS_DONE);
                break;
            case R.id.btn_refuse:
                handleConcern(PreferenceUtil.getLoginUserUID(this), concernId, ConstantValues.STATUS_REFUSE);
                break;
        }
    }
}