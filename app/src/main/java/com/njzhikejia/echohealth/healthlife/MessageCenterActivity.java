package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.adapter.MessageCenterAdapter;
import com.njzhikejia.echohealth.healthlife.entity.Message;
import com.njzhikejia.echohealth.healthlife.greendao.DaoSession;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class MessageCenterActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MessageCenterActivity";
    private RecyclerView mRecycleView;
    private MessageCenterAdapter mAdapter;
    private List<Message> messageList;
    private Toolbar mToolbar;
    public static final String KEY_MSG_CONTENT = "key_msg_content";
    private TextView tvNoData;

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
                Intent intentMsg = new Intent(MessageCenterActivity.this, MessageDetailsActivity.class);
                intentMsg.putExtra(KEY_MSG_CONTENT, message);
                startActivity(intentMsg);
            }
        });
    }

    private void initView() {
        tvNoData = findViewById(R.id.tv_no_data);
        tvNoData.setVisibility(View.VISIBLE);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecycleView = findViewById(R.id.recycle_view_data);
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        loadMessages();
        if (messageList == null ) {
            messageList = new ArrayList<>();
        }
        mAdapter = new MessageCenterAdapter(this, messageList);
        mRecycleView.setAdapter(mAdapter);
    }

    private void checkEmptyData() {
        if (messageList != null && messageList.size() > 0) {
            tvNoData.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.VISIBLE);
        }
    }

    private void loadMessages() {
        HealthLifeApplication application = (HealthLifeApplication) getApplication();
        DaoSession daoSession = application.getDaoSession();
        messageList = daoSession.getMessageDao().loadAll();
        fakeData();
        checkEmptyData();
    }

    private void fakeData() {

        Message one = new Message("测试标题一", "说是古代有一个名医，医术高明，被他治好过的病人无数，这时就有三个混混，成天想着如何砸了这名医的招牌\n" +
                "\n" +
                "有一天，他们想出了个办法\n" +
                "\n" +
                "混混头儿装病去看这位名医，如果这个名医给他诊出病来，","2018-09-23 09:40");

        Message two = new Message("测试标题一", "说是古代有一个名医，医术高明，被他治好过的病人无数，这时就有三个混混，成天想着如何砸了这名医的招牌\n" +
                "\n" +
                "有一天，他们想出了个办法\n" +
                "\n" +
                "混混头儿装病去看这位名医，如果这个名医给他诊出病来，","2018-09-23 09:40");
        messageList.add(one);
        messageList.add(two);
    }

    @Override
    public void onRefresh() {
        Logger.d(TAG, "onRefresh");
        loadMessages();
    }
}