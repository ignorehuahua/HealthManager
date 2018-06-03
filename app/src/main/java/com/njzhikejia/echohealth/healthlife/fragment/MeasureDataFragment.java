package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16222 on 2018/5/27.
 */

public class MeasureDataFragment extends BaseFragment {

    private static final String TAG = "MeasureDataFragment";
    private RecyclerView mRecycleView;
    private MeasureDataAdapter mAdapter;
    private List<MeasureData> measureDataList;
    private Context mContext;
    private FloatingActionButton mFabBtn;

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
        return view;

    }

    private void initView(View view) {
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
}

