package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.PreviewAdapter;
import com.google.android.material.tabs.TabLayout;
import com.yalantis.ucrop.view.CropImageView;

public class PreviewActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private TabLayout TabPreview;
    private ViewPager PagerPreview;
    private String PreviewImagePath, PreviewImageBorder;
    private Float FlipHorizontal, FlipVertical, Rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        TabPreview = (TabLayout) findViewById(R.id.TabPreview);
        PagerPreview = (ViewPager) findViewById(R.id.PagerPreview);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText("Preview");
        PreviewImagePath = getIntent().getStringExtra(Constants.PERVIEW);
        PreviewImageBorder = getIntent().getStringExtra(Constants.SELECTED_BORDER);
        FlipHorizontal = Float.valueOf(getIntent().getFloatExtra(Constants.FLIP_HORIZONTAL, CropImageView.DEFAULT_ASPECT_RATIO));
        FlipVertical = Float.valueOf(getIntent().getFloatExtra(Constants.FLIP_VERTICAL, CropImageView.DEFAULT_ASPECT_RATIO));
        Rotate = Float.valueOf(getIntent().getFloatExtra(Constants.ROTATE, CropImageView.DEFAULT_ASPECT_RATIO));


        String[] strings = new String[]{getResources().getString(R.string.instagram), getResources().getString(R.string.facebook), getResources().getString(R.string.whatsapp)};
        for (int i = 0; i < strings.length; i++) {
            TabPreview.addTab(TabPreview.newTab().setText(strings[i].toString()));
        }
        PagerPreview.setAdapter(new PreviewAdapter(getSupportFragmentManager(), PreviewImagePath, PreviewImageBorder, FlipHorizontal, FlipVertical, Rotate, TabPreview.getTabCount()));
        PagerPreview.setOffscreenPageLimit(3);
        PagerPreview.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(TabPreview));
        TabPreview.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public final void onTabSelected(TabLayout.Tab tab) {
                PagerPreview.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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