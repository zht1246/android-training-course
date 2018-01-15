package com.zht.album.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zht.album.R;

import java.util.ArrayList;

/*import com.zht.album.R;*/

/*import com.zht.album.R;*/

/**
 * Created by masterzht on 2017/12/5.
 */

public class HomeExamFragment extends Fragment {
    private View viewCourse;
    /*private Button button;
*/


    ListView lv;
    ArrayAdapter Adapter;
    ArrayList arr=new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewCourse = inflater.inflate(R.layout.main, container, false);
        return viewCourse;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(arr.size()>0) return;

        lv = (ListView) view.findViewById(R.id.lv);
        arr.add("123");
        arr.add("234");
        arr.add("345");
        Adapter = new ArrayAdapter(getContext(), R.layout.playlist, arr);
        lv.setAdapter(Adapter);
        lv.setOnItemClickListener(lvLis);
        editItem edit = new editItem();
        edit.execute("0", "第1项");//把第一项内容改为"第一项"
        Handler handler = new Handler();
        handler.postDelayed(add, 3000);//延迟3秒执行

       /* TextView tt=(TextView)viewCourse.findViewById(R.id.tt);
        tt.setText("3");}*/

    /*@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        button = (Button) view.findViewById(R.id.btn_pick_photo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//先不要实现这个library，有点小问题
                //startActivity(new Intent(getContext(), com.github.skykai.ui.PhotoPickerActivity.class));
            }
        });



    }*/

    }
    Runnable add=new Runnable(){

        @Override
        public void run() {
            // TODO Auto-generated method stub
            arr.add("增加一项");//增加一项
            Adapter.notifyDataSetChanged();
        }
    };
    class editItem extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... params) {
            arr.set(Integer.parseInt(params[0]),params[1]);
            //params得到的是一个数组，params[0]在这里是"0",params[1]是"第1项"
            //Adapter.notifyDataSetChanged();
            //执行添加后不能调用 Adapter.notifyDataSetChanged()更新UI，因为与UI不是同线程
            //下面的onPostExecute方法会在doBackground执行后由UI线程调用
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            Adapter.notifyDataSetChanged();
            //执行完毕，更新UI
        }

    }

    private AdapterView.OnItemClickListener lvLis=new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView arg0, View arg1, int arg2,
                                long arg3) {
            //点击条目时触发
            //arg2即为点中项的位置
            //setTitle(String.valueOf(arr.get(arg2)));

        }

    };
}


