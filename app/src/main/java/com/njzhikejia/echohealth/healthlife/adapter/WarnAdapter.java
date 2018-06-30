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
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.List;

/**
 * Created by 16222 on 2018/6/30.
 */

public class WarnAdapter  extends RecyclerView.Adapter<WarnAdapter.WarnViewHolder>{

    private static final String TAG = "WarnAdapter";
    private Context mContext;
    private List<WarnInfo> list;


    public WarnAdapter(Context context, List<WarnInfo> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public WarnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_warn, parent, false);
        WarnViewHolder viewHolder = new WarnViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WarnViewHolder holder, int position) {
        WarnInfo warnInfo = list.get(position);
        if (warnInfo == null) {
            Logger.e(TAG, "warnInfo is null");
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
}
