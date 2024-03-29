package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.UserDetailsActivity;
import com.njzhikejia.echohealth.healthlife.adapter.UserBaseInfoAdapter;
import com.njzhikejia.echohealth.healthlife.entity.user.Extend;
import com.njzhikejia.echohealth.healthlife.entity.user.User;
import com.njzhikejia.echohealth.healthlife.entity.UserBaseInfo;
import com.njzhikejia.echohealth.healthlife.entity.user.UserDetailsResponse;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
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
    private UserBaseInfoAdapter mAdapter;
    private LocalBroadcastManager mLocalBroadcastManager;

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
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, new IntentFilter(ConstantValues.ACTION_LOAD_USER_DETAILS));
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_user_base_info, null);
        initView(view);
        UserDetailsActivity mActivity = (UserDetailsActivity) getActivity();
        if (mActivity.getUserDetailsResponse() != null) {
            initData(mActivity.getUserDetailsResponse());
        }
        return view;
    }

    private void initView(View view) {
        mRecycleView = view.findViewById(R.id.recycle_view_data);
//        rlQRCode = view.findViewById(R.id.rl_qr_code);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setNestedScrollingEnabled(false);
//        rlQRCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentQRCode = new Intent(mContext, QRCodeActivity.class);
//                startActivity(intentQRCode);
//            }
//        });
    }


    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                Logger.d(TAG, "onReceive");
                UserDetailsResponse userDetailsResponse = intent.getParcelableExtra(UserDetailsActivity.KEY_USER_DETAILS);
                initData(userDetailsResponse);
            }
        }
    };

    public void initData(UserDetailsResponse userDetailsResponse) {
        userBaseInfoList = new ArrayList<>();
        String[] label = getResources().getStringArray(R.array.base_info_label);
        User user = userDetailsResponse.getData().getUser();
        Extend extend = user.getExtend();
        String[] value = new String[]{user.getName(), user.getPhone(), user.getIdcard(), user.getBirthday(),matchGender(user),
        matchNation(extend), matchLivingStatus(user), matchMarriageState(extend), matchPoliticalStatus(extend), matchEducation(extend),
        matchHealth(extend), matchBloodType(extend), user.getHome_addr(), matchExercises(extend), matchEatHabit(extend), matchEatAbility(extend), matchWashAbility(extend), matchWearAbility(extend), matchTolietAbility(extend),
        matchMoveAbility(extend), extend.getInterests(), matchIncomeLevel(extend), matchMedicalPay(extend)};
        for (int i = 0; i < label.length; i++) {
            UserBaseInfo userBaseInfo = new UserBaseInfo(label[i], value[i]);
            userBaseInfoList.add(userBaseInfo);
        }
        mAdapter = new UserBaseInfoAdapter(mContext, userBaseInfoList);
        mRecycleView.setAdapter(mAdapter);

    }

    // 匹配性别
    private String matchGender(User user) {
        String result = getString(R.string.male);
        if (user.getGender() == 0) {
            result = getString(R.string.female);
        }

        return result;
    }

    // 匹配民族
    private String matchNation(Extend extend) {
        String[] nations = getResources().getStringArray(R.array.nation);
        return nations[extend.getNation()];
    }

    // 匹配户籍情况
    private String matchLivingStatus(User user) {
        String[] livingStatus = getResources().getStringArray(R.array.living_status);
        return livingStatus[user.getTenant_id()];
    }

    // 匹配婚姻状况
    private String matchMarriageState(Extend extend) {
        String[] marriages = getResources().getStringArray(R.array.marriage_status);
        return marriages[extend.getMarriage()];
    }

    //  匹配政治面貌
    private String matchPoliticalStatus(Extend extend) {
        String[] politicals = getResources().getStringArray(R.array.political_status);
        return politicals[extend.getPolitical_status()];
    }

    // 匹配健康状况
    private String matchHealth(Extend extend) {
        String[] healths = getResources().getStringArray(R.array.health_status);
        return healths[extend.getHealth_state()];
    }

    // 匹配教育程度
    private String matchEducation(Extend extend) {
        String[] educations = getResources().getStringArray(R.array.education_status);
        return educations[extend.getEdu_level()];
    }

    // 匹配血型
    private String matchBloodType(Extend extend) {
        String[] bloodTypes = getResources().getStringArray(R.array.blood_type);
        return bloodTypes[extend.getBlood_group()];
    }

    // 匹配体育锻炼
    private String matchExercises(Extend extend) {
        String[] exercises = getResources().getStringArray(R.array.exercise);
        return exercises[extend.getSport_habit()];
    }

    // 匹配饮食习惯
    private String matchEatHabit(Extend extend) {
        String[] eatHabits = getResources().getStringArray(R.array.eating_habit);
        return eatHabits[extend.getMeal_habit()];
    }

    // 匹配进食能力
    private String matchEatAbility(Extend extend) {
        String[] selfAbilities = getResources().getStringArray(R.array.self_care_ability);
        return selfAbilities[extend.getEat_ability()];
    }

    // 梳洗能力
    private String matchWashAbility(Extend extend) {
        String[] selfAbilities = getResources().getStringArray(R.array.self_care_ability);
        return selfAbilities[extend.getWash_ability()];
    }

    // 穿衣能力
    private String matchWearAbility(Extend extend) {
        String[] selfAbilities = getResources().getStringArray(R.array.self_care_ability);
        return selfAbilities[extend.getWear_ability()];
    }

    // 如厕能力
    private String matchTolietAbility(Extend extend) {
        String[] selfAbilities = getResources().getStringArray(R.array.self_care_ability);
        return selfAbilities[extend.getToilet_ability()];
    }

    // 活动能力
    private String matchMoveAbility(Extend extend) {
        String[] selfAbilities = getResources().getStringArray(R.array.self_care_ability);
        return selfAbilities[extend.getMove_ability()];
    }

    // 收入水平
    private String matchIncomeLevel(Extend extend) {
        String[] incomeLevels = getResources().getStringArray(R.array.income_level);
        return incomeLevels[extend.getRevenue_level()];
    }

    // 医疗支付
    private String matchMedicalPay(Extend extend) {
        String[] medicalPays = getResources().getStringArray(R.array.medical_payment);
        return medicalPays[extend.getPay_type()];
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mLocalBroadcastManager != null) {
            mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
        }
    }
}
