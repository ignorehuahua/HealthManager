package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.njzhikejia.echohealth.healthlife.adapter.ShowImageAdapter;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.ImageUtil;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NetWorkUtils;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

import org.w3c.dom.ProcessingInstruction;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

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
    private RecyclerView mRecycleView;
    private ShowImageAdapter mAdapter;
    private List<Uri> imgList;

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

        mRecycleView = findViewById(R.id.recycle_view);
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
        imgList = new ArrayList<>();
        mAdapter = new ShowImageAdapter(this, imgList);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ShowImageAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                Logger.d(TAG, "onDeleteClick position = "+position);
            }

            @Override
            public void onPicker(int position) {
                Logger.d(TAG, "onPicker position = "+position);
                Logger.d(TAG, "list.size = "+imgList.size());
                if (position == imgList.size()) {
                    if (position != ShowImageAdapter.MAX_SIZE) {
                        // select photo
                        choosePhoto();
                    }
                }
            }
        });
    }

    private void submitFeedback() {
        if (!NetWorkUtils.isNetworkConnected(this)) {
            ToastUtil.showShortToast(this, R.string.net_work_error);
            return;
        }
        String img1 = "";
        String img2 = "";
        String img3 = "";
//        if (imgList.size() > 0) {
//            for (int i = 0; i < imgList.size(); i++) {
//                Bitmap bitmap = ImageUtil.decodeUri(this, imgList.get(i), 800, 800);
//                if (i == 0) {
//                    img1 = ImageUtil.bitmapToBase64(bitmap);
//                } else if (i == 1) {
//                    img2 = ImageUtil.bitmapToBase64(bitmap);
//                } else if (i == 2) {
//                    img3 = ImageUtil.bitmapToBase64(bitmap);
//                }
//            }
//        }

        Logger.d(TAG, "img1 = "+img1+ "img2 = "+img2 + "img3 = "+img3);

        OKHttpClientManager.getInstance().postAsync(CommonRequest.postFeedbackRequest(PreferenceUtil.getLoginUserUID(this),
                etInput.getText().toString(), img1, img2, img3, 0, 0, ""), new Callback() {
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

    /**
     * 从相册选取
     */
    private void choosePhoto() {
        Intent intentPhoto = new Intent(Intent.ACTION_PICK);
        intentPhoto.setType("image/*");
        startActivityForResult(intentPhoto, ConstantValues.REQUEST_CODE_OF_GRLLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ConstantValues.REQUEST_CODE_OF_GRLLERY) {
            Uri uri = data.getData();
            imgList.add(uri);
            mAdapter.setList(imgList);
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