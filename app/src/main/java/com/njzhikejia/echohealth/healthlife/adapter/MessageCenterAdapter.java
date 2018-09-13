package com.njzhikejia.echohealth.healthlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.entity.Message;

import java.util.List;

public class MessageCenterAdapter extends RecyclerView.Adapter<MessageCenterAdapter.MessageCenterViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Message> list;

    public MessageCenterAdapter(Context context, List<Message> list) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<Message> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MessageCenterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, null);
        MessageCenterViewHolder viewHolder = new MessageCenterViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MessageCenterViewHolder holder, int position) {
        holder.itemView.setTag(position);
        Message message = list.get(position);
        holder.tvTitle.setText(message.getTitle());
        holder.tvContent.setText(message.getContent());
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


    class MessageCenterViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvContent;

        public MessageCenterViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_msg_title);
            tvContent = itemView.findViewById(R.id.tv_msg_content);
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