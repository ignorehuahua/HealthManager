package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.entity.concern.Concerns;
import com.njzhikejia.echohealth.healthlife.entity.user.User;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.QRCodeUtils;

/**
 * Created by 16222 on 2018/8/27.
 */

public class QRCodeActivity extends BaseActivity {

    private static final String TAG = "QRCodeActivity";
    private ImageView ivQRCode;
    private Toolbar mToolbar;
    private static final int QRCODE_SIZE = 800;
    private TextView tvName;
    private TextView tvNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        setContentView(R.layout.activity_qrcode);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivQRCode = findViewById(R.id.iv_qr_code);
        tvName = findViewById(R.id.tv_qr_name);
        tvNumber = findViewById(R.id.tv_qr_number);
        Intent intent = getIntent();
        String number = PreferenceUtil.getLoginUserPhone(this);
        String name = PreferenceUtil.getLoginUserName(this);
        int uid = PreferenceUtil.getLoginUserUID(this);
        if (intent != null) {
            Concerns user = intent.getParcelableExtra(UserDetailsActivity.USER_QR_CODE);
            if (user != null) {
                uid = user.getUid();
                if (uid != PreferenceUtil.getLoginUserUID(this)) {
                    name = user.getName();
                }
                number = user.getPhone();
            }
        }
        tvName.setText(name);
        tvNumber.setText(number);
        String content = ConstantValues.ID_FOR_HEALTH_LIFE + "&&" + name + "&&" + number + "&&" + uid;
        Logger.d(TAG, "content = "+content);
        Bitmap qrcodeBitmap = QRCodeUtils.createQRCodeBitmap(content, QRCODE_SIZE, QRCODE_SIZE);
        ivQRCode.setImageBitmap(qrcodeBitmap);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d(TAG, "onDestroy");
    }
}
