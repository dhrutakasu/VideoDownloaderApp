package com.app.videodownloaderapp.Ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Models.Category;
import com.app.videodownloaderapp.Models.Hashtag;
import com.app.videodownloaderapp.Models.HashtagResponseData;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.HashTagAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HashtagActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private ImageView IvNotFound;
    private RecyclerView RvHashTag;
    private String HashTagStr;
    private ArrayList hashtagModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtag);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        RvHashTag = (RecyclerView) findViewById(R.id.RvHashTag);
        IvNotFound = (ImageView) findViewById(R.id.IvNotFound);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        IvNotFound.setVisibility(View.GONE);
        TvTitle.setText("Hashtag");
        new HashTagAsynkTask().execute();
    }

    public class HashTagAsynkTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            hashtagModelArrayList = new ArrayList();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                InputStream inputStream = getAssets().open("hashtags.json");
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                inputStream.close();
                HashTagStr = new String(bytes, "UTF-8");

                Gson gson = new Gson();
                HashtagResponseData responseData = gson.fromJson(HashTagStr, HashtagResponseData.class);
                List<Category> categories = responseData.getData();
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).getHashtag().size() > 0) {
                        hashtagModelArrayList.add(categories.get(i));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                HashTagStr = null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            RvHashTag.setLayoutManager(new GridLayoutManager(context, 4));
            if (hashtagModelArrayList.size() > 0) {
                RvHashTag.setVisibility(View.VISIBLE);
                IvNotFound.setVisibility(View.GONE);
            } else {
                RvHashTag.setVisibility(View.GONE);
                IvNotFound.setVisibility(View.VISIBLE);
            }
            HashTagAdapter hashTagAdapter = new HashTagAdapter(context, hashtagModelArrayList, (position, hashTagModel) -> {
                Intent intent = new Intent(context, HashtagCategoriesActivity.class);
                intent.putExtra(Constants.HashCategoryId, hashTagModel.getId());
                intent.putExtra(Constants.HashCategoryName, hashTagModel.getName());
                intent.putExtra(Constants.HashCategoryData, new Gson().toJson(hashTagModel.getHashtag()));
                startActivity(intent);
            });
            RvHashTag.setAdapter(hashTagAdapter);
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