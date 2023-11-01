package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Models.DpMakerModelItem;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.DPMTitleMakerAdapter;
import com.app.videodownloaderapp.Ui.Adapters.DPMakerPagerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DPMakerActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private RecyclerView RvDpFrame;
    private ViewPager PagerDpFrame;
    private ProgressBar ProgressBarDp;
    private ImageView TvNoDpFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpmaker);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        RvDpFrame = (RecyclerView) findViewById(R.id.RvDpFrame);
        PagerDpFrame = (ViewPager) findViewById(R.id.PagerDpFrame);
        ProgressBarDp = (ProgressBar) findViewById(R.id.ProgressBarDp);
        TvNoDpFrame = (ImageView) findViewById(R.id.TvNoDpFrame);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText("DP Maker");
        String str;
        try {
            InputStream inputStream = getAssets().open("dpmaker.json");
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();
            str = new String(bytes, "UTF-8");
            System.out.println("------- - -- srr : "+str);
            ArrayList arrayList = (ArrayList) new Gson().fromJson(str, new TypeToken<List<DpMakerModelItem>>() {
            }.getType());

//        https://api.appcodiz.com/DPMaker/storage/Animal%20Print/1.webp
//        https://api.appcodiz.com/DPMaker/storage/Badge/1.webp
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            if (arrayList.size() > 0) {

                RvDpFrame.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                DPMTitleMakerAdapter dpmTitleMakerAdapter = new DPMTitleMakerAdapter(context, arrayList, new DPMTitleMakerAdapter.getStatusModel() {
                    @Override
                    public void SetClickHashTag(int position, DpMakerModelItem statusType) {
                        PagerDpFrame.setCurrentItem(position);
                    }
                });
                RvDpFrame.setAdapter(dpmTitleMakerAdapter);
                PagerDpFrame.setAdapter(new DPMakerPagerAdapter(getSupportFragmentManager(), context, arrayList));
                PagerDpFrame.setOffscreenPageLimit(arrayList.size());
                PagerDpFrame.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        RvDpFrame.getLayoutManager().scrollToPosition(position);
                        dpmTitleMakerAdapter.selectedPosition = position;
                        dpmTitleMakerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                PagerDpFrame.setCurrentItem(0);
                TvNoDpFrame.setVisibility(View.GONE);
            } else {
                TvNoDpFrame.setVisibility(View.VISIBLE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("------- - -- srr e : "+e.getMessage());
            str = null;
        }
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