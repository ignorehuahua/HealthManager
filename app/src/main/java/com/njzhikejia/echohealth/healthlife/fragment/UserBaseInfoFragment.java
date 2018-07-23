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

/**
 * Created by 16222 on 2018/7/22.
 */

public class UserBaseInfoFragment extends BaseFragment {

    private static final String TAG = "UserBaseInfoFragment";
    private Context mContext;
    private RecyclerView mRecycleView;
    private List<UserBaseInfo> userBaseInfoList;
    private UserDetailsResponse userDetailsResponse;
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_user_base_info, null);
        initView(view);
//        initData();
        return view;
    }

    private void initView(View view) {
        mRecycleView = view.findViewById(R.id.recycle_view_data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
    }

    public void initData() {
        userBaseInfoList = new ArrayList<>();
        UserDetailsActivity parentActivity = (UserDetailsActivity) getActivity();
        userDetailsResponse = parentActivity.getUserDetailsResponse();
        String[] label = getResources().getStringArray(R.array.base_info_label);
        UserDetailsResponse.ResponseData.User user = userDetailsResponse.getData().getUser();
        UserDetailsResponse.ResponseData.User.Extend extend = user.getExtend();
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
    private String matchGender(UserDetailsResponse.ResponseData.User user) {
        String result = getString(R.string.male);
        if (user.getGender() == 0) {
            result = getString(R.string.female);
        }

        return result;
    }

    // 匹配民族
    private String matchNation(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] nations = getResources().getStringArray(R.array.nation);
        return nations[extend.getNation()];
    }

    // 匹配户籍情况
    private String matchLivingStatus(UserDetailsResponse.ResponseData.User user) {
        String[] livingStatus = getResources().getStringArray(R.array.living_status);
        return livingStatus[user.getTenant_id()];
    }

    // 匹配婚姻状况
    private String matchMarriageState(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] marriages = getResources().getStringArray(R.array.marriage_status);
        return marriages[extend.getMarriage()];
    }

    //  匹配政治面貌
    private String matchPoliticalStatus(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] politicals = getResources().getStringArray(R.array.political_status);
        return politicals[extend.getPolitical_status()];
    }

    // 匹配健康状况
    private String matchHealth(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] healths = getResources().getStringArray(R.array.health_status);
        return healths[extend.getHealth_state()];
    }

    // 匹配教育程度
    private String matchEducation(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] educations = getResources().getStringArray(R.array.education_status);
        return educations[extend.getEdu_level()];
    }

    // 匹配血型
    private String matchBloodType(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] bloodTypes = getResources().getStringArray(R.array.blood_type);
        return bloodTypes[extend.getBlood_group()];
    }

    // 匹配体育锻炼
    private String matchExercises(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] exercises = getResources().getStringArray(R.array.exercise);
        return exercises[extend.getSport_habit()];
    }

    // 匹配饮食习惯
    private String matchEatHabit(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] eatHabits = getResources().getStringArray(R.array.eating_habit);
        return eatHabits[extend.getMeal_habit()];
    }

    // 匹配进食能力
    private String matchEatAbility(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] selfAbilities = getResources().getStringArray(R.array.self_care_ability);
        return selfAbilities[extend.getEat_ability()];
    }

    // 梳洗能力
    private String matchWashAbility(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] selfAbilities = getResources().getStringArray(R.array.self_care_ability);
        return selfAbilities[extend.getWash_ability()];
    }

    // 穿衣能力
    private String matchWearAbility(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] selfAbilities = getResources().getStringArray(R.array.self_care_ability);
        return selfAbilities[extend.getWear_ability()];
    }

    // 如厕能力
    private String matchTolietAbility(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] selfAbilities = getResources().getStringArray(R.array.self_care_ability);
        return selfAbilities[extend.getToilet_ability()];
    }

    // 活动能力
    private String matchMoveAbility(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] selfAbilities = getResources().getStringArray(R.array.self_care_ability);
        return selfAbilities[extend.getMove_ability()];
    }

    // 收入水平
    private String matchIncomeLevel(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] incomeLevels = getResources().getStringArray(R.array.income_level);
        return incomeLevels[extend.getRevenue_level()];
    }

    // 医疗支付
    private String matchMedicalPay(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] medicalPays = getResources().getStringArray(R.array.medical_payment);
        return medicalPays[extend.getPay_type()];
    }

    // 慢性病史；为多选项
    private String matchChronicHistory(UserDetailsResponse.ResponseData.User.Extend extend) {
        String[] chronicHistories = getResources().getStringArray(R.array.chronic_history);
        String chronicHistoryContent = extend.getChronic_illness();
        String[] parms = chronicHistoryContent.split(",");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < parms.length; i++) {
            stringBuffer.append(chronicHistories[Integer.parseInt(parms[i])]);
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
