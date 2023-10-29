package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Helper.CaptionHelper;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.CaptionDetailAdapter;

public class CaptionCategoryDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private RecyclerView RvCaptionCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caption_category_details);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        RvCaptionCategory = (RecyclerView) findViewById(R.id.RvCaptionDetail);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText(getIntent().getStringExtra(Constants.CaptionCategory));
        RvCaptionCategory.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        CaptionDetailAdapter detailAdapter = new CaptionDetailAdapter(context, new CaptionHelper(context).getStatus(getIntent().getStringExtra(Constants.CaptionCategory)));
        RvCaptionCategory.setAdapter(detailAdapter);
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