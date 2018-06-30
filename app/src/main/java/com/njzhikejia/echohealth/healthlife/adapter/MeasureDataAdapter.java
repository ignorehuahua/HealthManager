package com.njzhikejia.echohealth.healthlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.entity.MeasureData;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.List;

/**
 * Created by 16222 on 2018/5/27.
 */

public class MeasureDataAdapter extends RecyclerView.Adapter<MeasureDataAdapter.MeasureDataViewHolder>{

    private static final String TAG = "MeasureDataAdapter";
    private Context context;
    private List<MeasureData> list;

    public MeasureDataAdapter(Context context, List<MeasureData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MeasureDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_measure_data, parent, false);
        MeasureDataViewHolder viewHolder = new MeasureDataViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MeasureDataViewHolder holder, int position) {
        MeasureData measureData = list.get(position);
        if (measureData == null) {
            Logger.e(TAG, "measureData == null!");
            return;
        }
        holder.ivType.setImageResource(R.drawable.blood_pressure);
        holder.tvDataType.setText(measureData.getDataName());
        holder.tvHealthRate.setText("ddddddddddd");
        holder.tvDataValue.setText(measureData.getDataValue());
        holder.tvDataUnit.setText("");
        holder.tvTime.setText(measureData.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
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
