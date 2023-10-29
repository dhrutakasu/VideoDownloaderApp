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
import com.app.videodownloaderapp.Models.CategoryDb;
import com.app.videodownloaderapp.Helper.QuoteHelper;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.QuoteAdapter;

public class QuoteActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitle, TvQuotes;
    private ImageView IvBack, IvNoData;
    private RecyclerView RvQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        TvQuotes = (TextView) findViewById(R.id.TvQuotes);
        RvQuotes = (RecyclerView) findViewById(R.id.RvQuotes);
        IvNoData = (ImageView) findViewById(R.id.IvNoData);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText("Quote");
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                new QuoteHelper(context);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                RvQuotes.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                QuoteAdapter captionAdapter = new QuoteAdapter(context, new QuoteHelper(context).getCategory(), new QuoteAdapter.getQuote() {
                    @Override
                    public void SetClickQuote(int position, CategoryDb categoryDb) {
                        Intent intent = new Intent(context, QuoteItemActivity.class);
                        intent.putExtra(Constants.QuoteId, categoryDb.getId());
                        intent.putExtra(Constants.QuoteName, categoryDb.getName());
                        startActivity(intent);

                    }
                });
                RvQuotes.setAdapter(captionAdapter);
                if (new QuoteHelper(context).getCategory().size() > 0) {
                    IvNoData.setVisibility(View.GONE);
                    RvQuotes.setVisibility(View.VISIBLE);
                } else {
                    IvNoData.setVisibility(View.VISIBLE);
                    RvQuotes.setVisibility(View.GONE);
                }
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