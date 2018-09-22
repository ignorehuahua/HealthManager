package com.njzhikejia.echohealth.healthlife;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NetWorkUtils;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

import org.w3c.dom.ProcessingInstruction;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FeedbackActivity extends BaseActivity {

    private static final String TAG = "FeedbackActivity";
    private Toolbar mToolbar;
    private EditText etInput;
    private Button btnSubmit;
    private FeedbackHandler mHandler;
    private static final int RESULT_SUCCESS = 50;
    private static final int RESULT_FAILURE = 51;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Logger.d(TAG, "onCreate");
        mHandler = new FeedbackHandler(this);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etInput = findViewById(R.id.et_feedback);
        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setEnabled(false);
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etInput.getText().toString())) {
                    btnSubmit.setEnabled(false);
                } else {
                    btnSubmit.setEnabled(true);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d(TAG, "submit button clicked！");
                submitFeedback();
            }
        });
    }

    private void submitFeedback() {
        if (!NetWorkUtils.isNetworkConnected(this)) {
            ToastUtil.showShortToast(this, R.string.net_work_error);
            return;
        }
        OKHttpClientManager.getInstance().postAsync(CommonRequest.postFeedbackRequest(PreferenceUtil.getLoginUserUID(this),
                etInput.getText().toString(), "", "", "", 0, 0, ""), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "submit feedback failure");
                mHandler.sendEmptyMessage(RESULT_FAILURE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Logger.d(TAG, "onResponse code = "+response.code() + "content = "+response.body().string());
                if (response.code() == 200) {
                    mHandler.sendEmptyMessage(RESULT_SUCCESS);
                }
            }
        });
    }

    class FeedbackHandler extends Handler{

        private WeakReference<FeedbackActivity> weakReference;

        public FeedbackHandler(FeedbackActivity activity) {
            weakReference = new WeakReference<FeedbackActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_SUCCESS:
                    if (weakReference.get() != null) {
                        ToastUtil.showShortToast(FeedbackActivity.this, R.string.feedback_sent_success);
                        finish();
                    }
                    break;
                case RESULT_FAILURE:
                    if (weakReference.get() != null) {
                        ToastUtil.showShortToast(FeedbackActivity.this, R.string.feedback_sent_failure);
                    }
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}