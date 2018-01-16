package com.zht.album.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
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
    private adapt mAdapter;

    private FragmentManager fm;
    private FragmentTransaction ft;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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
        new GetImgUrl().execute("http://gank.io/api/data/福利/10/1");

    }

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
        return viewCourse;

    }
//算是oncreateview之后执行的一个方法，不过不会在官方的生命周期图显示，太普通了吧
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initdata();
        initview();

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void initdata() {

        for(int i=0;i<url.length;i++){
            url[i]="http://gank.io/api/data/福利/10/"+"i";
        }

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
                    mAdapter = new adapt(R.layout.fragment1item, meizis);
                    mAdapter.bindToRecyclerView(recyclerview);
                }

                mAdapter.notifyDataSetChanged();

            }
            swipeRefreshLayout.setRefreshing(false);

            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    String uu=((Person)(adapter.getItem(position))).getUrl();
                    /*getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.drawer_layout, BlankFragment2.newInstance(uu,"hh"))
                            .addToBackStack(null)
                            .commit();*/
                    /*fm=getFragmentManager();
                    ft = fm.beginTransaction();
//当前的fragment会被myJDEditFragment替换
                    ft.replace(R.id.drawer_layout, BlankFragment2.newInstance(uu,"hh"));
                    ft.addToBackStack(null);
                    ft.commit();*/
                    Intent a=new Intent(getActivity(),blankactivity2.class);
                    a.putExtra("tup",uu);
                    startActivity(a);

                }
            });



        }
    }



}
