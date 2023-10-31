package com.app.videodownloaderapp.Ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.TwitterPagerAdapter;
import com.app.videodownloaderapp.Ui.Fragments.TwitterDownloadFragment;
import com.app.videodownloaderapp.Ui.Fragments.TwitterListFragment;

import java.util.ArrayList;

public class TwitterActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private ViewPager PagerTwitter;
    private LinearLayout LLTwitter, LLDownTwitter;
    private ImageView IvTwitterBottom, IvDownTwitter, IvStoryTwitter;
    private TextView TvTwitterBottom, TvDownTwitter, TvStoryTwitter;
    private ImageView IvTwitterTop;
    private ImageView IvUseTop;
    private boolean IsInst = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        IvTwitterTop = (ImageView) findViewById(R.id.IvInstagramTop);
        IvUseTop = (ImageView) findViewById(R.id.IvUseTop);
        PagerTwitter = (ViewPager) findViewById(R.id.PagerTwitter);
        LLTwitter = (LinearLayout) findViewById(R.id.LLTwitter);
        IvTwitterBottom = (ImageView) findViewById(R.id.IvTwitterBottom);
        TvTwitterBottom = (TextView) findViewById(R.id.TvTwitterBottom);
        LLDownTwitter = (LinearLayout) findViewById(R.id.LLDownTwitter);
        IvDownTwitter = (ImageView) findViewById(R.id.IvDownTwitter);
        TvDownTwitter = (TextView) findViewById(R.id.TvDownTwitter);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
        LLTwitter.setOnClickListener(this);
        LLDownTwitter.setOnClickListener(this);
        IvTwitterTop.setOnClickListener(this);
        IvUseTop.setOnClickListener(this);
        PagerTwitter.setOnPageChangeListener(this);
    }

    public final void changeTabSelectedColor(LinearLayout linearLayout) {
        LLDownTwitter.setAlpha(0.5f);
        LLDownTwitter.setAlpha(0.5f);
        LLTwitter.setAlpha(0.5f);
        linearLayout.setAlpha(1.0f);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        IvTwitterTop.setImageResource(R.drawable.ic_twitter);
        IvTwitterTop.setVisibility(View.VISIBLE);
        IvUseTop.setVisibility(View.VISIBLE);
        TvTitle.setText("Twitter");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new TwitterListFragment());
        fragments.add(new TwitterDownloadFragment());
        TwitterPagerAdapter TwitterPagerAdapter = new TwitterPagerAdapter(getSupportFragmentManager(), context, fragments);
        PagerTwitter.setAdapter(TwitterPagerAdapter);
        changeTabSelectedColor(LLTwitter);
    }

    @Override
    public void onClick(View v) {
        IsInst = false;
        switch (v.getId()) {
            case R.id.IvBack:
                finish();
                break;
            case R.id.LLTwitter:
                changeTabSelectedColor(LLTwitter);
                PagerTwitter.setCurrentItem(0);
                break;
            case R.id.LLDownTwitter:
                changeTabSelectedColor(LLDownTwitter);
                PagerTwitter.setCurrentItem(1);
                break;
            case R.id.IvUseTop:
                GotoHowToUsed();
                break;
            case R.id.IvInstagramTop:
                GotoTwitter();
                break;
        }
    }

    private void GotoHowToUsed() {
        Intent intent = new Intent(context, HowToUseActivity.class);
        intent.putExtra(Constants.ISFromWhere, "Twitter");
        startActivity(intent);
    }

    private void GotoTwitter() {
        try {
            getApplicationContext().getPackageManager().getApplicationInfo("com.twitter.android", 0);
            IsInst = true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (IsInst) {
            Intent intent = new Intent();
            if (intent != null) {
                intent.setAction(Intent.ACTION_VIEW);
                intent.setClassName("com.twitter.android", "com.twitter.android.ProfileActivity");
                startActivity(intent);
                return;
            }
            return;
        }
        Toast.makeText(context, getResources().getString(R.string.toast_twitter_not_found), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                changeTabSelectedColor(LLTwitter);
                break;
            case 1:
                changeTabSelectedColor(LLDownTwitter);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}