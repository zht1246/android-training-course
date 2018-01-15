package com.zht.album.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bm.library.PhotoView;
import com.squareup.picasso.Picasso;
import com.zht.album.R;

public class blankactivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blankactivity2);

        String str = getIntent().getExtras().getString("tup");
        PhotoView a=(PhotoView)findViewById(R.id.photoview2);
        Picasso.with(getApplicationContext()).load(str).into(a);
    }
}
