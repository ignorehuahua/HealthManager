package com.njzhikejia.echohealth.healthlife.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.njzhikejia.echohealth.healthlife.R;

import java.util.List;

/**
 * Created by 16222 on 2018/9/23.
 */

public class ShowImageAdapter  extends RecyclerView.Adapter<ShowImageAdapter.ShowImageViewHolder>{

    private Context context;
    private List<Uri> list;
    public static final int MAX_SIZE = 3;

    public ShowImageAdapter(Context context, List<Uri> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<Uri> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ShowImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_show_image, null);
        ShowImageViewHolder viewHolder = new ShowImageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShowImageViewHolder holder, final int position) {
        if (list.size() == 0 || list.size() == position) {
            holder.ivDel.setVisibility(View.GONE);
            holder.ivImg.setImageResource(R.drawable.ic_add);
        } else {
            holder.ivDel.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(list.get(position))
                    .placeholder(R.drawable.ic_add)
                    .into(holder.ivImg);
        }

        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
                mOnItemClickListener.onDeleteClick(position);
            }
        });

        holder.ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onPicker(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size() < MAX_SIZE ? list.size() + 1: list.size();
    }

    class ShowImageViewHolder extends RecyclerView.ViewHolder{

        ImageView ivImg;
        ImageView ivDel;

        public ShowImageViewHolder(View itemView) {
            super(itemView);
            ivImg = itemView.findViewById(R.id.iv_img);
            ivDel = itemView.findViewById(R.id.iv_del);
        }
    }

    public interface OnItemClickListener {
        void onDeleteClick(int position);

        void onPicker(int position);
    }

    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
