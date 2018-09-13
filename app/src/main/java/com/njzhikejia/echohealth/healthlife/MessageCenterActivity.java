package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.njzhikejia.echohealth.healthlife.adapter.MessageCenterAdapter;
import com.njzhikejia.echohealth.healthlife.entity.Message;
import com.njzhikejia.echohealth.healthlife.greendao.DaoSession;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class MessageCenterActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MessageCenterActivity";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private MessageCenterAdapter mAdapter;
    private List<Message> messageList;
    private Toolbar mToolbar;
    public static final String KEY_MSG_CONTENT = "key_msg_content";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);
        Logger.d(TAG, "onCreate");
        initView();
        mAdapter.setOnItemClickListener(new MessageCenterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Logger.d(TAG, "onItemClick");
                Message message = messageList.get(position);
                String content = message.getContent();
                Intent intentMsg = new Intent(MessageCenterActivity.this, MessageDetailsActivity.class);
                intentMsg.putExtra(KEY_MSG_CONTENT, content);
                startActivity(intentMsg);
            }
        });
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSwipeRefreshLayout =findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.toolbar);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecycleView = findViewById(R.id.recycle_view_data);
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        loadMessages();
//        Message one = new Message("标题", "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容");
//        messageList.add(one);
        mAdapter = new MessageCenterAdapter(this, messageList);
        mAdapter.setList(messageList);
        mRecycleView.setAdapter(mAdapter);
    }

    private void loadMessages() {
        HealthLifeApplication application = (HealthLifeApplication) getApplication();
        DaoSession daoSession = application.getDaoSession();
        messageList = daoSession.getMessageDao().loadAll();

    }

    @Override
    public void onRefresh() {
        Logger.d(TAG, "onRefresh");
    }

    private void stopRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            Logger.d(TAG, "stopRefresh!");
        }
    }
}