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
import com.app.videodownloaderapp.Ui.Adapters.FacebookPagerAdapter;
import com.app.videodownloaderapp.Ui.Fragments.FacebookAllFragment;
import com.app.videodownloaderapp.Ui.Fragments.FacebookDownloadFragment;
import com.app.videodownloaderapp.Ui.Fragments.FacebookListFragment;

import java.util.ArrayList;

public class FacebookActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private ViewPager PagerFacebook;
    private LinearLayout LLFacebook, LLDownFacebook, LlStoryFacebook;
    private ImageView IvFacebookBottom, IvDownFacebook, IvStoryFacebook;
    private TextView TvFacebookBottom, TvDownFacebook, TvStoryFacebook;
    private ImageView IvFacebookTop;
    private ImageView IvUseTop;
    private boolean IsInst = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        IvFacebookTop = (ImageView) findViewById(R.id.IvInstagramTop);
        IvUseTop = (ImageView) findViewById(R.id.IvUseTop);
        PagerFacebook = (ViewPager) findViewById(R.id.PagerFacebook);
        LLFacebook = (LinearLayout) findViewById(R.id.LLFacebook);
        IvFacebookBottom = (ImageView) findViewById(R.id.IvFacebookBottom);
        TvFacebookBottom = (TextView) findViewById(R.id.TvFacebookBottom);
        LLDownFacebook = (LinearLayout) findViewById(R.id.LLDownFacebook);
        IvDownFacebook = (ImageView) findViewById(R.id.IvDownFacebook);
        TvDownFacebook = (TextView) findViewById(R.id.TvDownFacebook);
        LlStoryFacebook = (LinearLayout) findViewById(R.id.LlStoryFacebook);
        IvStoryFacebook = (ImageView) findViewById(R.id.IvStoryFacebook);
        TvStoryFacebook = (TextView) findViewById(R.id.TvStoryFacebook);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
        LLFacebook.setOnClickListener(this);
        LLDownFacebook.setOnClickListener(this);
        LlStoryFacebook.setOnClickListener(this);
        IvFacebookTop.setOnClickListener(this);
        IvUseTop.setOnClickListener(this);
        PagerFacebook.setOnPageChangeListener(this);
    }

    public final void changeTabSelectedColor(LinearLayout linearLayout) {
        LLDownFacebook.setAlpha(0.5f);
        LLDownFacebook.setAlpha(0.5f);
        LLFacebook.setAlpha(0.5f);
        linearLayout.setAlpha(1.0f);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        IvFacebookTop.setImageResource(R.drawable.ic_facebook);
        IvFacebookTop.setVisibility(View.VISIBLE);
        IvUseTop.setVisibility(View.VISIBLE);
        TvTitle.setText("Facebook");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FacebookListFragment());
        fragments.add(new FacebookDownloadFragment());
        fragments.add(new FacebookAllFragment());
        FacebookPagerAdapter FacebookPagerAdapter = new FacebookPagerAdapter(getSupportFragmentManager(), context, fragments);
        PagerFacebook.setAdapter(FacebookPagerAdapter);
        changeTabSelectedColor(LLFacebook);
    }

    @Override
    public void onClick(View v) {
        IsInst = false;
        switch (v.getId()) {
            case R.id.IvBack:
                finish();
                break;
            case R.id.LLFacebook:
                changeTabSelectedColor(LLFacebook);
                PagerFacebook.setCurrentItem(0);
                break;
            case R.id.LLDownFacebook:
                changeTabSelectedColor(LLDownFacebook);
                PagerFacebook.setCurrentItem(1);
                break;
            case R.id.LlStoryFacebook:
                changeTabSelectedColor(LlStoryFacebook);
                PagerFacebook.setCurrentItem(2);
                break;
            case R.id.IvUseTop:
                GotoHowToUsed();
                break;
            case R.id.IvInstagramTop:
                GotoFacebook();
                break;
        }
    }

    private void GotoHowToUsed() {
        Intent intent = new Intent(context, HowToUseActivity.class);
        intent.putExtra(Constants.ISFromWhere, "Facebook");
        startActivity(intent);
    }

    private void GotoFacebook() {
        try {
            getApplicationContext().getPackageManager().getApplicationInfo("com.facebook.katana", 0);
            IsInst = true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (IsInst) {
            Intent intent = new Intent();
            if (intent != null) {
                intent.setAction(Intent.ACTION_VIEW);
                String str;
                try {
                    str = getPackageManager().getPackageInfo("com.facebook.orca", 0).versionCode >= 3002850 ? "fb://facewebmodal/f?href=https://www.facebook.com/" : "fb://page/YourPageName";
                } catch (PackageManager.NameNotFoundException unused2) {
                    str = "https://www.facebook.com/";
                }
                intent.setData(Uri.parse(str));
                startActivity(intent);
                return;
            }
            return;
        }
        Toast.makeText(context, getResources().getString(R.string.toast_facebook_not_found), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                changeTabSelectedColor(LLFacebook);
                break;
            case 1:
                changeTabSelectedColor(LLDownFacebook);
                break;
            case 2:
                changeTabSelectedColor(LlStoryFacebook);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}