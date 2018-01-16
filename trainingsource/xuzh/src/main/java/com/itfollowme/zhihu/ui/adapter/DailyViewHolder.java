package com.itfollowme.zhihu.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itfollowme.zhihu.R;

/**
 * Created by masterzht on 2018/1/16.
 */

public class DailyViewHolder extends RecyclerView.ViewHolder{

    TextView mTvTitle;
    ImageView mIvImage;

    public DailyViewHolder(View itemView) {
        super(itemView);
        mTvTitle = itemView.findViewById(R.id.tv_daily_item_title);
        mIvImage = itemView.findViewById(R.id.iv_daily_item_image);
    }
}
