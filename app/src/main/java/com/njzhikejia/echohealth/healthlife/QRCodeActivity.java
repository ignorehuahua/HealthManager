package com.njzhikejia.echohealth.healthlife;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

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
        String number = PreferenceUtil.getLoginUserPhone(this);
        String name = PreferenceUtil.getLoginUserName(this);
        int uid = PreferenceUtil.getLoginUserUID(this);
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
