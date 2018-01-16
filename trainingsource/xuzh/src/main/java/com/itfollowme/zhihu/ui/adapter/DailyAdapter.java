package com.itfollowme.zhihu.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itfollowme.zhihu.R;
import com.itfollowme.zhihu.ui.model.DailyListBean;

import java.util.List;

/**
 * Created by masterzht on 2018/1/16.
 */

public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DailyListBean.StoryBean> stories;

    Context context;

    public DailyAdapter(List<DailyListBean.StoryBean> stories,Context context) {
        this.stories = stories;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily,parent,false);
        DailyViewHolder dailyViewHolder = new DailyViewHolder(view);
        return dailyViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DailyViewHolder dvHolder = (DailyViewHolder)holder;
        dvHolder.mTvTitle.setText(stories.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }
}
