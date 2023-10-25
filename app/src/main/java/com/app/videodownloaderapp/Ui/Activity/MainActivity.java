package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Models.DataItem;
import com.app.videodownloaderapp.Models.HashTagModel;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.HashTagAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private ConstraintLayout ConsInstagram, ConsFacebook, ConsWhatsapp, ConsTwitter;
    private CardView CardHashtag, CardCaption, CardQuotes, CardDpMaker, CardStory, CardCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        ConsInstagram = (ConstraintLayout) findViewById(R.id.ConsInstagram);
        ConsFacebook = (ConstraintLayout) findViewById(R.id.ConsFacebook);
        ConsWhatsapp = (ConstraintLayout) findViewById(R.id.ConsWhatsapp);
        ConsTwitter = (ConstraintLayout) findViewById(R.id.ConsTwitter);
        CardHashtag = (CardView) findViewById(R.id.CardHashtag);
        CardCaption = (CardView) findViewById(R.id.CardCaption);
        CardQuotes = (CardView) findViewById(R.id.CardQuotes);
        CardDpMaker = (CardView) findViewById(R.id.CardDpMaker);
        CardStory = (CardView) findViewById(R.id.CardStory);
        CardCreation = (CardView) findViewById(R.id.CardCreation);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
        CardHashtag.setOnClickListener(this);
        CardCaption.setOnClickListener(this);
        CardQuotes.setOnClickListener(this);
        CardDpMaker.setOnClickListener(this);
        CardStory.setOnClickListener(this);
        CardCreation.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IvBack:
                finish();
                break;
            case R.id.CardHashtag:
                startActivity(new Intent(context, HashtagActivity.class));
                break;
            case R.id.CardCaption:
                break;
            case R.id.CardQuotes:
                break;
            case R.id.CardDpMaker:
                break;
            case R.id.CardStory:
                break;
            case R.id.CardCreation:
                break;
        }
    }
}