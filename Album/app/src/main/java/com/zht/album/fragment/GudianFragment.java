package com.zht.album.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zht.album.R;
import com.zht.album.activity.Bigimgactivity;
import com.zht.album.adapter.MeituAdapter;
import com.zht.album.util.ParseJson;

import java.util.List;

/*import com.zht.album.R;*/

/*import com.zht.album.R;*/

/**
 * Created by masterzht on 2017/12/5.
 */

public class GudianFragment extends Fragment {
    private View viewCourse;
    /*private Button button;
*/

    private List<String> meizistest;

    private RecyclerView recyclerview;
    private GridLayoutManager mLayoutManager;
    private MeituAdapter mAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private int lastVisibleItem;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(viewCourse !=null) {
            ViewGroup parent = (ViewGroup) viewCourse.getParent();
            if(parent!=null) {
                parent.removeView(viewCourse);
            }return viewCourse;
        }

        viewCourse = inflater.inflate(R.layout.gudian, container, false);
        meizistest= ParseJson.ParseJsonWithGson("3");

        mAdapter = new MeituAdapter(meizistest,getContext());
        recyclerview = (RecyclerView)viewCourse.findViewById(R.id.grid_recycler);
        recyclerview.setAdapter(mAdapter);
        mLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(mLayoutManager);

        swipeRefreshLayout=viewCourse.findViewById(R.id.grid_swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(R.color.album_ColorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                meizistest= ParseJson.ParseJsonWithGson("3");

                mAdapter = new MeituAdapter(meizistest,getContext());

                recyclerview = (RecyclerView)viewCourse.findViewById(R.id.grid_recycler);
                recyclerview.setAdapter(mAdapter);
                /*
                mLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                recyclerview.setLayoutManager(mLayoutManager);*/
                mAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);

                mAdapter.setOnItemClickListener(new MeituAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String uu=meizistest.get(position);

                        Intent a=new Intent(getActivity(),Bigimgactivity.class);
                        a.putExtra("tup",uu);
                        startActivity(a);

                    }
                });

            }
        });

        mAdapter.setOnItemClickListener(new MeituAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String uu=meizistest.get(position);

                Intent a=new Intent(getActivity(),Bigimgactivity.class);
                a.putExtra("tup",uu);
                startActivity(a);

            }
        });

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
                // 滑动状态停止并且剩余少于两个item时，自动加载下一页
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem +2>=mLayoutManager.getItemCount()) {

                    List<String> more= ParseJson.ParseJsonWithGson("3");
                    meizistest.addAll(more);
                    mAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

            }
        });




        return viewCourse;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }



}


