package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.adapter.MeasureDataAdapter;
import com.njzhikejia.echohealth.healthlife.adapter.WarnAdapter;
import com.njzhikejia.echohealth.healthlife.entity.MeasureData;
import com.njzhikejia.echohealth.healthlife.entity.WarnInfo;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16222 on 2018/6/30.
 */

public class WarningFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "WarningFragment";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private WarnAdapter mAdapter;
    private List<WarnInfo> warnInfoList;
    private Context mContext;
    private WarnHandler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCrateView");
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_warning, null);
        initView(view);
        mHandler = new WarnHandler(this);
        return view;
    }

    private void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.toolbar);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecycleView = view.findViewById(R.id.recycle_view_data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
        warnInfoList = new ArrayList<>();
        testInitData();
        mAdapter = new WarnAdapter(mContext, warnInfoList);
        mRecycleView.setAdapter(mAdapter);
    }

    private void testInitData() {
        WarnInfo bloodWarn = new WarnInfo();
        WarnInfo heartWarn = new WarnInfo();
        warnInfoList.add(bloodWarn);
        warnInfoList.add(heartWarn);

    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(TAG, "onPause");
        stopRefresh();
    }

    @Override
    public void onRefresh() {
        Logger.d(TAG, "onRefresh");
        mHandler.sendEmptyMessageDelayed(ConstantValues.MSG_REFRESH_TIME_OUT, ConstantValues.REFRESH_TIME_OUT);
    }

    static class WarnHandler extends Handler {

        private WeakReference<WarningFragment> warningFragmentWeakReference;

        public WarnHandler(WarningFragment warningFragment) {
            warningFragmentWeakReference = new WeakReference<WarningFragment>(warningFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValues.MSG_REFRESH_TIME_OUT:
                    if (warningFragmentWeakReference.get() != null)
                        warningFragmentWeakReference.get().stopRefresh();
                    break;
            }
        }
    }

    private void stopRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            Logger.d(TAG, "stopRefresh!");
        }
    }
}
