package com.njzhikejia.echohealth.healthlife.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njzhikejia.echohealth.healthlife.util.Logger;

/**
 * Created by 16222 on 2018/7/22.
 */

public class UserBaseInfoFragment extends BaseFragment {

    private static final String TAG = "UserBaseInfoFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
