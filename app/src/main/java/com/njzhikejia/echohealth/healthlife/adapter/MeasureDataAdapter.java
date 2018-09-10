package com.njzhikejia.echohealth.healthlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.entity.RecentMeasureData;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.List;

/**
 * Created by 16222 on 2018/5/27.
 */

public class MeasureDataAdapter extends RecyclerView.Adapter<MeasureDataAdapter.MeasureDataViewHolder>{

    private static final String TAG = "MeasureDataAdapter";
    private Context context;
    private List<RecentMeasureData.AllData.SpecificData> list;
    public static final int BLOOD_PRESSURE = 1;
    private static final int HEART_RATE = 2;
    private static final int BLOOD_SUGAR = 3;
    private static final int BLOOD_OXYGEN = 7;
    public static final int DIASTOLIC_PRESSURE = 11;  //舒张压
    public static final int SYSTOLIC_PRESSURE = 12;  //收缩压


    public MeasureDataAdapter(Context context, List<RecentMeasureData.AllData.SpecificData> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<RecentMeasureData.AllData.SpecificData> recentlist){
        this.list = recentlist;
        notifyDataSetChanged();
    }

    @Override
    public MeasureDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_measure_data, parent, false);
        MeasureDataViewHolder viewHolder = new MeasureDataViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MeasureDataViewHolder holder, int position) {
        RecentMeasureData.AllData.SpecificData measureData = list.get(position);
        if (measureData == null) {
            Logger.e(TAG, "measureData == null!");
            return;
        }
        matchMeasureType(measureData, holder);
        calculateHealthRate(measureData, holder);
        holder.tvTime.setText(measureData.getMeasure_time());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void matchMeasureType(RecentMeasureData.AllData.SpecificData measureData, MeasureDataViewHolder holder) {
        switch (measureData.getType()) {
            case BLOOD_PRESSURE:
                if (measureData.getBlood_pressure_type() == DIASTOLIC_PRESSURE) {
                    holder.tvDataType.setText(R.string.diastolic_pressure);
                    holder.tvDataValue.setText(String.valueOf(measureData.getValue1()));
                    holder.ivType.setImageResource(R.drawable.ic_diastolic);
                } else {
                    holder.tvDataType.setText(R.string.systolic_pressure);
                    holder.tvDataValue.setText(String.valueOf(measureData.getValue2()));
                    holder.ivType.setImageResource(R.drawable.ic_systolic);
                }
                holder.tvDataUnit.setText(R.string.blood_pressure_unit);
                break;
            case HEART_RATE:
                holder.tvDataType.setText(R.string.heart_rate);
                holder.tvDataUnit.setText(R.string.rate);
                holder.tvDataValue.setText(String.valueOf(measureData.getValue1()));
                holder.ivType.setImageResource(R.drawable.ic_heart_rate);
                break;
            case BLOOD_SUGAR:
                holder.tvDataType.setText(R.string.blood_sugar);
                holder.tvDataUnit.setText(R.string.blood_sugar_unit);
                holder.tvDataValue.setText(String.valueOf(measureData.getValue1()));
                holder.ivType.setImageResource(R.drawable.ic_blood_fat);
                break;
            case BLOOD_OXYGEN:
                holder.tvDataType.setText(R.string.blood_oxygen);
                holder.tvDataUnit.setText(R.string.blood_oxygen_unit);
                holder.tvDataValue.setText(String.valueOf(measureData.getValue1()));
                holder.ivType.setImageResource(R.drawable.ic_blood_oxygen);
                break;
        }
    }

    private void calculateHealthRate(RecentMeasureData.AllData.SpecificData measureData, MeasureDataViewHolder viewHolder) {
        switch (measureData.getType()) {
            case HEART_RATE:
                    if (measureData.getValue1() > 100) {
                        viewHolder.tvHealthRate.setText(R.string.high);
                        viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_high_status);
                    } else if (measureData.getValue1() < 60) {
                        viewHolder.tvHealthRate.setText(R.string.low);
                        viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_low_status);
                    } else {
                        viewHolder.tvHealthRate.setText(R.string.normal);
                        viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_normal_status);
                    }
                break;
            case BLOOD_SUGAR:
                if (measureData.getValue1() > 6.2) {
                    viewHolder.tvHealthRate.setText(R.string.high);
                    viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_high_status);
                } else if (measureData.getValue1() < 3.1) {
                    viewHolder.tvHealthRate.setText(R.string.low);
                    viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_low_status);
                } else {
                    viewHolder.tvHealthRate.setText(R.string.normal);
                    viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_normal_status);
                }
                break;
            case BLOOD_OXYGEN:
                if (measureData.getValue1() > 100) {
                    viewHolder.tvHealthRate.setText(R.string.high);
                    viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_high_status);
                } else if (measureData.getValue1() < 95) {
                    viewHolder.tvHealthRate.setText(R.string.low);
                    viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_low_status);
                } else {
                    viewHolder.tvHealthRate.setText(R.string.normal);
                    viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_normal_status);
                }
                break;
            case BLOOD_PRESSURE:
                if (measureData.getBlood_pressure_type() == DIASTOLIC_PRESSURE) {
                    if (measureData.getValue1() > 90) {
                        viewHolder.tvHealthRate.setText(R.string.high);
                        viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_high_status);
                    } else if (measureData.getValue1() < 60) {
                        viewHolder.tvHealthRate.setText(R.string.low);
                        viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_low_status);
                    } else {
                        viewHolder.tvHealthRate.setText(R.string.normal);
                        viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_normal_status);
                    }
                } else {
                    if (measureData.getValue1() > 140) {
                        viewHolder.tvHealthRate.setText(R.string.high);
                        viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_high_status);
                    } else if (measureData.getValue1() < 90) {
                        viewHolder.tvHealthRate.setText(R.string.low);
                        viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_low_status);
                    } else {
                        viewHolder.tvHealthRate.setText(R.string.normal);
                        viewHolder.tvHealthRate.setBackgroundResource(R.drawable.shape_normal_status);
                    }
                }
                break;
        }

    }

    class MeasureDataViewHolder extends RecyclerView.ViewHolder{

        ImageView ivType;
        TextView tvDataType;
        TextView tvHealthRate;
        TextView tvDataValue;
        TextView tvDataUnit;
        TextView tvTime;

        public MeasureDataViewHolder(View itemView) {
            super(itemView);
            ivType = itemView.findViewById(R.id.iv_data);
            tvDataType = itemView.findViewById(R.id.tv_data_type);
            tvHealthRate = itemView.findViewById(R.id.tv_data_health_rate);
            tvDataValue = itemView.findViewById(R.id.tv_data_value);
            tvDataUnit = itemView.findViewById(R.id.tv_data_unit);
            tvTime = itemView.findViewById(R.id.tv_data_measure_time);
        }
    }
}
