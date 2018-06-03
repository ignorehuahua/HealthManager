package com.njzhikejia.echohealth.healthlife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.util.Logger;

/**
 * Created by 16222 on 2018/6/3.
 */

public class MemberInfoActivity extends AppCompatActivity{

    private static final String TAG = "MemberInfoActivity";
    private Toolbar mToolbar;
    private TextView tvName;
    private TextView tvRelationship;
    private ImageView ivAvatar;
    private TextView tvPhoneNumber;
    private TextView tvBirthday;
    private TextView tvSex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        setContentView(R.layout.activity_member_info);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        ivAvatar = findViewById(R.id.iv_avatar);
        tvName = findViewById(R.id.tv_member_name);
        tvRelationship = findViewById(R.id.tv_member_relationship);
        tvPhoneNumber = findViewById(R.id.tv_number_value);
        tvBirthday = findViewById(R.id.tv_birthday_value);
        tvSex = findViewById(R.id.tv_sex_value);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
