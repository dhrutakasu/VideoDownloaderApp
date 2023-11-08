package com.app.videodownloaderapp.Ui.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.WhatsappPagerAdapter;
import com.app.videodownloaderapp.Ui.Fragments.WhatsappDownloadFragment;
import com.app.videodownloaderapp.Ui.Fragments.WhatsappListFragment;

import java.util.ArrayList;

public class WhatsappActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;

    private ViewPager PagerWhatsapp;
    private LinearLayout LLWhatsapp, LLDownWhatsapp;
    private ImageView IvWhatsappBottom, IvDownWhatsapp;
    private TextView TvWhatsappBottom, TvDownWhatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapp);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        PagerWhatsapp = (ViewPager) findViewById(R.id.PagerWhatsapp);
        LLWhatsapp = (LinearLayout) findViewById(R.id.LLWhatsapp);
        IvWhatsappBottom = (ImageView) findViewById(R.id.IvWhatsappBottom);
        TvWhatsappBottom = (TextView) findViewById(R.id.TvWhatsappBottom);
        LLDownWhatsapp = (LinearLayout) findViewById(R.id.LLDownWhatsapp);
        IvDownWhatsapp = (ImageView) findViewById(R.id.IvDownWhatsapp);
        TvDownWhatsapp = (TextView) findViewById(R.id.TvDownWhatsapp);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
        LLWhatsapp.setOnClickListener(this);
        LLDownWhatsapp.setOnClickListener(this);
        PagerWhatsapp.setOnPageChangeListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText("Whatsapp");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new WhatsappListFragment());
        fragments.add(new WhatsappDownloadFragment());
        WhatsappPagerAdapter WhatsappPagerAdapter = new WhatsappPagerAdapter(getSupportFragmentManager(), context, fragments);
        PagerWhatsapp.setAdapter(WhatsappPagerAdapter);
        changeTabSelectedColor(LLWhatsapp);
    }

    public final void changeTabSelectedColor(LinearLayout linearLayout) {
        LLDownWhatsapp.setAlpha(0.5f);
        LLDownWhatsapp.setAlpha(0.5f);
        LLWhatsapp.setAlpha(0.5f);
        linearLayout.setAlpha(1.0f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IvBack:
                finish();
                break;
            case R.id.LLWhatsapp:
                changeTabSelectedColor(LLWhatsapp);
                PagerWhatsapp.setCurrentItem(0);
                break;
            case R.id.LLDownWhatsapp:
                changeTabSelectedColor(LLDownWhatsapp);
                PagerWhatsapp.setCurrentItem(1);
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
                changeTabSelectedColor(LLWhatsapp);
                break;
            case 1:
                changeTabSelectedColor(LLDownWhatsapp);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}