package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.entity.Message;
import com.njzhikejia.echohealth.healthlife.util.Logger;

public class MessageDetailsActivity extends BaseActivity {

    private static final String TAG = "MessageDetailsActivity";
    private TextView tvContent;
    private Toolbar mToolbar;
    private TextView tvTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        Logger.d(TAG, "onCreate");
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTime = findViewById(R.id.tv_msg_detail_time);
        tvContent = findViewById(R.id.tv_msg_details);
        Intent msgIntent = getIntent();
        if (msgIntent != null && msgIntent.hasExtra(MessageCenterActivity.KEY_MSG_CONTENT)) {
            Message message = msgIntent.getParcelableExtra(MessageCenterActivity.KEY_MSG_CONTENT);
            mToolbar.setTitle(message.getTitle());
            tvContent.setText(message.getContent());
            tvTime.setText(message.getTime());
        }
    }
}