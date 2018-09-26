package com.njzhikejia.echohealth.healthlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.entity.rule.RuleResult;
import com.njzhikejia.echohealth.healthlife.entity.warn.Notices;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.List;

/**
 * Created by 16222 on 2018/6/30.
 */

public class WarnAdapter  extends RecyclerView.Adapter<WarnAdapter.WarnViewHolder> implements View.OnClickListener {

    private static final String TAG = "WarnAdapter";
    private Context mContext;
    private List<Notices> list;
    private View mFooterView;

    // 告警类型；1：数据指标告警；2：围栏告警；3：SOS告警；4：跌倒告警。
    private static final int MEASURE_DATA_WARN = 1;
    private static final int FENCE_WARN = 2;
    private static final int SOS_WARN = 3;
    private static final int FALL_WARN = 4;

    // 	告警处理状态，0：初始化；1：处理中；2：已处理。
    private static final int INIT_STATUS = 0;
    private static final int PROCESSING_STATUS = 1;
    private static final int PROCESSED_STATUS = 2;

    //    测量类型  1：血压；2：心率；3：血糖；7：血氧
    private static final int BLOOD_PRESSURE_WARN = 1;
    private static final int HEART_RATE_WARN = 2;
    private static final int BLOOD_SUGAR_WARN = 3;
    private static final int BLOOD_OXYGEN_WARN = 7;

    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的


    private RuleResult ruleResult;

    public RuleResult getRuleResult() {
        return ruleResult;
    }

    public void setRuleResult(RuleResult ruleResult) {
        this.ruleResult = ruleResult;
    }

    public WarnAdapter(Context context, List<Notices> list) {
        this.mContext = context;
        this.list = list;
    }

    public void setlist(List<Notices> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public WarnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mFooterView != null && viewType == TYPE_FOOTER){
            mFooterView.setOnClickListener(this);
            return new WarnViewHolder(mFooterView);
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_warn, parent, false);
        WarnViewHolder viewHolder = new WarnViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WarnViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_NORMAL) {
            Notices warnInfo = list.get(position);
            if (warnInfo == null) {
                Logger.e(TAG, "warnInfo is null");
                return;
            }

            matchWarnType(holder, warnInfo);

            Logger.d(TAG, "remark = "+warnInfo.getRemark());
//        {"notice_desc":"心率疑似异常 [心率偏高]"}
            Gson gson = new Gson();
            String remarkJson = warnInfo.getRemark();
            if (!TextUtils.isEmpty(remarkJson)) {
                RemarkDesc desc = gson.fromJson(remarkJson, RemarkDesc.class);
                Logger.d(TAG, "desc = "+desc.getNotice_desc());
                holder.tvWarnInfo.setText(desc.getNotice_desc());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mFooterView == null) {
            return TYPE_NORMAL;
        }
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    public View getFooterView() {
        return mFooterView;
    }
    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount()-1);
    }

    @Override
    public void onClick(View v) {
        if (mFooterClickListener != null) {
            mFooterClickListener.onFooterClick();
        }
    }

    class RemarkDesc{
        private String notice_desc;

        public String getNotice_desc() {
            return notice_desc;
        }

        public void setNotice_desc(String notice_desc) {
            this.notice_desc = notice_desc;
        }
    }

    @Override
    public int getItemCount() {
        if (mFooterView == null) {
            return list.size();
        } else {
            return list.size() + 1;
        }
    }


    class WarnViewHolder extends RecyclerView.ViewHolder {

        ImageView ivType;
        TextView tvType;
        TextView tvWarnInfo;
        TextView tvWarnExplanation;
        TextView tvMeasureValue;
        TextView tvTimeLabel;
        TextView tvMeasureTime;

        public WarnViewHolder(View itemView) {
            super(itemView);
            if (itemView == mFooterView) {
                return;
            }
            ivType = itemView.findViewById(R.id.iv_type);
            tvType = itemView.findViewById(R.id.tv_warn_type);
            tvWarnInfo = itemView.findViewById(R.id.tv_warn_info);
            tvWarnExplanation = itemView.findViewById(R.id.tv_warn_info_explanation);
            tvMeasureValue = itemView.findViewById(R.id.tv_value);
            tvTimeLabel = itemView.findViewById(R.id.tv_measure_time_label);
            tvMeasureTime = itemView.findViewById(R.id.tv_measure_time_value);
        }
    }

    private void matchWarnType(WarnViewHolder holder, Notices notice) {
        switch (notice.getType()) {
            case MEASURE_DATA_WARN:
                double value1 = notice.getSrc_data().getMeasure().getValue1();
                double value2 = notice.getSrc_data().getMeasure().getValue2();
                String value1Text = String.valueOf(value1);
                String value2Text = String.valueOf(value2);
                switch (notice.getSrc_data().getMeasure().getType()) {
                    case BLOOD_PRESSURE_WARN:
                        holder.ivType.setImageResource(R.drawable.ic_diastolic);
                        holder.tvType.setText(R.string.blood_pressure_warn);
                        String diastolicStandardValue = getDiastolicStandardValue(ruleResult);
                        String systolicStandValue = getSystolicStandValue(ruleResult);
                        holder.tvWarnExplanation.setText(String.format(mContext.getString(R.string.standard_value), diastolicStandardValue + systolicStandValue) +
                                "(" +mContext.getString(R.string.blood_pressure_unit) + ")");
                        int blood_value1 = (int) notice.getSrc_data().getMeasure().getValue1();
                        int blood_value2 = (int) notice.getSrc_data().getMeasure().getValue2();
                        matchBloodPressureValueColor(holder, String.valueOf(blood_value1), String.valueOf(blood_value2));
                        break;
                    case HEART_RATE_WARN:
                        holder.ivType.setImageResource(R.drawable.ic_heart_rate);
                        holder.tvType.setText(R.string.heart_rate_warn);
                        String heartStandardValue = getHeartRateStandardValue(ruleResult);
                        holder.tvWarnExplanation.setText(String.format(mContext.getString(R.string.standard_value), heartStandardValue) +
                                "(" +mContext.getString(R.string.heart_rate_with_unit) + ")");
                        int rate_value = (int) notice.getSrc_data().getMeasure().getValue1();
                        matchMeasuredValueColor(holder, String.valueOf(rate_value), mContext.getString(R.string.heart_rate_with_unit));
                        break;
                    case BLOOD_SUGAR_WARN:
                        holder.ivType.setImageResource(R.drawable.ic_blood_fat);
                        holder.tvType.setText(R.string.blood_sugar_warn);
                        String sugarStandardValue = getBloodSugarStandardValue(ruleResult);
                        holder.tvWarnExplanation.setText(String.format(mContext.getString(R.string.standard_value), sugarStandardValue) +
                                "(" +mContext.getString(R.string.blood_sugar_unit) + ")");
                        matchMeasuredValueColor(holder, value1Text, mContext.getString(R.string.blood_sugar_unit));
                        break;
                    case BLOOD_OXYGEN_WARN:
                        holder.ivType.setImageResource(R.drawable.ic_blood_oxygen);
                        holder.tvType.setText(R.string.blood_oxygen_warn);
                        String oxygenStandardValue = getBloodOxygenStandardValue(ruleResult);
                        holder.tvWarnExplanation.setText(String.format(mContext.getString(R.string.standard_value), oxygenStandardValue) +
                                "(" +mContext.getString(R.string.blood_oxygen_unit) + ")");
                        matchMeasuredValueColor(holder, value1Text, mContext.getString(R.string.blood_oxygen_unit));
                        break;
                }
                holder.tvTimeLabel.setText(R.string.measure_time);
                holder.tvMeasureTime.setText(notice.getSrc_data().getMeasure().getMeasure_time());
                break;
            case FENCE_WARN:
                holder.ivType.setImageResource(R.drawable.ic_fence);
                holder.tvType.setText(R.string.fence_warn);
                holder.tvTimeLabel.setText(R.string.warn_time);
                holder.tvMeasureTime.setText(notice.getSrc_data().getMeasure().getMeasure_time());
                break;

            case SOS_WARN:
                holder.ivType.setImageResource(R.drawable.ic_sos);
                holder.tvType.setText(R.string.sos_warn);
                holder.tvTimeLabel.setText(R.string.warn_time);
                int heartRate = (int) notice.getSrc_data().getMeasure().getValue1();
                holder.tvWarnExplanation.setText(mContext.getString(R.string.warn_heart_rate) + String.valueOf(heartRate) + "(" + mContext.getString(R.string.heart_rate_with_unit) + ")");
                holder.tvMeasureValue.setText(mContext.getString(R.string.warn_location) + notice.getSrc_data().getLocation().getAddress());
                holder.tvWarnInfo.setVisibility(View.GONE);
                holder.tvMeasureTime.setText(notice.getSrc_data().getMeasure().getMeasure_time());
                break;
            case FALL_WARN:
                holder.ivType.setImageResource(R.drawable.ic_fall);
                holder.tvType.setText(R.string.fall_warn);
                holder.tvTimeLabel.setText(R.string.warn_time);
                holder.tvMeasureValue.setText(mContext.getString(R.string.warn_location) + notice.getSrc_data().getFalldown().getAddress());
                holder.tvWarnExplanation.setVisibility(View.GONE);
                holder.tvWarnInfo.setVisibility(View.GONE);
                holder.tvMeasureTime.setText(notice.getSrc_data().getFalldown().getAction_time());
                break;
        }
    }


    /**
     * 舒张压标准值
     * @param rule
     * @return
     */
    private String getDiastolicStandardValue(RuleResult rule) {
        String value = mContext.getString(R.string.diastolic_pressure);
        int min = (int) rule.getBp().getDiastolic().getMin();
        int max = (int) rule.getBp().getDiastolic().getMax();
        return value + String.valueOf(min) + "-" + String.valueOf(max) + ",";
    }


    /**
     * 收缩压标准值
     * @param rule
     * @return
     */
    private String getSystolicStandValue(RuleResult rule) {
        String value = mContext.getString(R.string.systolic_pressure);
        int min = (int) rule.getBp().getSystolic().getMin();
        int max = (int) rule.getBp().getSystolic().getMax();
        return value + String.valueOf(min) + "-" + String.valueOf(max);
    }


    /**
     * 心率标准值
     * @param ruleResult
     * @return
     */
    private String getHeartRateStandardValue(RuleResult ruleResult) {

        int min = (int) ruleResult.getPulse().getMin();
        int max = (int) ruleResult.getPulse().getMax();
        return String.valueOf(min) + "-" + String.valueOf(max);
    }

    /**
     * 血糖标准值
     * @param ruleResult
     * @return
     */
    private String getBloodSugarStandardValue(RuleResult ruleResult) {
        double min = ruleResult.getBlood_sugar().getVein_whole_blood().getMin();
        double max = ruleResult.getBlood_sugar().getVein_whole_blood().getMax();
        return String.valueOf(min) + "-" + String.valueOf(max);
    }


    private String getBloodOxygenStandardValue(RuleResult ruleResult) {
        double min = ruleResult.getSpo2().getNormal_min();
        double max = ruleResult.getSpo2().getNormal_max();
        return String.valueOf(min) + "-" + String.valueOf(max);
    }

    private void matchMeasuredValueColor(WarnViewHolder holder, String measuredValue, String valueUnit) {
        String text = String.format(mContext.getString(R.string.measured_value), measuredValue, valueUnit);
        int index[] = new int[2];
        index[0] = text.indexOf(measuredValue);
        index[1] = text.indexOf(valueUnit);

        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.red)),index[0],index[0]+measuredValue.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        holder.tvMeasureValue.setText(style);
    }

    private void matchBloodPressureValueColor(WarnViewHolder holder, String value1, String value2) {
        String text = String.format(mContext.getString(R.string.blood_pressure_measured_value), value1, value2);
        int index[] = new int[2];
        index[0] = text.indexOf(value1);
        index[1] = text.indexOf(value2);

        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.red)),index[0],index[0]+value1.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.red)),index[1],index[1]+value2.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        holder.tvMeasureValue.setText(style);
    }

    public interface OnFooterClickListener {
        void onFooterClick();
    }

    private OnFooterClickListener mFooterClickListener = null;

    public void setOnFooterClickListener(OnFooterClickListener listener) {
        this.mFooterClickListener = listener;
    }
}
