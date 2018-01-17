package com.zht.album.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.PhotoView;
import com.squareup.picasso.Picasso;
import com.zht.album.R;

import java.util.List;

/**
 * Created by masterzht on 2018/1/16.
 */

public class MeituAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    List<String> meizistest;
    Context context;


    public MeituAdapter(List<String> meizistest,Context context)
    {
        this.meizistest=meizistest;
        this.context=context;

    }


    //与viewholder连接
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment1item,parent,false);
        MeituViewHolder meituViewHolder = new MeituViewHolder(view);

        view.setOnClickListener(this);

        return meituViewHolder;

    }

    //给viewholder里面的元素设置
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MeituViewHolder dvHolder = (MeituViewHolder) holder;
        Picasso.with(context).load(meizistest.get(position)).into((PhotoView)dvHolder.meitu_img);

        holder.itemView.setTag(position);



    }

    @Override
    public int getItemCount() {
        return meizistest.size();
    }

    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
}

