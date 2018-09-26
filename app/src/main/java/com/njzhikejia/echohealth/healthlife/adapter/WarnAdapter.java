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
import com.njzhikejia.echohealth.healthlife.entity.warn.Notices;
import com.njzhikejia.echohealth.healthlife.entity.warn.WarnNoticesData;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.List;

/**
 * Created by 16222 on 2018/6/30.
 */

public class WarnAdapter  extends RecyclerView.Adapter<WarnAdapter.WarnViewHolder>{

    private static final String TAG = "WarnAdapter";
    private Context mContext;
    private List<Notices> list;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_warn, parent, false);
        WarnViewHolder viewHolder = new WarnViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WarnViewHolder holder, int position) {
        Notices warnInfo = list.get(position);
        if (warnInfo == null) {
            Logger.e(TAG, "warnInfo is null");
        }
        matchWarnType(holder, warnInfo);
        if (warnInfo != null && warnInfo.getSrc_data() != null && warnInfo.getSrc_data().getMeasure() != null) {
            holder.tvMeasureTime.setText(warnInfo.getSrc_data().getMeasure().getMeasure_time());

        }
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
        return list.size();
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
                        String diastolicStandardValue = mContext.getString(R.string.diastolic_pressure) + "60-90,";
                        String systolicStandValue = mContext.getString(R.string.systolic_pressure) + "90-140";
                        holder.tvWarnExplanation.setText(String.format(mContext.getString(R.string.standard_value), diastolicStandardValue + systolicStandValue) +
                                "(" +mContext.getString(R.string.blood_pressure_unit) + ")");
                        matchBloodPressureValueColor(holder, value1Text, value2Text);
                        break;
                    case HEART_RATE_WARN:
                        holder.ivType.setImageResource(R.drawable.ic_heart_rate);
                        holder.tvType.setText(R.string.heart_rate_warn);
                        String heartStandardValue = "60-100";
                        holder.tvWarnExplanation.setText(String.format(mContext.getString(R.string.standard_value), heartStandardValue) +
                                "(" +mContext.getString(R.string.heart_rate_with_unit) + ")");
                        matchMeasuredValueColor(holder, value1Text, mContext.getString(R.string.heart_rate_with_unit));
                        break;
                    case BLOOD_SUGAR_WARN:
                        holder.ivType.setImageResource(R.drawable.ic_blood_fat);
                        holder.tvType.setText(R.string.blood_sugar_warn);
                        String sugarStandardValue = "3.1-6.2";
                        holder.tvWarnExplanation.setText(String.format(mContext.getString(R.string.standard_value), sugarStandardValue) +
                                "(" +mContext.getString(R.string.blood_sugar_unit) + ")");
                        matchMeasuredValueColor(holder, value1Text, mContext.getString(R.string.blood_sugar_unit));
                        break;
                    case BLOOD_OXYGEN_WARN:
                        holder.ivType.setImageResource(R.drawable.ic_blood_oxygen);
                        holder.tvType.setText(R.string.blood_oxygen_warn);
                        String oxygenStandardValue = "95-100";
                        holder.tvWarnExplanation.setText(String.format(mContext.getString(R.string.standard_value), oxygenStandardValue) +
                                "(" +mContext.getString(R.string.blood_oxygen_unit) + ")");
                        matchMeasuredValueColor(holder, value1Text, mContext.getString(R.string.blood_oxygen_unit));
                        break;
                }
                holder.tvTimeLabel.setText(R.string.measure_time);
                break;
            case FENCE_WARN:
                holder.ivType.setImageResource(R.drawable.ic_fence);
                holder.tvType.setText(R.string.fence_warn);
                holder.tvTimeLabel.setText(R.string.warn_time);
                break;

            case SOS_WARN:
                holder.ivType.setImageResource(R.drawable.ic_sos);
                holder.tvType.setText(R.string.sos_warn);
                holder.tvTimeLabel.setText(R.string.warn_time);
                break;
            case FALL_WARN:
                holder.ivType.setImageResource(R.drawable.ic_fall);
                holder.tvType.setText(R.string.fall_warn);
                holder.tvTimeLabel.setText(R.string.warn_time);
                break;
        }
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
}
