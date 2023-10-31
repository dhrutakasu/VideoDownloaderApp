package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.R;
import com.bumptech.glide.Glide;

public class HowToUseActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private String isFromWher;
    private TextView TvHowToDown, TvHowToStep1, TvHowToStep2, TvHowToStep3;
    private ImageView  IvHowToStep2, IvHowToStep3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        TvHowToDown = (TextView) findViewById(R.id.TvHowToDown);
        TvHowToStep1 = (TextView) findViewById(R.id.TvHowToStep1);
        TvHowToStep2 = (TextView) findViewById(R.id.TvHowToStep2);
        IvHowToStep2 = (ImageView) findViewById(R.id.IvHowToStep2);
        TvHowToStep3 = (TextView) findViewById(R.id.TvHowToStep3);
        IvHowToStep3 = (ImageView) findViewById(R.id.IvHowToStep3);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText("How To Use");
        String stringExtra = getIntent().getStringExtra(Constants.ISFromWhere);
        isFromWher = stringExtra;
        if (stringExtra.equalsIgnoreCase("Instagram")) {
            HowToUseAppDetail(Constants.In_Image, Constants.In_Text);
        } else if (this.isFromWher.equalsIgnoreCase("Facebook")) {
            HowToUseAppDetail(Constants.Fb_Image, Constants.Fb_Text);
        } else if (this.isFromWher.equalsIgnoreCase("Twitter")) {
            HowToUseAppDetail(Constants.Twit_Image, Constants.Twit_Text);
        }
    }

    private void HowToUseAppDetail(int[] image, int[] txt) {
        TvHowToStep1.setText(getResources().getString(txt[0]));
        TvHowToStep2.setText(getResources().getString(txt[1]));
        TvHowToStep3.setText(getResources().getString(txt[2]));
        Glide.with(this).load(Integer.valueOf(image[0])).into(IvHowToStep2);
        Glide.with(this).load(Integer.valueOf(image[1])).into(IvHowToStep3);
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