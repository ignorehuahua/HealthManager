package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.TimeUtil;

import java.util.Date;

/**
 * Created by 16222 on 2018/6/3.
 */

public class HeartRateFragment extends BaseFragment {

    private static final String TAG = "HeartRateFragment";
    private Context mContext;
    private EditText etTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_heart_rate, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mContext = getActivity();
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
