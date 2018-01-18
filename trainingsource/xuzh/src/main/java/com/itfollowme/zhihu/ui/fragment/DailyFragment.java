package com.itfollowme.zhihu.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.itfollowme.zhihu.R;
import com.itfollowme.zhihu.ui.adapter.DailyAdapter;
import com.itfollowme.zhihu.ui.model.DailyListBean;

import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends Fragment {



    private RecyclerView mRecyclerView;

    private DailyListBean dailyListBean;

    DailyAdapter dailyAdapter;


    public DailyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        parseJSON();
        dailyAdapter = new DailyAdapter(dailyListBean.getStories(),getContext());





        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        mRecyclerView = view.findViewById(R.id.rv_daily_items);
        mRecyclerView.setAdapter(dailyAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        return view;


        //return inflater.inflate(R.layout.fragment_daily, container, false);
    }


    public void parseJSON(){
        try {
            InputStream inputStream = getContext().getAssets().open("news.json");
            int size = inputStream.available();
            byte[] buf = new byte[size];
            inputStream.read(buf);
            String text = new String(buf,"UTF-8");
            dailyListBean = JSON.parseObject(text, DailyListBean.class);

            /*            Log.i("topStorySize",dailyListBean.getTopStories().size()+"");
            for(DailyListBean.TopStoryBean topStoryBean : dailyListBean.getTopStories()){
                Log.i("",topStoryBean.getTitle()+" "+topStoryBean.getImage());
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
