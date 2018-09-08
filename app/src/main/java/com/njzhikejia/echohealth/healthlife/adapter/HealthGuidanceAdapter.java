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

public class HealthGuidanceAdapter extends RecyclerView.Adapter<HealthGuidanceAdapter.HealthGuidanceViewHolder> implements View.OnClickListener {

    private static final String TAG = "HealthGuidanceAdapter";
    private Context context;
    private List<ReportData.Data.Reports> list;
    private static final int BLOOD_PRESSURE_REPORT = 1;
    public static final int SLEEP_REPORT = 2;
    public static final int DEPRESSION_REPORT = 4;
    private static final int CHRONIC_PROSTATE = 5;

    public HealthGuidanceAdapter(Context context, List<ReportData.Data.Reports> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public HealthGuidanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_health_guidance, null);
        HealthGuidanceViewHolder viewHolder = new HealthGuidanceViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HealthGuidanceViewHolder holder, int position) {
        holder.itemView.setTag(position);
        ReportData.Data.Reports healthGuidance = list.get(position);
        if (healthGuidance == null) {
            Logger.e(TAG, "healthGuidance == null!");
            return;
        }
        holder.tvTime.setText(healthGuidance.getCreate_time());
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

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    class HealthGuidanceViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvTime;
        private TextView tvResult;

        public HealthGuidanceViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_health_guidance_avatar);
            tvName = itemView.findViewById(R.id.tv_health_guidance_name);
            tvTime = itemView.findViewById(R.id.tv_health_guidance_time);
            tvResult = itemView.findViewById(R.id.tv_health_guidance_result);
        }
    }

    private void matchReportType(ReportData.Data.Reports data, HealthGuidanceViewHolder holder) {
        switch (data.getType()) {
            case BLOOD_PRESSURE_REPORT:
                holder.tvName.setText(R.string.blood_pressure_report);
                holder.ivAvatar.setImageResource(R.drawable.ic_blood_pressure);
                holder.tvResult.setText(context.getString(R.string.result) + context.getString(R.string.click_for_details));
                break;
            case SLEEP_REPORT:
                holder.tvName.setText(R.string.sleep_report);
                holder.ivAvatar.setImageResource(R.drawable.ic_sleep);
                holder.tvResult.setText(context.getString(R.string.result) + context.getString(R.string.click_for_details));
                break;
            case DEPRESSION_REPORT:
                holder.tvName.setText(R.string.depression_report);
                holder.tvResult.setText(context.getString(R.string.result) + data.getResult());
                holder.ivAvatar.setImageResource(R.drawable.ic_depression);
                break;
            case CHRONIC_PROSTATE:
                holder.tvName.setText(R.string.chronic_prostate);
                holder.ivAvatar.setImageResource(R.drawable.ic_chronic_prostate);
                holder.tvResult.setText(context.getString(R.string.result) + data.getResult());
                break;
        }
    }



    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
