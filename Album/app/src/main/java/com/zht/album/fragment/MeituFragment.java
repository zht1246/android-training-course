package com.zht.album.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zht.album.Person;
import com.zht.album.R;
import com.zht.album.activity.blankactivity2;
import com.zht.album.adapter.MeituAdapter;
import com.zht.album.util.MyOkhttp;
import com.zht.album.util.ParseJson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeituFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeituFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeituFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerview;
    private GridLayoutManager mLayoutManager;


    private String[]url=new String[20];
    private View viewCourse;
    private List<Person> meizis;
    private List<String> meizistest;
    //private Basequickadapt mAdapter;
    private MeituAdapter mAdapter;

    private int lastVisibleItem;

    public MeituFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeituFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeituFragment newInstance(String param1, String param2) {
        MeituFragment fragment = new MeituFragment();
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
        //new GetImgUrl().execute("http://gank.io/api/data/福利/10/1");

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(viewCourse !=null) {
            ViewGroup parent = (ViewGroup) viewCourse.getParent();
            if(parent!=null) {
                parent.removeView(viewCourse);
            }return viewCourse;
        }

        viewCourse = inflater.inflate(R.layout.sw,container, false);
        //获取美图地址，这属于初始化
        meizistest= ParseJson.ParseJsonWithGson();

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

                meizistest= ParseJson.ParseJsonWithGson();

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

                        Intent a=new Intent(getActivity(),blankactivity2.class);
                        a.putExtra("tup",uu);
                        startActivity(a);

                    }
                });

            }
        });

//点击事件参考http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0327/2647.html
        mAdapter.setOnItemClickListener(new MeituAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String uu=meizistest.get(position);

                Intent a=new Intent(getActivity(),blankactivity2.class);
                a.putExtra("tup",uu);
                startActivity(a);

            }
        });

//下拉加载更多
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
                // 滑动状态停止并且剩余少于两个item时，自动加载下一页
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem +2>=mLayoutManager.getItemCount()) {

                    //swipeRefreshLayout.setRefreshing(true);

                    meizistest= ParseJson.ParseJsonWithGson();

                    mAdapter = new MeituAdapter(meizistest,getContext());

                    recyclerview = (RecyclerView)viewCourse.findViewById(R.id.grid_recycler);
                    recyclerview.setAdapter(mAdapter);
                /*
                mLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
                recyclerview.setLayoutManager(mLayoutManager);*/
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






    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    //结束fragment，要销毁了
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void initdata() {

        for(int i=0;i<url.length;i++){
            url[i]="http://gank.io/api/data/福利/10/"+"i";
        }


        if (meizistest == null || meizistest.size() == 0) {
            meizistest= ParseJson.ParseJsonWithGson();
        } else {
            List<String> more = ParseJson.ParseJsonWithGson();
            meizistest.addAll(more);
        }

        if (mAdapter == null) {
            //mAdapter = new Basequickadapt(R.layout.fragment1item, meizistest);
            //mAdapter.bindToRecyclerView(recyclerview);
        }

        mAdapter.notifyDataSetChanged();


    }


    public void initview() {



        swipeRefreshLayout = (SwipeRefreshLayout)getView().findViewById(R.id.grid_swipe_refresh);
        recyclerview = (RecyclerView)getView().findViewById(R.id.grid_recycler);
        mLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(mLayoutManager);

        swipeRefreshLayout.setProgressViewOffset(false, 0,  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

    }



    private class GetImgUrl extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            //得到传过来的第一个数据并放置于子线程执行
            //把得到的页面的json文件发给onpostExcute
            return MyOkhttp.get(strings[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //如果有传过来json数据的话
            if (!TextUtils.isEmpty(result)) {

                JSONObject jsonObject;
                Gson gson = new Gson();
                String jsonData = null;

                try {
                    jsonObject = new JSONObject(result);
                    jsonData = jsonObject.getString("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (meizis == null || meizis.size() == 0) {
                    meizis = gson.fromJson(jsonData, new TypeToken<List<Person>>() {
                    }.getType());
                } else {
                    List<Person> more = gson.fromJson(jsonData, new TypeToken<List<Person>>() {
                    }.getType());
                    meizis.addAll(more);
                }

                if (mAdapter == null) {
                   // mAdapter = new Basequickadapt(R.layout.fragment1item, meizis);
                   // mAdapter.bindToRecyclerView(recyclerview);
                }

                mAdapter.notifyDataSetChanged();

            }
            swipeRefreshLayout.setRefreshing(false);



           /*mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    String uu=((Person)(adapter.getItem(position))).getUrl();

                    Intent a=new Intent(getActivity(),blankactivity2.class);
                    a.putExtra("tup",uu);
                    startActivity(a);

                }
            });*/



        }
    }



}

