package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Helper.CaptionHelper;
import com.app.videodownloaderapp.Helper.QuoteHelper;
import com.app.videodownloaderapp.Models.CategoryDb;
import com.app.videodownloaderapp.Models.StatusType;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.CaptionAdapter;
import com.app.videodownloaderapp.Ui.Adapters.QuoteAdapter;

public class CaptionActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private RecyclerView RvCaptionDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caption);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        RvCaptionDetail = (RecyclerView) findViewById(R.id.RvCaptionCategory);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText("Caption");
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                new CaptionHelper(context);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                RvCaptionDetail.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                CaptionAdapter captionAdapter = new CaptionAdapter(context, new CaptionHelper(context).getStatusType(), new CaptionAdapter.getStatusModel() {
                    @Override
                    public void SetClickHashTag(int position, StatusType statusType) {
                        Intent intent = new Intent(context, CaptionCategoryDetailsActivity.class);
                        intent.putExtra(Constants.CaptionCategory, statusType.getTitle());
                        startActivity(intent);
                    }
                });
                RvCaptionDetail.setAdapter(captionAdapter);
            }
        }.execute();

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