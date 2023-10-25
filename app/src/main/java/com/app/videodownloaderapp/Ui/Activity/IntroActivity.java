package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.R;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitleIntro, TvDescr;
    private ImageView IvIntro;
    private ShimmerFrameLayout ShimmerLoad;
    private CardView CardNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitleIntro = (TextView) findViewById(R.id.TvTitleIntro);
        TvDescr = (TextView) findViewById(R.id.TvDescr);
        IvIntro = (ImageView) findViewById(R.id.IvIntro);
        ShimmerLoad = (ShimmerFrameLayout) findViewById(R.id.ShimmerLoad);
        CardNext = (CardView) findViewById(R.id.CardNext);
    }

    private void VideoInitListerns() {
        CardNext.setOnClickListener(this);
    }

    private void VideoInitActions() {
        ShimmerLoad.setVisibility(View.VISIBLE);
        CardNext.setVisibility(View.GONE);
        Glide.with(context).load(R.drawable.ic_download_fast).into(IvIntro);
        TvTitleIntro.setText(getResources().getString(R.string.faster_download_speed));
        TvDescr.setText(getResources().getString(R.string.description1));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ShimmerLoad.setVisibility(View.GONE);
                CardNext.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.CardNext:
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}