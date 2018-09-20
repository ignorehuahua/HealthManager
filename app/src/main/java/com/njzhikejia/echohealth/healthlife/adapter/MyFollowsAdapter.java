package com.njzhikejia.echohealth.healthlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.entity.Concerns;
import com.njzhikejia.echohealth.healthlife.entity.MyFollowsData;
import com.njzhikejia.echohealth.healthlife.widget.CircleImageView;

import java.util.List;

/**
 * Created by 16222 on 2018/9/16.
 */

public class MyFollowsAdapter extends RecyclerView.Adapter<MyFollowsAdapter.FollowsViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Concerns> list;

    public MyFollowsAdapter(Context context, List<Concerns> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<Concerns> list) {
        this.list = list;
        notifyDataSetChanged();
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

        Concerns concerns  = (Concerns) list.get(position);
        holder.tvName.setText(concerns.getName());
        holder.tvNumber.setText(concerns.getPhone());
        holder.btnAccept.setVisibility(View.GONE);
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

    class FollowsViewHolder extends RecyclerView.ViewHolder{

        CircleImageView ivAvatar;
        TextView tvName;
        TextView tvNumber;
        Button btnAccept;

        public FollowsViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_follow_avatar);
            tvName = itemView.findViewById(R.id.tv_follow_name);
            tvNumber = itemView.findViewById(R.id.tv_follow_number);
            btnAccept = itemView.findViewById(R.id.btn_accept);

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
