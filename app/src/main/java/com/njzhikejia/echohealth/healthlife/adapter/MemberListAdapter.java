package com.njzhikejia.echohealth.healthlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.EventLogTags;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.entity.Member;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.List;

/**
 * Created by 16222 on 2018/5/26.
 */

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder>{

    private static final String TAG = "MemberListAdapter";

    private Context context;
    private List<Member> list;

    public MemberListAdapter(Context context, List<Member> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_member_list, parent, false);
        MemberViewHolder viewHolder = new MemberViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        Member member = list.get(position);
        if (member == null) {
            Logger.e(TAG, "member == null!");
            return;
        }
        if (member.getBitmap() == null) {
            holder.ivAvatar.setImageResource(R.drawable.icon_apply_visit);
        } else {
            holder.ivAvatar.setImageBitmap(member.getBitmap());
        }
        holder.tvName.setText(member.getName());
        holder.tvTime.setText(context.getString(R.string.recent_measure_time)+" "+member.getRecentTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Member> memberList) {
        this.list = memberList;
        notifyDataSetChanged();
    }


    class MemberViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvTime;

        public MemberViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_member_name);
            tvTime = itemView.findViewById(R.id.tv_member_measure_time);
        }
    }
}
