package com.app.videodownloaderapp.Ui.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Models.DpMakerModelItem;
import com.app.videodownloaderapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yalantis.ucrop.view.CropImageView;

import java.util.ArrayList;

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
    private String Images;
    private int Id;
    private String CatName;
    private String ImagesArray;
    private String Items;
    private DpMakerModelItem categoryModel;
    private ArrayList<String> list = new ArrayList<>();

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
        Images = getIntent().getStringExtra(Constants.DPMakerItem);
        Id = getIntent().getIntExtra(Constants.DPMakerPos, 0);
        CatName = getIntent().getStringExtra(Constants.DpMakerName);
        ImagesArray = getIntent().getStringExtra(Constants.DPMakerValue);
        Items = getIntent().getStringExtra(Constants.DpMakerItemList);
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText("Edit Image");
        DpMakerModelItem fromJson = (DpMakerModelItem) new Gson().fromJson(ImagesArray, DpMakerModelItem.class);
        categoryModel = fromJson;
        list = (ArrayList) fromJson.getImages();
        ArrayList arrayList = (ArrayList) new Gson().fromJson(Items, new TypeToken<ArrayList<DpMakerModelItem>>() {
        }.getType());
        loadAnimation();

    }

    private void loadAnimation() {
        RequestOptions requestOptions = new RequestOptions();
        ProgressEdit.setVisibility(View.VISIBLE);
        RequestManager requestManager = Glide.with(this);

        StringBuilder m = new StringBuilder().append("https://api.appcodiz.com/DPMaker/");
        m.append(Items);
        requestManager.load(m.toString()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                ProgressEdit.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                RotateAnimation rotateAnimation = new RotateAnimation(CropImageView.DEFAULT_ASPECT_RATIO, 360.0f, 1, 0.5f, 1, 0.5f);
                rotateAnimation.setDuration(1000);
                rotateAnimation.setInterpolator(new LinearInterpolator());
                IvImgEdit.startAnimation(rotateAnimation);
                return false;
            }
        }).into(IvImgEdit);
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