package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.UserDetailsActivity;
import com.njzhikejia.echohealth.healthlife.entity.UserBaseInfo;
import com.njzhikejia.echohealth.healthlife.entity.UserDetailsResponse;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 16222 on 2018/7/22.
 */

public class UserBaseInfoFragment extends BaseFragment {

    private static final String TAG = "UserBaseInfoFragment";
    private Context mContext;
    private RecyclerView mRecycleView;
    private List<UserBaseInfo> userBaseInfoList;
    private UserDetailsResponse userDetailsResponse;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_user_base_info, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecycleView = view.findViewById(R.id.recycle_view_data);
    }

    private void initData() {
        userBaseInfoList = new ArrayList<>();
        UserDetailsActivity parentActivity = (UserDetailsActivity) getActivity();
        userDetailsResponse = parentActivity.getUserDetailsResponse();
        String[] label = getResources().getStringArray(R.array.base_info_label);
        UserDetailsResponse.ResponseData.User user = userDetailsResponse.getData().getUser();
        UserDetailsResponse.ResponseData.User.Extend extend = user.getExtend();
        String nation = label[extend.getNation()];

//        String[] value = new String[]{user.getName(), user.getPhone(), user.getIdcard(), user.getBirthday(), matchGender(user.getGender())}


    }

    private String matchGender(UserDetailsResponse.ResponseData.User user) {
        String result = getString(R.string.male);
        if (user.getGender() == 0) {
            result = getString(R.string.female);
        }

        return result;
    }
}
