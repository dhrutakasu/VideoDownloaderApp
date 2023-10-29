package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Models.Hashtag;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.HashTagCategoryAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class HashtagCategoriesActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private ImageView IvNoDataCatagory;
    private RecyclerView RvHashtagCategory;
    private String HashTagStr;
    private ArrayList hashtagModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtag_categories);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        RvHashtagCategory = (RecyclerView) findViewById(R.id.RvHashtagCategory);
        IvNoDataCatagory = (ImageView) findViewById(R.id.IvNoDataCatagory);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        IvNoDataCatagory.setVisibility(View.GONE);
        TvTitle.setText(getIntent().getStringExtra(Constants.HashCategoryName));

        hashtagModelArrayList = new Gson().fromJson(getIntent().getStringExtra(Constants.HashCategoryData), new TypeToken<ArrayList<Hashtag>>() {
        }.getType());
        if (hashtagModelArrayList.size() > 0) {
            RvHashtagCategory.setLayoutManager(new GridLayoutManager(context, 2));
            RvHashtagCategory.setAdapter(new HashTagCategoryAdapter(context, hashtagModelArrayList, new HashTagCategoryAdapter.getHashTagModel() {
                @Override
                public void SetClickHashTag(int position, Hashtag hashTagModel) {
                    Intent intent = new Intent(context, HashtagListsActivity.class);
                    intent.putExtra(Constants.HashCategoryName, hashTagModel.getTagName());
                    intent.putExtra(Constants.HashCategoryDetail, hashTagModel.getTagNameDetail());
                    startActivity(intent);
                }
            }));
            RvHashtagCategory.setVisibility(View.VISIBLE);
            IvNoDataCatagory.setVisibility(View.GONE);
        } else {
            RvHashtagCategory.setVisibility(View.GONE);
            IvNoDataCatagory.setVisibility(View.VISIBLE);
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