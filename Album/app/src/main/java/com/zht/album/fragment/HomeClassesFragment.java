package com.zht.album.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zht.album.MyOkhttp;
import com.zht.album.Person;
import com.zht.album.R;
import com.zht.album.adapt;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by masterzht on 2017/12/5.
 */

public class HomeClassesFragment extends Fragment {

    private static String url="http://gank.io/api/data/福利/10/";
    private View viewCourse;
    private  RecyclerView recyclerview;
    private GridLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int lastVisibleItem;
    private int page=1;
    private ItemTouchHelper itemTouchHelper;
    private List<Person> meizis;
    private adapt mAdapter;
    private CoordinatorLayout coordinatorLayout;
    private BlankFragment blankFragment;
    private PhotoView tt;
    Info mInfo;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public static HomeClassesFragment newInstance(String param1, String param2) {
        HomeClassesFragment fragment = new HomeClassesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        //为什么说是工厂方法，其实这个方法是让我们去实现的，这样的话可以统一传递数据过来，然后在oncreate方法处理
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        if(viewCourse !=null) {


            ViewGroup parent = (ViewGroup) viewCourse.getParent();


            if(parent!=null) {


                parent.removeView(viewCourse);


            }return viewCourse;


        }

        viewCourse = inflater.inflate(R.layout.sw,container, false);
        return viewCourse;





    }





   /* public void onActivityCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


         //tt=(PhotoView) view.findViewById(R.id.img2) ;

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.grid_swipe_refresh);

        recyclerview = (RecyclerView) view.findViewById(R.id.grid_recycler);
        mLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(mLayoutManager);

        swipeRefreshLayout.setProgressViewOffset(false, 0,  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        new GetData().execute("http://gank.io/api/data/福利/10/1","http://gank.io/api/data/福利/10/2");

        //下拉刷新的实现
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //定时器，3秒后执行
                *//*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
        ...
                        adapter.addRefreshBeans(temp);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);*//*

                //page=1;
                new GetData().execute("http://gank.io/api/data/福利/10/1");

            }
        });
//看不懂
        itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags=0;
                if(recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager ||recyclerView.getLayoutManager() instanceof GridLayoutManager){
                    dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
                }
                return makeMovementFlags(dragFlags,0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from=viewHolder.getAdapterPosition();
                int to=target.getAdapterPosition();
                Person moveItem=meizis.get(from);
                meizis.remove(from);
                meizis.add(to,moveItem);
                mAdapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }
        });

//recyclerview滚动监听，上拉刷新监听接口
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
                // 滑动状态停止并且剩余少于两个item时，自动加载下一页
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem +2>=mLayoutManager.getItemCount()) {

                    //开始刷新图片
                    //swipeRefreshLayout.setRefreshing(true);
                    new GetData().execute("http://gank.io/api/data/福利/10/"+(++page));
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                获取加载的最后一个可见视图在适配器的位置。
             lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

            }
        });




    }*/




    private class GetData extends AsyncTask<String, Integer, String> {

       /* View mParent;
        View mBg;
        PhotoView mPhotoView;
        PhotoView mPhotoView2;
        Info mInfo;*/



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //设置swipeRefreshLayout为刷新状态，展开刷新动画
            /*swipeRefreshLayout.setRefreshing(true);*/



        }

        @Override
        protected String doInBackground(String... params) {

            return MyOkhttp.get(params[0]);


        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(!TextUtils.isEmpty(result)){

                JSONObject jsonObject;
                Gson gson=new Gson();
                String jsonData=null;

                try {
                    jsonObject = new JSONObject(result);
                    jsonData = jsonObject.getString("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//第一次打开，是空的，所以要加载
                if(meizis==null||meizis.size()==0){
                    meizis= gson.fromJson(jsonData, new TypeToken<List<Person>>() {}.getType());
                }else{

                    List<Person> more= gson.fromJson(jsonData, new TypeToken<List<Person>>() {}.getType());
                    meizis.addAll(more);
                }

//这里才是关键,要用item文件，老是出错
                /*recyclerview.setAdapter(mAdapter=new adapt(R.layout.fragment1item,meizis));
                mAdapter.notifyDataSetChanged();*/

                if(mAdapter==null) {
                    mAdapter = new adapt(R.layout.fragment1item, meizis);
                    mAdapter.bindToRecyclerView(recyclerview);
                }

                mAdapter.notifyDataSetChanged();
                itemTouchHelper.attachToRecyclerView(recyclerview);
                mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        /*Inf mRectF= (Inf) ((PhotoView)view).getInfo();

                        */
                        view.setVisibility(View.INVISIBLE);
                        FragmentManager manager=getFragmentManager();
                        FragmentTransaction ft=manager.beginTransaction();
                        ft.replace(R.id.test, blankFragment);
                        ft.addToBackStack(null);
                        ft.commit();

                        String item=(String)adapter.getItem(position);

                        Bundle bundle=new Bundle();
                        bundle.putString("tt", item);
                        blankFragment.setArguments(bundle);


                        /*mInfo = PhotoView.getImageViewInfo((PhotoView) view);
                        recyclerview.setVisibility(View.GONE);

                        tt.setImageResource(R.drawable.ic_broken_image);
                        tt.setVisibility(View.VISIBLE);
                        //tt.setImageBitmap(view.getDrawingCache());
                        tt.animaFrom(mInfo);*/
                        //tt.setImageDrawable(((PhotoView) view).getDrawable());



                        //Picasso.with(getContext()).load(view.getDrawingCache()).into(tt);



                    }
                });









                /*mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Log.d("hh", "onItemClick: 我嘞个去");
                    }
                });*/




            }
//停止swipeRefreshLayout加载动画
            swipeRefreshLayout.setRefreshing(false);
        }
    }




}
