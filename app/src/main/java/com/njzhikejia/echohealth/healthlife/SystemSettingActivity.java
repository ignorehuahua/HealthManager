package com.njzhikejia.echohealth.healthlife;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;

public class SystemSettingActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SystemSettingActivity";
    private Toolbar mToolbar;
    private TextView tvExit;
    private TextView tvChangePwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);
        Logger.d(TAG, "onCreate");
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvExit = findViewById(R.id.exit);
        tvChangePwd = findViewById(R.id.tv_change_pwd);
        tvExit.setOnClickListener(this);
        tvChangePwd.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                AlertDialog dialog = builder.setTitle(R.string.exit)
                        .setMessage(R.string.confirm_exit_login)
                        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // handle exit operation
                                Logger.d(TAG, "exit login clicked");
                                PreferenceUtil.clear(SystemSettingActivity.this);
                                Intent exitIntent = new Intent(ConstantValues.ACTION_EXIT_LOGIN);
                                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(exitIntent);
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // cancel exit
                                Logger.d(TAG, "cancel login clicked");
                            }
                        })
                        .create();
                dialog.show();

                break;
            case R.id.tv_change_pwd:
                Intent intentChangePwd = new Intent(this, ChangePwdActivity.class);
                startActivity(intentChangePwd);
                finish();
                break;
        }
    }
}