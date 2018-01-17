package com.zht.album.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.zht.album.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView textReason=findViewById(R.id.hhhh);

        SpannableStringBuilder span = new SpannableStringBuilder("缩进"+textReason.getText());
        span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 2,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textReason.setText(span);
    }
}
