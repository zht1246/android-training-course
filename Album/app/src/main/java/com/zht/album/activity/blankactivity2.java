package com.zht.album.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.bm.library.PhotoView;
import com.squareup.picasso.Picasso;
import com.zht.album.R;

public class blankactivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_blankactivity2);

        String str = getIntent().getExtras().getString("tup");

        PhotoView photoView=(PhotoView)findViewById(R.id.photoview2);
        // 启用图片缩放功能
        photoView.enable();

        Picasso.with(getApplicationContext()).load(str).into(photoView);
    }
}
