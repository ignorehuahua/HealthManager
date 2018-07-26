package com.njzhikejia.echohealth.healthlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.entity.WarnInfo;
import com.njzhikejia.echohealth.healthlife.entity.WarnNoticesData;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import org.w3c.dom.ProcessingInstruction;

import java.util.List;

/**
 * Created by 16222 on 2018/6/30.
 */

public class WarnAdapter  extends RecyclerView.Adapter<WarnAdapter.WarnViewHolder>{

    private static final String TAG = "WarnAdapter";
    private Context mContext;
    private List<WarnNoticesData.Data.Notices> list;

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


    public WarnAdapter(Context context, List<WarnNoticesData.Data.Notices> list) {
        this.mContext = context;
        this.list = list;
    }

    public void setlist(List<WarnNoticesData.Data.Notices> list) {
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
        WarnNoticesData.Data.Notices warnInfo = list.get(position);
        if (warnInfo == null) {
            Logger.e(TAG, "warnInfo is null");
        }
        matchWarnType(holder, warnInfo);
        holder.tvMeasureTime.setText(warnInfo.getSrc_data().getMeasure().getMeasure_time());
        holder.tvWarnInfo.setText(warnInfo.getRemark().getNotice_desc());

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
        TextView tvMeasureTime;

        public WarnViewHolder(View itemView) {
            super(itemView);
            ivType = itemView.findViewById(R.id.iv_type);
            tvType = itemView.findViewById(R.id.tv_warn_type);
            tvWarnInfo = itemView.findViewById(R.id.tv_warn_info);
            tvWarnExplanation = itemView.findViewById(R.id.tv_warn_info_explanation);
            tvMeasureValue = itemView.findViewById(R.id.tv_value);
            tvMeasureTime = itemView.findViewById(R.id.tv_measure_time_value);
        }
    }

    private void matchWarnType(WarnViewHolder holder, WarnNoticesData.Data.Notices notice) {
        switch (notice.getType()) {
            case MEASURE_DATA_WARN:
                switch (notice.getSrc_data().getMeasure().getType()) {
                    case BLOOD_PRESSURE_WARN:
                        holder.tvType.setText(R.string.blood_pressure_warn);
                        String diastolicStandardValue = mContext.getString(R.string.diastolic_pressure) + "60-90";
                        String systolicStandValue = mContext.getString(R.string.systolic_pressure) + "90-140";
                        holder.tvWarnExplanation.setText(String.format(mContext.getString(R.string.standard_value), diastolicStandardValue + systolicStandValue));
                        holder.tvMeasureValue.setText(mContext.getString(R.string.diastolic_pressure) + String.valueOf(notice.getSrc_data().getMeasure().getValue1()) +
                        mContext.getString(R.string.systolic_pressure) + String.valueOf(notice.getSrc_data().getMeasure().getValue2()));
                        break;
                    case HEART_RATE_WARN:
                        holder.tvType.setText(R.string.heart_rate_warn);
                        String heartStandardValue = "60-100";
                        holder.tvWarnExplanation.setText(String.format(mContext.getString(R.string.standard_value), heartStandardValue));
                        break;
                    case BLOOD_SUGAR_WARN:
                        holder.tvType.setText(R.string.blood_sugar_warn);
                        String sugarStandardValue = "3.1-6.2";
                        holder.tvWarnExplanation.setText(String.format(mContext.getString(R.string.standard_value), sugarStandardValue));
                        break;
                    case BLOOD_OXYGEN_WARN:
                        holder.tvType.setText(R.string.blood_oxygen_warn);
                        String oxygenStandardValue = "95-100";
                        holder.tvWarnExplanation.setText(String.format(mContext.getString(R.string.standard_value), oxygenStandardValue));
                        break;
                }
                break;
            case FENCE_WARN:
                holder.tvType.setText(R.string.fence_warn);
                break;
            case SOS_WARN:
                holder.tvType.setText(R.string.sos_warn);
                break;
            case FALL_WARN:
                holder.tvType.setText(R.string.fall_warn);
                break;
        }
    }
}
