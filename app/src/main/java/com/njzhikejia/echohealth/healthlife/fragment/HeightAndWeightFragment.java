package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.TimeUtil;

import java.util.Date;

/**
 * Created by 16222 on 2018/6/3.
 */

public class HeightAndWeightFragment extends BaseFragment {

    private static final String TAG = "HeightAndWeightFragment";
    private Context mContext;
    private EditText etHeight;
    private EditText etWeight;
    private EditText etNote;
    private RelativeLayout rlTime;
    private TextView tvMeasureTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_height_and_weight, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mContext = getActivity();
        etHeight = view.findViewById(R.id.et_height);
        etWeight = view.findViewById(R.id.et_weight);
        etNote = view.findViewById(R.id.et_note);
        rlTime = view.findViewById(R.id.rl_measure_time);
        tvMeasureTime = view.findViewById(R.id.tv_measure_time);
        rlTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeUtil.showTimePickerDialog(mContext, new OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        Logger.d(TAG, "onTimeSelect");
                        String time = TimeUtil.getTime(date);
                        tvMeasureTime.setText(time);
                    }
                });
            }
        });
    }
}
