package com.njzhikejia.echohealth.healthlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.entity.HealthGuidance;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.List;

/**
 * Created by 16222 on 2018/5/28.
 */

public class HealthGuidanceAdapter extends RecyclerView.Adapter<HealthGuidanceAdapter.HealthGuidanceViewHolder> {

    private static final String TAG = "HealthGuidanceAdapter";
    private Context context;
    private List<HealthGuidance> list;

    public HealthGuidanceAdapter(Context context, List<HealthGuidance> list) {
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
        HealthGuidance healthGuidance = list.get(position);
        if (healthGuidance == null) {
            Logger.e(TAG, "healthGuidance == null!");
            return;
        }

        holder.tvName.setText(healthGuidance.getHealthGuideName());
        holder.tvTime.setText(healthGuidance.getHealthGuideTime());
        holder.ivAvatar.setImageResource(R.drawable.blood_pressure);
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
}
