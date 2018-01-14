package com.itfollowme.zhihu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.itfollowme.zhihu.R;

public class SplashActivity extends AppCompatActivity {


    private ImageView mIvSplashBg; //m Iv SplashBg

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mIvSplashBg = findViewById(R.id.iv_splash_bg);
        mIvSplashBg.animate().scaleX(1.5f).scaleY(1.5f).alpha(0.5f).setDuration(5000).start();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        },5000);
    }
}
