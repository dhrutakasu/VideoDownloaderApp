package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.InstagramPagerAdapter;
import com.app.videodownloaderapp.Ui.Fragments.InstagramAllFragment;
import com.app.videodownloaderapp.Ui.Fragments.InstagramDownloadFragment;
import com.app.videodownloaderapp.Ui.Fragments.InstagramListFragment;

import java.util.ArrayList;

public class InstagramActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private ViewPager PagerInstagram;
    private LinearLayout LLInstagram, LLDownInstagram, LlStoryInstagram;
    private ImageView IvInstagramBottom, IvDownInstagram, IvStoryInstagram;
    private TextView TvInstagramBottom, TvDownInstagram, TvStoryInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        PagerInstagram = (ViewPager) findViewById(R.id.PagerInstagram);
        LLInstagram = (LinearLayout) findViewById(R.id.LLInstagram);
        IvInstagramBottom = (ImageView) findViewById(R.id.IvInstagramBottom);
        TvInstagramBottom = (TextView) findViewById(R.id.TvInstagramBottom);
        LLDownInstagram = (LinearLayout) findViewById(R.id.LLDownInstagram);
        IvDownInstagram = (ImageView) findViewById(R.id.IvDownInstagram);
        TvDownInstagram = (TextView) findViewById(R.id.TvDownInstagram);
        LlStoryInstagram = (LinearLayout) findViewById(R.id.LlStoryInstagram);
        IvStoryInstagram = (ImageView) findViewById(R.id.IvStoryInstagram);
        TvStoryInstagram = (TextView) findViewById(R.id.TvStoryInstagram);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
        LLInstagram.setOnClickListener(this);
        LLDownInstagram.setOnClickListener(this);
        LlStoryInstagram.setOnClickListener(this);
        PagerInstagram.setOnPageChangeListener(this);
    }

    public final void changeTabSelectedColor(LinearLayout linearLayout) {
        LLDownInstagram.setAlpha(0.5f);
        LLDownInstagram.setAlpha(0.5f);
        LLInstagram.setAlpha(0.5f);
        linearLayout.setAlpha(1.0f);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new InstagramListFragment());
        fragments.add(new InstagramDownloadFragment());
        fragments.add(new InstagramAllFragment());
        InstagramPagerAdapter instagramPagerAdapter = new InstagramPagerAdapter(getSupportFragmentManager(), context, fragments);
        PagerInstagram.setAdapter(instagramPagerAdapter);
        changeTabSelectedColor(LLInstagram);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IvBack:
                finish();
                break;
            case R.id.LLInstagram:
                changeTabSelectedColor(LLInstagram);
                PagerInstagram.setCurrentItem(0);
                break;
            case R.id.LLDownInstagram:
                changeTabSelectedColor(LLDownInstagram);
                PagerInstagram.setCurrentItem(1);
                break;
            case R.id.LlStoryInstagram:
                changeTabSelectedColor(LlStoryInstagram);
                PagerInstagram.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                changeTabSelectedColor(LLInstagram);
                break;
            case 1:
                changeTabSelectedColor(LLDownInstagram);
                break;
            case 2:
                changeTabSelectedColor(LlStoryInstagram);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}