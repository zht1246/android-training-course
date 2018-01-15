package com.zht.album.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zht.album.R;


/**
 * Created by masterzht on 2017/12/5.
 */

public class HomeAttendanceFragment extends Fragment {

    private View viewCourse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewCourse = inflater.inflate(R.layout.ss,container, false);
        return viewCourse;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tt=(TextView)viewCourse.findViewById(R.id.tt);
        tt.setText("2");

    }

}
