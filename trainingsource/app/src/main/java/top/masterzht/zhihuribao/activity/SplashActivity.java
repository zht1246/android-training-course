package top.masterzht.zhihuribao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import top.masterzht.zhihuribao.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH=3000;
    private Handler handler;
    private ImageView mIvWelcomeBg;
    private TextView mTvWelcomeAuthor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mIvWelcomeBg = findViewById(R.id.iv_welcome_bg);
        mTvWelcomeAuthor = findViewById(R.id.tv_welcome_author);
        mIvWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        mTvWelcomeAuthor.setText("北京欢迎你");

        new Handler().postDelayed(

                new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        SplashActivity.this.finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }

                }
                , SPLASH_DISPLAY_LENGTH

        );
    }



}
