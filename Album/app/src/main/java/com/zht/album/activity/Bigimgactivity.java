package com.zht.album.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.zht.album.R;

public class Bigimgactivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_blankactivity2);

        final String str = getIntent().getExtras().getString("tup");

        PhotoView photoView=(PhotoView)findViewById(R.id.photoview2);
        // 启用图片缩放功能
        photoView.enable();

        Picasso.with(getApplicationContext()).load(str).into(photoView);





        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {



                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(!TextUtils.isEmpty(str)){
                                   Bitmap myBitmap = Glide.with( Bigimgactivity.this)
                                        .load(str)
                                        .asBitmap()
                                        .centerCrop()
                                        .into(500, 1000)
                                        .get();
                                Uri imageUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),myBitmap,"美图","ss"));
                                Intent shareIntent = new Intent();
                                shareIntent.setAction(Intent.ACTION_SEND);
                                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                                shareIntent.setType("image/*");
                                startActivity(Intent.createChooser(shareIntent, "分享到"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();






                return false;
            }
        });
    }
}
