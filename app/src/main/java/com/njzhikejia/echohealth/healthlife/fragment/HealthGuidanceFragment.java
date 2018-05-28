package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.adapter.HealthGuidanceAdapter;
import com.njzhikejia.echohealth.healthlife.entity.HealthGuidance;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16222 on 2018/5/28.
 */

public class HealthGuidanceFragment extends BaseFragment {

    private static final String TAG = "HealthGuidanceFragment";
    private Context mContext;
    private RecyclerView mRecycleView;
    private HealthGuidanceAdapter mAdapter;
    private List<HealthGuidance> healthGuidanceList;

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
        return view;
    }

    private void initView(View view) {
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
}




