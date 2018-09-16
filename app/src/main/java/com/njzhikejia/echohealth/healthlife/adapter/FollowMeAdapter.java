package com.njzhikejia.echohealth.healthlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.entity.FollowMeData;
import com.njzhikejia.echohealth.healthlife.entity.MyFollowsData;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.widget.CircleImageView;

import java.util.List;

/**
 * Created by 16222 on 2018/9/16.
 */

public class FollowMeAdapter extends RecyclerView.Adapter<FollowMeAdapter.FollowsViewHolder> implements View.OnClickListener {

    private Context context;
    private List<FollowMeData.Data.Concerneds> list;

    public FollowMeAdapter(Context context, List<FollowMeData.Data.Concerneds> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<FollowMeData.Data.Concerneds> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    @Override
    public FollowsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_follows, null);
        FollowsViewHolder viewHolder = new FollowsViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FollowsViewHolder holder, int position) {
        holder.itemView.setTag(position);

        FollowMeData.Data.Concerneds concerns = list.get(position);
        holder.tvName.setText(concerns.getName());
        holder.tvNumber.setText(concerns.getPhone());
        matchStatus(concerns, holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class FollowsViewHolder extends RecyclerView.ViewHolder {

        CircleImageView ivAvatar;
        TextView tvName;
        TextView tvNumber;
        Button btnAccept;
        TextView tvResult;

        public FollowsViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_follow_avatar);
            tvName = itemView.findViewById(R.id.tv_follow_name);
            tvNumber = itemView.findViewById(R.id.tv_follow_number);
            btnAccept = itemView.findViewById(R.id.btn_accept);
            tvResult = itemView.findViewById(R.id.tv_apply_satus);

        }
    }

    private void matchStatus(final FollowMeData.Data.Concerneds concerneds, FollowsViewHolder viewHolder, final int position) {
        switch (concerneds.getStatus()) {
            case ConstantValues.STATUS_APPLY:
                viewHolder.btnAccept.setVisibility(View.VISIBLE);
                viewHolder.tvResult.setVisibility(View.GONE);
                viewHolder.btnAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onBtnAccpetOnClick(position);
                    }
                });
                break;
            case ConstantValues.STATUS_DONE:
                viewHolder.btnAccept.setVisibility(View.GONE);
                viewHolder.tvResult.setVisibility(View.VISIBLE);
                viewHolder.tvResult.setText(R.string.added_already);
                break;
            case ConstantValues.STATUS_REFUSE:
                viewHolder.btnAccept.setVisibility(View.GONE);
                viewHolder.tvResult.setVisibility(View.VISIBLE);
                viewHolder.tvResult.setText(R.string.refused_already);
                break;
        }
        if (concerneds.getStatus() == ConstantValues.STATUS_APPLY) {


        } else {
            viewHolder.btnAccept.setVisibility(View.GONE);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onBtnAccpetOnClick(int position);
    }

    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
