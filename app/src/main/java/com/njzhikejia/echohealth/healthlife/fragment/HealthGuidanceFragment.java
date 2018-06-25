package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.adapter.HealthGuidanceAdapter;
import com.njzhikejia.echohealth.healthlife.entity.HealthGuidance;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16222 on 2018/5/28.
 */

public class HealthGuidanceFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "HealthGuidanceFragment";
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private HealthGuidanceAdapter mAdapter;
    private List<HealthGuidance> healthGuidanceList;
    private HealthGuidanceHandler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateView");
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_health_guidance, null);
        initView(view);
        mHandler = new HealthGuidanceHandler(this);
        return view;
    }

    private void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.toolbar);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecycleView = view.findViewById(R.id.recycle_view_health_guidance);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
        healthGuidanceList = new ArrayList<>();
        initData();
        mAdapter = new HealthGuidanceAdapter(mContext, healthGuidanceList);
        mRecycleView.setAdapter(mAdapter);
    }

    private void initData() {
        HealthGuidance bloodPressure = new HealthGuidance("血压","2018-05-12 12:30");
        HealthGuidance pressure = new HealthGuidance("舒张压","2018-05-12 12:30");
        healthGuidanceList.add(bloodPressure);
        healthGuidanceList.add(pressure);
    }

    static class HealthGuidanceHandler extends Handler {

        private WeakReference<HealthGuidanceFragment> healthGuidanceFragmentWeakReference;

        public HealthGuidanceHandler(HealthGuidanceFragment healthGuidanceFragment) {
            healthGuidanceFragmentWeakReference = new WeakReference<HealthGuidanceFragment>(healthGuidanceFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValues.MSG_REFRESH_TIME_OUT:
                    if (healthGuidanceFragmentWeakReference.get() != null)
                        healthGuidanceFragmentWeakReference.get().stopRefresh();
                    break;
            }
        }
    }

    @Override
    public void onRefresh() {
        Logger.d(TAG, "onRefresh");
        mHandler.sendEmptyMessageDelayed(ConstantValues.MSG_REFRESH_TIME_OUT, ConstantValues.REFRESH_TIME_OUT);
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(TAG, "onPause");
        stopRefresh();
    }

    private void stopRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            Logger.d(TAG, "stopRefresh!");
        }
    }
}




