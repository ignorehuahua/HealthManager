package com.njzhikejia.echohealth.healthlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.entity.UserBaseInfo;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.List;

/**
 * Created by 16222 on 2018/7/22.
 */

public class UserBaseInfoAdapter extends RecyclerView.Adapter<UserBaseInfoAdapter.BaseInfoViewHolder>{

    private static final String TAG = "UserBaseInfoAdapter";
    private Context mContext;
    private List<UserBaseInfo> list;

    public UserBaseInfoAdapter(Context mContext, List<UserBaseInfo> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public BaseInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user_base_info, parent, false);
        BaseInfoViewHolder viewHolder = new BaseInfoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseInfoViewHolder holder, int position) {
        UserBaseInfo userBaseInfo = list.get(position);
        if (userBaseInfo == null) {
            Logger.e(TAG, "userBaseInfo is null");
            return;
        }
        holder.tvLabel.setText(userBaseInfo.getLabel());
        holder.tvValue.setText(userBaseInfo.getValue());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BaseInfoViewHolder extends RecyclerView.ViewHolder{
        private TextView tvLabel;
        private TextView tvValue;

        public BaseInfoViewHolder(View itemView) {
            super(itemView);
            tvLabel = itemView.findViewById(R.id.tv_label);
            tvValue = itemView.findViewById(R.id.tv_value);
        }
    }
}
