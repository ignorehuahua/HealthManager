package com.njzhikejia.echohealth.healthlife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.util.Logger;

/**
 * Created by 16222 on 2018/9/22.
 */

public class AboutActivity extends BaseActivity {

    private static final String TAG = "AboutActivity";
    private Toolbar mToolbar;
    private TextView tvVerionCode;
    private TextView tvUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Logger.d(TAG, "versionCode = "+BuildConfig.VERSION_NAME);
        tvVerionCode = findViewById(R.id.tv_app_version);
        tvVerionCode.setText(getString(R.string.version_code) + BuildConfig.VERSION_NAME);
        tvUpdate = findViewById(R.id.tv_update);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.d(TAG, "check new version clicked");
            }
        });
    }
}
