package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.entity.ApplyResponse;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NetWorkUtils;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 16222 on 2018/9/11.
 */

public class ScanResultActivity extends BaseActivity {

    private static final String TAG = "ScanResultActivity";
    private TextView tvName;
    private TextView tvNumber;
    private Toolbar mToolbar;
    private Button btnAdd;
    private int uid;
    private ScanResultHandler mHandler;
    private static final int RESULT_SUCCESS_0 = 30;
    private static final int RESULT_SUCCESS_101 = 31;
    private static final int RESULT_FAILURE = 32;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);
        mHandler = new ScanResultHandler(this);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvName = findViewById(R.id.tv_member_name);
        tvNumber = findViewById(R.id.tv_number);
        Intent intent = getIntent();
        final String  content = intent.getStringExtra(ConstantValues.SCAN_RESULT);
        if (!TextUtils.isEmpty(content)) {
            String[] temp = null;
            temp = content.split("&&");
            if (temp[0].equals(ConstantValues.ID_FOR_HEALTH_LIFE)) {
                tvName.setText(temp[1]);
                tvNumber.setText(temp[2]);
                uid = Integer.parseInt(temp[3]);
            }
        }
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!NetWorkUtils.isNetworkConnected(ScanResultActivity.this)) {
                    ToastUtil.showShortToast(ScanResultActivity.this, R.string.net_work_error);
                    return;
                }
                OKHttpClientManager.getInstance().postAsync(CommonRequest.postStartConcernRequest(PreferenceUtil.getLoginUserUID(ScanResultActivity.this),
                        uid, ConstantValues.WAY_OF_QR_CODE), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Logger.e(TAG, "scan to add friend failure!!!");
                        mHandler.sendEmptyMessage(RESULT_FAILURE);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseContent = response.body().string();
                        Logger.d(TAG, "code = "+response.code()+ "responseContent = "+responseContent);
                        if (response.code() == 200) {
                            Gson gson = new Gson();
                            ApplyResponse applyResponse = gson.fromJson(responseContent, ApplyResponse.class);
                            if (applyResponse.getRet() == 0) {
                                mHandler.sendEmptyMessage(RESULT_SUCCESS_0);
                            } else if (applyResponse.getRet() == 101) {
                                mHandler.sendEmptyMessage(RESULT_SUCCESS_101);
                            }
                        }
                    }
                });
            }
        });
    }

    class ScanResultHandler extends Handler{

        WeakReference<ScanResultActivity> weakReference;

        public ScanResultHandler(ScanResultActivity activity) {
            weakReference = new WeakReference<ScanResultActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RESULT_SUCCESS_0:
                    ToastUtil.showShortToast(ScanResultActivity.this, R.string.apply_request_sent);
                    break;
                case RESULT_SUCCESS_101:
                    ToastUtil.showShortToast(ScanResultActivity.this, R.string.apply_request_sent);
                    break;
                case RESULT_FAILURE:
                    ToastUtil.showShortToast(ScanResultActivity.this, R.string.request_failure);
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
