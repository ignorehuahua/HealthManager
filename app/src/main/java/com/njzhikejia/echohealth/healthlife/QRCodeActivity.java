package com.njzhikejia.echohealth.healthlife;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.QRCodeUtils;

/**
 * Created by 16222 on 2018/8/27.
 */

public class QRCodeActivity extends BaseActivity {

    private static final String TAG = "QRCodeActivity";
    private ImageView ivQRCode;
    private Toolbar mToolbar;

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
        Bitmap qrcodeBitmap = QRCodeUtils.createQRCodeBitmap("www.baidu.com", 480, 480);
        ivQRCode.setImageBitmap(qrcodeBitmap);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d(TAG, "onDestroy");
    }
}
