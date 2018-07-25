package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.adapter.HealthGuidanceAdapter;
import com.njzhikejia.echohealth.healthlife.entity.HealthGuidance;
import com.njzhikejia.echohealth.healthlife.entity.ReportData;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 16222 on 2018/5/28.
 */

public class HealthGuidanceFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "HealthGuidanceFragment";
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private HealthGuidanceAdapter mAdapter;
    private List<ReportData.Data.Reports> healthGuidanceList;
    private HealthGuidanceHandler mHandler;
    private static final int KEY_REPORT = 21;

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
        mAdapter = new HealthGuidanceAdapter(mContext, healthGuidanceList);
        mRecycleView.setAdapter(mAdapter);
        loadReports();
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
                case KEY_REPORT:
                    if (healthGuidanceFragmentWeakReference.get() != null) {
                        healthGuidanceFragmentWeakReference.get().mAdapter.setList(healthGuidanceFragmentWeakReference.get().healthGuidanceList);
                    }
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

    private void loadReports() {
        OKHttpClientManager.getInstance().getAsync(CommonRequest.getUserReports(PreferenceUtil.getUID(mContext)), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                Logger.d(TAG, "onResponse code = "+response.code() + "responseContent = "+responseContent);
                Gson gson = new Gson();
                ReportData reportData = gson.fromJson(responseContent, ReportData.class);
                healthGuidanceList = reportData.getData().getReports();
                mHandler.sendEmptyMessage(KEY_REPORT);
            }
        });
    }
}




