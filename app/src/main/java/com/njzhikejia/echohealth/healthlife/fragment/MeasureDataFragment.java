package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njzhikejia.echohealth.healthlife.AddMeasureDataActivity;
import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.adapter.MeasureDataAdapter;
import com.njzhikejia.echohealth.healthlife.entity.MeasureData;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16222 on 2018/5/27.
 */

public class MeasureDataFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MeasureDataFragment";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private MeasureDataAdapter mAdapter;
    private List<MeasureData> measureDataList;
    private Context mContext;
    private FloatingActionButton mFabBtn;
    private MeasureDataHandler mHandler;



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
        View view = inflater.inflate(R.layout.fragment_measure_data, null);
        initView(view);
        mHandler = new MeasureDataHandler(this);
        return view;

    }

    private void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.toolbar);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecycleView = view.findViewById(R.id.recycle_view_data);
        mFabBtn = view.findViewById(R.id.floating_action_btn);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
        measureDataList = new ArrayList<>();
        testInitData();
        mAdapter = new MeasureDataAdapter(mContext, measureDataList);
        mRecycleView.setAdapter(mAdapter);
        mFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(mContext, AddMeasureDataActivity.class);
                startActivity(addIntent);
            }
        });
    }

    private void testInitData() {
        MeasureData bloodPressure = new MeasureData(ConstantValues.BLOOD_PRESSURE, "血压", "120", 10, "12:00" );
        MeasureData diastolicPressure = new MeasureData(ConstantValues.DIASTOLIC_PRESSURE, "舒张压", "120", 10, "12:00" );
        measureDataList.add(bloodPressure);
        measureDataList.add(diastolicPressure);
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(TAG, "onPause");
        stopRefresh();
    }


    static class MeasureDataHandler extends Handler{

        private WeakReference<MeasureDataFragment> measureDataFragmentWeakReference;

        public MeasureDataHandler(MeasureDataFragment measureDataFragment) {
            measureDataFragmentWeakReference = new WeakReference<MeasureDataFragment>(measureDataFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValues.MSG_REFRESH_TIME_OUT:
                    if (measureDataFragmentWeakReference.get() != null)
                    measureDataFragmentWeakReference.get().stopRefresh();
                    break;
            }
        }
    }

    @Override
    public void onRefresh() {
        Logger.d(TAG, "onRefresh");
        mHandler.sendEmptyMessageDelayed(ConstantValues.MSG_REFRESH_TIME_OUT, ConstantValues.REFRESH_TIME_OUT);
    }

    private void stopRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            Logger.d(TAG, "stopRefresh!");
        }
    }
}

