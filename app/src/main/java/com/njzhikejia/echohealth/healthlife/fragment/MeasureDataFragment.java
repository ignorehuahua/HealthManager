package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.AddMeasureDataActivity;
import com.njzhikejia.echohealth.healthlife.HealthLifeApplication;
import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.adapter.MeasureDataAdapter;
import com.njzhikejia.echohealth.healthlife.entity.MeasureData;
import com.njzhikejia.echohealth.healthlife.entity.RecentMeasureData;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NetWorkUtils;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 16222 on 2018/5/27.
 */

public class MeasureDataFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MeasureDataFragment";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private MeasureDataAdapter mAdapter;
    private List<RecentMeasureData.AllData.SpecificData> measureDataList;
    private Context mContext;
    private FloatingActionButton mFabBtn;
    private MeasureDataHandler mHandler;
    private static final int KEY_RECENT_DATA = 20;



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
        queryRecentData();
        return view;

    }

    private void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.toolbar);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecycleView = view.findViewById(R.id.recycle_view_data);
        mFabBtn = view.findViewById(R.id.floating_action_btn);
        if (HealthLifeApplication.isMultiUser) {
            mFabBtn.setVisibility(View.GONE);
        } else {
            mFabBtn.setVisibility(View.GONE);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
        measureDataList = new ArrayList<>();
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
    private void queryRecentData() {
        if (!NetWorkUtils.isNetworkConnected(mContext)) {
            ToastUtil.showShortToast(mContext, R.string.net_work_error);
            return;
        }
        OKHttpClientManager.getInstance().getAsync(CommonRequest.getUserRecentMeasureData(PreferenceUtil.getUID(mContext)), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "onFailure queryRecentData");
                stopRefresh();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                Logger.d(TAG, "onResponse code = "+response.code() + "responseContent = "+responseContent);
                Gson gson = new Gson();
                RecentMeasureData recentMeasureData = gson.fromJson(responseContent, RecentMeasureData.class);
                measureDataList = recentMeasureData.getData().getData();
                for (RecentMeasureData.AllData.SpecificData data : measureDataList) {
                    if (data.getType() == MeasureDataAdapter.BLOOD_PRESSURE) {
                        RecentMeasureData.AllData.SpecificData diastolicData = new RecentMeasureData.AllData.SpecificData();
                        diastolicData.setBlood_pressure_type(MeasureDataAdapter.DIASTOLIC_PRESSURE);
                        diastolicData.setMeasure_time(data.getMeasure_time());
                        diastolicData.setType(data.getType());
                        diastolicData.setValue1(data.getValue1());
                        diastolicData.setValue2(data.getValue2());

                        RecentMeasureData.AllData.SpecificData systolicData = new RecentMeasureData.AllData.SpecificData();
                        systolicData.setBlood_pressure_type(MeasureDataAdapter.SYSTOLIC_PRESSURE);
                        systolicData.setMeasure_time(data.getMeasure_time());
                        systolicData.setType(data.getType());
                        systolicData.setValue1(data.getValue1());
                        systolicData.setValue2(data.getValue2());

                        measureDataList.remove(data);
                        measureDataList.add(diastolicData);
                        measureDataList.add(systolicData);
                        break;
                    }
                }
                mHandler.sendEmptyMessage(KEY_RECENT_DATA);
            }
        });
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
                    if (measureDataFragmentWeakReference.get() != null) {
                        measureDataFragmentWeakReference.get().stopRefresh();
                    }
                    break;
                case KEY_RECENT_DATA:
                    Logger.d(TAG, "getRecentData");
                    if (measureDataFragmentWeakReference.get() != null) {
                        measureDataFragmentWeakReference.get().stopRefresh();
                        measureDataFragmentWeakReference.get().mAdapter.setList(measureDataFragmentWeakReference.get().measureDataList);
                    }
                    break;
            }
        }
    }

    @Override
    public void onRefresh() {
        Logger.d(TAG, "onRefresh");
        queryRecentData();
        mHandler.sendEmptyMessageDelayed(ConstantValues.MSG_REFRESH_TIME_OUT, ConstantValues.REFRESH_TIME_OUT);
    }

    private void stopRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            Logger.d(TAG, "stopRefresh!");
        }
    }
}

