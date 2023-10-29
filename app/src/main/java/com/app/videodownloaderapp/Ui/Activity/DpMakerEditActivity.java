package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.videodownloaderapp.R;

public class DpMakerEditActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private ImageView IvGallery, IvImgEdit, IvChooseImage;
    private ProgressBar ProgressEdit;
    private LinearLayout LlBorderView, LlFlipView, LlRotateView;
    private ConstraintLayout ConsFlipView, ConsRotateView;
    private TextView TvHorizontal, TvVertical, TvDegree, TvSeekValue;
    private SeekBar SeekRotate;
    private RecyclerView RvAllFrame, RvCategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dp_maker_edit);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        IvGallery = (ImageView) findViewById(R.id.IvGallery);
        IvImgEdit = (ImageView) findViewById(R.id.IvImgEdit);
        ProgressEdit = (ProgressBar) findViewById(R.id.ProgressEdit);
        IvChooseImage = (ImageView) findViewById(R.id.IvChooseImage);
        LlBorderView = (LinearLayout) findViewById(R.id.LlBorderView);
        LlFlipView = (LinearLayout) findViewById(R.id.LlFlipView);
        LlRotateView = (LinearLayout) findViewById(R.id.LlRotateView);
        ConsFlipView = (ConstraintLayout) findViewById(R.id.ConsFlipView);
        ConsRotateView = (ConstraintLayout) findViewById(R.id.ConsRotateView);
        TvHorizontal = (TextView) findViewById(R.id.TvHorizontal);
        TvVertical = (TextView) findViewById(R.id.TvVertical);
        SeekRotate = (SeekBar) findViewById(R.id.SeekRotate);
        TvDegree = (TextView) findViewById(R.id.TvDegree);
        TvSeekValue = (TextView) findViewById(R.id.TvSeekValue);
        RvAllFrame = (RecyclerView) findViewById(R.id.RvAllFrame);
        RvCategoryName = (RecyclerView) findViewById(R.id.RvCategoryName);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText("Edit Image");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IvBack:
                finish();
                break;
        }
    }
}