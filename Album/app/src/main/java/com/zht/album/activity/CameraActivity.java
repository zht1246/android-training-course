package com.zht.album.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.task.DefaultAlbumLoader;
import com.zht.album.R;

public class CameraActivity extends AppCompatActivity {



    TextView mTextView;
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = findViewById(R.id.camera_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("相机");//父标题
        //assert actionBar != null;


        mTextView = findViewById(R.id.camera_tv_message);
        mImageView = findViewById(R.id.camera_image_view);
    }

    private void takePicture() {
        Album.camera(this)
                .image()
//                .filePath()
                .requestCode(2)
                .onResult(new Action<String>() {
                    @Override
                    public void onAction(int requestCode, @NonNull String result) {
                        mTextView.setText(result);

                        DefaultAlbumLoader.getInstance()
                                .loadImage(mImageView, result, 720, 1280);
                    }
                })
                .onCancel(new Action<String>() {
                    @Override
                    public void onAction(int requestCode, @NonNull String result) {
                        Toast.makeText(CameraActivity.this, "取消", Toast.LENGTH_LONG).show();
                    }
                })
                .start();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_album_camera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                finish();
                break;
            }
            case R.id.menu_image_capture: {
                takePicture();
                break;
            }

        }
        return true;
    }
}
