package com.app.videodownloaderapp.Ui.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class StoryActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private TextView TvTitle;
    private ImageView IvBack, IvInstagramTop, IvUseTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        IvInstagramTop = (ImageView) findViewById(R.id.IvInstagramTop);
        IvUseTop = (ImageView) findViewById(R.id.IvUseTop);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
        IvInstagramTop.setOnClickListener(this);
        IvUseTop.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        IvInstagramTop.setVisibility(View.VISIBLE);
        IvUseTop.setVisibility(View.VISIBLE);
        TvTitle.setText("Instagram");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IvBack:
                finish();
                break;
            case R.id.IvInstagramTop:
                finish();
                break;
            case R.id.IvUseTop:
                finish();
                break;
        }
    }
}