package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.util.ConstantValues;

/**
 * Created by 16222 on 2018/9/11.
 */

public class ScanResultActivity extends BaseActivity {

    private static final String TAG = "ScanResultActivity";
    private TextView tvName;
    private TextView tvNumber;
    private Toolbar mToolbar;
    private Button btnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvName = findViewById(R.id.tv_member_name);
        tvNumber = findViewById(R.id.tv_number);
        Intent intent = getIntent();
        String  content = intent.getStringExtra(ConstantValues.SCAN_RESULT);
        if (!TextUtils.isEmpty(content)) {
            String[] temp = null;
            temp = content.split("&&");
            if (temp[0].equals(ConstantValues.ID_FOR_HEALTH_LIFE)) {
                tvName.setText(temp[1]);
                tvNumber.setText(temp[2]);
            }
        }
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
