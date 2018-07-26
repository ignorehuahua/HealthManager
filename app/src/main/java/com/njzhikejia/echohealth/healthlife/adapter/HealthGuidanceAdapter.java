package com.njzhikejia.echohealth.healthlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.entity.ReportData;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.List;

/**
 * Created by 16222 on 2018/5/28.
 */

public class HealthGuidanceAdapter extends RecyclerView.Adapter<HealthGuidanceAdapter.HealthGuidanceViewHolder> {

    private static final String TAG = "HealthGuidanceAdapter";
    private Context context;
    private List<ReportData.Data.Reports> list;
    private static final int BLOOD_PRESSURE_REPORT = 1;
    private static final int SLEEP_REPORT = 2;
    private static final int DEPRESSION_REPORT = 4;
    private static final int CHRONIC_PROSTATE = 5;

    public HealthGuidanceAdapter(Context context, List<ReportData.Data.Reports> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public HealthGuidanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_health_guidance, parent, false);
        HealthGuidanceViewHolder viewHolder = new HealthGuidanceViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HealthGuidanceViewHolder holder, int position) {
        ReportData.Data.Reports healthGuidance = list.get(position);
        if (healthGuidance == null) {
            Logger.e(TAG, "healthGuidance == null!");
            return;
        }
        holder.tvTime.setText(healthGuidance.getCreate_time());
        holder.ivAvatar.setImageResource(R.drawable.blood_pressure);
        matchReportType(healthGuidance, holder);
    }

    public void setList(List<ReportData.Data.Reports> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HealthGuidanceViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvTime;

        public HealthGuidanceViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_health_guidance_avatar);
            tvName = itemView.findViewById(R.id.tv_health_guidance_name);
            tvTime = itemView.findViewById(R.id.tv_health_guidance_time);
        }
    }

    private void matchReportType(ReportData.Data.Reports data, HealthGuidanceViewHolder holder) {
        switch (data.getType()) {
            case BLOOD_PRESSURE_REPORT:
                holder.tvName.setText(R.string.blood_pressure_report);
                break;
            case SLEEP_REPORT:
                holder.tvName.setText(R.string.sleep_report);
                break;
            case DEPRESSION_REPORT:
                holder.tvName.setText(R.string.depression_report);
                break;
            case CHRONIC_PROSTATE:
                holder.tvName.setText(R.string.chronic_prostate);
                break;
        }
    }
}
