package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.TimeUtil;

import java.util.Date;


/**
 * Created by 16222 on 2018/6/3.
 */

public class BloodPressureFragment extends BaseFragment {

    private static final String TAG = "BloodPressureFragment";
    private Context mContext;
    private EditText etTime;
    private EditText etDiastolicPressure;
    private EditText etSystolicPressure;
    private RadioButton rbNormalState;
    private RadioButton rbSportsState;
    private RadioButton rbRestState;
    private EditText etNote;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_blood_pressure, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mContext = getActivity();
        etDiastolicPressure = view.findViewById(R.id.et_diastolic_pressure);
        etSystolicPressure = view.findViewById(R.id.et_systolic_pressure);
        rbNormalState = view.findViewById(R.id.rb_normal);
        rbSportsState = view.findViewById(R.id.rb_sports);
        rbRestState = view.findViewById(R.id.rb_rest);
        etNote = view.findViewById(R.id.et_note);
        etTime = view.findViewById(R.id.et_measure_time);
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeUtil.showTimePickerDialog(mContext, new OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        Logger.d(TAG, "onTimeSelect");
                        String time = TimeUtil.getTime(date);
                        etTime.setText(time);
                    }
                });
            }
        });
    }

}
