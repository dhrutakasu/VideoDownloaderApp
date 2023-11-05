package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.R;

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
        ConsInstagram.setOnClickListener(this);
        ConsFacebook.setOnClickListener(this);
        ConsWhatsapp.setOnClickListener(this);
        ConsTwitter.setOnClickListener(this);
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
            case R.id.ConsInstagram:
                startActivity(new Intent(context, InstagramActivity.class));
                break;
            case R.id.ConsFacebook:
                startActivity(new Intent(context, FacebookActivity.class));
                break;
            case R.id.ConsWhatsapp:
                break;
            case R.id.ConsTwitter:
                startActivity(new Intent(context, TwitterActivity.class));
                break;
            case R.id.CardHashtag:
                startActivity(new Intent(context, HashtagActivity.class));
                break;
            case R.id.CardCaption:
                startActivity(new Intent(context, CaptionActivity.class));
                break;
            case R.id.CardQuotes:
                startActivity(new Intent(context, QuoteActivity.class));
                break;
            case R.id.CardDpMaker:
                startActivity(new Intent(context, DPMakerActivity.class));
                break;
            case R.id.CardStory:
                startActivity(new Intent(context, StoryActivity.class));
                break;
            case R.id.CardCreation:
                startActivity(new Intent(context, CreationActivity.class));
                break;
        }
    }
}