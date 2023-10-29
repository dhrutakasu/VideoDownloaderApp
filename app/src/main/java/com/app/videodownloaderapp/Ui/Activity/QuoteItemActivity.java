package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Helper.QuoteHelper;
import com.app.videodownloaderapp.Models.QuoteModel;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.QuoteDataAdapter;

public class QuoteItemActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private ImageView IvNoData;
    private RecyclerView RvQuoteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_item);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        IvNoData = (ImageView) findViewById(R.id.IvNoData);
        RvQuoteData = (RecyclerView) findViewById(R.id.RvQuoteData);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText(getIntent().getStringExtra(Constants.QuoteName));
        if (new QuoteHelper(context).getCategory().size() > 0) {
            IvNoData.setVisibility(View.GONE);
            RvQuoteData.setVisibility(View.VISIBLE);
        } else {
            IvNoData.setVisibility(View.VISIBLE);
            RvQuoteData.setVisibility(View.GONE);
        }
        RvQuoteData.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        QuoteDataAdapter captionAdapter = new QuoteDataAdapter(context, new QuoteHelper(context).getQuoteId(getIntent().getStringExtra(Constants.QuoteId)));
        RvQuoteData.setAdapter(captionAdapter);
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