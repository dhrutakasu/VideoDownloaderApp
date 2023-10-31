package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.videodownloaderapp.Const.Constants;
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
    private ImageView IvInstagramTop;
    private ImageView IvUseTop;
    private boolean IsInst = false;

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
        IvInstagramTop = (ImageView) findViewById(R.id.IvInstagramTop);
        IvUseTop = (ImageView) findViewById(R.id.IvUseTop);
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
        IvInstagramTop.setOnClickListener(this);
        IvUseTop.setOnClickListener(this);
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
        IvInstagramTop.setVisibility(View.VISIBLE);
        IvUseTop.setVisibility(View.VISIBLE);
        TvTitle.setText("Instagram");
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
        IsInst = false;
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
            case R.id.IvUseTop:
                GotoHowToUsed();
                break;
            case R.id.IvInstagramTop:
                GotoInstagram();
                break;
        }
    }

    private void GotoHowToUsed() {
        Intent intent = new Intent(context, HowToUseActivity.class);
        intent.putExtra(Constants.ISFromWhere, "instagram");
        startActivity(intent);
    }

    private void GotoInstagram() {
        try {
            getApplicationContext().getPackageManager().getApplicationInfo("com.instagram.android", 0);
            IsInst = true;
        } catch (PackageManager.NameNotFoundException unused) {
            unused.getMessage();
        }
        if (IsInst) {
            Uri parse = Uri.parse("com.instagram.android");
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
            if (intent != null) {
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(parse);
                startActivity(intent);
                Toast.makeText(context, getResources().getString(R.string.toast_instagram_not_found), Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(context, getResources().getString(R.string.toast_instagram_not_found), Toast.LENGTH_LONG).show();
            return;
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