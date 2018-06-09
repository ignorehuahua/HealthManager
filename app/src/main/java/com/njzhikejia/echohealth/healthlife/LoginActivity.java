package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.ImageUtil;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 16222 on 2018/6/5.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d(TAG, "onDestroy");
    }
}
