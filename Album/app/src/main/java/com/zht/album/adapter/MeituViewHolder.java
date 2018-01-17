package com.zht.album.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bm.library.PhotoView;
import com.zht.album.R;

/**
 * Created by masterzht on 2018/1/16.
 */

public class MeituViewHolder extends RecyclerView.ViewHolder {

    PhotoView meitu_img;
    public MeituViewHolder(View itemView) {
        super(itemView);
        meitu_img=itemView.findViewById(R.id.iv);
    }



}
