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
import com.njzhikejia.echohealth.healthlife.UserDetailsActivity;
import com.njzhikejia.echohealth.healthlife.adapter.UserBaseInfoAdapter;
import com.njzhikejia.echohealth.healthlife.entity.UserBaseInfo;
import com.njzhikejia.echohealth.healthlife.entity.UserDetailsResponse;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.ArrayList;
import java.util.List;


public class UserHealthInfoFragment extends BaseFragment {

    private static final String TAG="UserHealthInfoFragment";
    private Context mContext;
    private RecyclerView mRecycleView;
    private List<UserBaseInfo> userHealthInfoList;
    private UserBaseInfoAdapter mAdapter;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_user_health_info, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecycleView = view.findViewById(R.id.recycle_view_data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
    }

    public void initData(UserDetailsResponse userDetailsResponse) {
        userHealthInfoList = new ArrayList<>();
        String[] label = getResources().getStringArray(R.array.health_info_label);
        UserDetailsResponse.ResponseData.User user = userDetailsResponse.getData().getUser();
        UserDetailsResponse.ResponseData.User.Extend extend = user.getExtend();
        String[] values = new String[]{extend.getHeight(), extend.getWeight(), extend.getBust(), extend.getWaist(),
        extend.getHip(), matchTCMconstitution(extend), matchChronicHistory(extend), matchDisability(extend),
                extend.getSurgery_history(),extend.getIrritability_history(), extend.getFamily_medical_history(), extend.getReadme()};
        for (int i = 0; i < label.length; i++) {
            UserBaseInfo userBaseInfo = new UserBaseInfo(label[i], values[i]);
            userHealthInfoList.add(userBaseInfo);
        }
        mAdapter = new UserBaseInfoAdapter(mContext, userHealthInfoList);
        mRecycleView.setAdapter(mAdapter);
    }

    // 慢性病史；为多选项
    private String matchChronicHistory(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] chronicHistories = getResources().getStringArray(R.array.chronic_history);
        String chronicHistoryContent = extend.getChronic_illness();
        String[] parms = chronicHistoryContent.split(",");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < parms.length; i++) {
            stringBuffer.append(chronicHistories[Integer.parseInt(parms[i])]);
            if (i < parms.length - 1) {
                stringBuffer.append(",");
            }
        }
        return stringBuffer.toString();
    }

    // 残疾情况
    private String matchDisability(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] disabilities = getResources().getStringArray(R.array.disability);
        return disabilities[Integer.parseInt(extend.getDisability())];
    }

    // 中医体质
    private String matchTCMconstitution(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] TCMconstitutions = getResources().getStringArray(R.array.tcm_constitution);
        return TCMconstitutions[extend.getConstitution_type()];
    }
}