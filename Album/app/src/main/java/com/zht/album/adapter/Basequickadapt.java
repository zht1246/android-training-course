package com.zht.album.adapter;

import android.util.Log;
import android.view.View;

import com.bm.library.PhotoView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.zht.album.R;

import java.util.List;

/**
 * Created by masterzht on 2017/12/15.
 */

public class Basequickadapt extends BaseQuickAdapter<String, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener{



    public Basequickadapt(int layoutResId, List data) {
        super(layoutResId, data);
        this.setOnItemClickListener(this);
        //this.setOnItemChildClickListener(this);

    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {


        Picasso.with(mContext).load(item).into((PhotoView)helper.getView(R.id.iv));
        helper.addOnClickListener(R.id.iv);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Log.d("hh", "onItemClick:神神叨叨 ");

    }

    /*public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        //PhotoView a2=view.findViewById(R.id.iv2);

        Log.d("hh", "onItemChildClick: ddddddd");
//获取img1的信息
        mRectF=((PhotoView)view).getInfo();
        view.setVisibility(View.INVISIBLE);
        //a2.setVisibility(View.VISIBLE);

        //PhotoView v2=(PhotoView)adapter.getViewByPosition(position,R.id.iv2);
        //v2.setVisibility(View.VISIBLE);

        //让img2从img1的位置变换到他本身的位置
        //v2.animaFrom(mRectF);

    }
*/


}