package com.app.videodownloaderapp.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.R;

import java.util.ArrayList;

public class HashtagListsActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private TextView TvTotalTags, TvHashtagData;
    private CardView CardTotalTags;
    private LinearLayout LlInstagram, LLFacebook, LLTwitter, LLCopyTag;
    private String HashTagStr;
    private ArrayList hashtagModelArrayList;

    private String IsStr;
    private boolean IsPackageFace = true;
    private boolean IsPackage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hashtag_lists);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        CardTotalTags = (CardView) findViewById(R.id.CardTotalTags);
        TvTotalTags = (TextView) findViewById(R.id.TvTotalTags);
        TvHashtagData = (TextView) findViewById(R.id.TvHashtagData);
        LlInstagram = (LinearLayout) findViewById(R.id.LlInstagram);
        LLFacebook = (LinearLayout) findViewById(R.id.LLFacebook);
        LLTwitter = (LinearLayout) findViewById(R.id.LLTwitter);
        LLCopyTag = (LinearLayout) findViewById(R.id.LLCopyTag);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
        LlInstagram.setOnClickListener(this);
        LLFacebook.setOnClickListener(this);
        LLTwitter.setOnClickListener(this);
        LLCopyTag.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText(getIntent().getStringExtra(Constants.HashCategoryName));
        int len = getIntent().getStringExtra(Constants.HashCategoryDetail).split(" ").length;
        TvTotalTags.setText(String.valueOf(len) + "TAGS");
        TvHashtagData.setText(getIntent().getStringExtra(Constants.HashCategoryDetail));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IvBack:
                finish();
                break;
            case R.id.LlInstagram:
                GotoInst();
                break;
            case R.id.LLFacebook:
                GotoFacebook();
                break;
            case R.id.LLTwitter:
                GotoTwitter();
                break;
            case R.id.LLCopyTag:
                AllCopyHashTagData();
                break;
        }
    }

    private void GotoInst() {
        AllCopyHashTagData();
        try {
            context.getPackageManager().getApplicationInfo("com.instagram.android", 0);
            IsPackage = true;
        } catch (PackageManager.NameNotFoundException unused3) {
        }
        if (IsPackage) {
            Uri uri = Uri.parse("com.instagram.android");
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
            if (intent != null) {
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
                return;
            }
            return;
        }
        Toast.makeText(this, getResources().getString(R.string.toast_instagram_not_found), Toast.LENGTH_LONG).show();
    }

    private void GotoFacebook() {
        AllCopyHashTagData();
        try {
            context.getPackageManager().getApplicationInfo("com.facebook.katana", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            IsPackageFace = false;
        }
        if (IsPackageFace) {
            Toast.makeText(context, getResources().getString(R.string.facebook_already_installed), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            try {
                IsStr = getPackageManager().getPackageInfo("com.facebook.orca", 0).versionCode >= 3002850 ? "fb://facewebmodal/f?href=https://www.facebook.com/" : "fb://page/YourPageName";
            } catch (PackageManager.NameNotFoundException unused2) {
                IsStr = "https://www.facebook.com/";
            }
            intent.setData(Uri.parse(IsStr));
            startActivity(intent);
            return;
        }
        Toast.makeText(context, getResources().getString(R.string.facebook_not_installing), Toast.LENGTH_SHORT).show();

    }

    private void GotoTwitter() {
        AllCopyHashTagData();
        try {
            context.getPackageManager().getPackageInfo("com.twitter.android", 0);
        } catch (PackageManager.NameNotFoundException unused4) {
            IsPackageFace = false;
        }
        if (IsPackageFace) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClassName("com.twitter.android", "com.twitter.android.ProfileActivity");
            intent.putExtra("user_id", 342391);
            startActivity(intent);
            return;
        }
        Toast.makeText(context, getResources().getString(R.string.toast_twitter_not_installing), Toast.LENGTH_SHORT).show();
    }

    public final void AllCopyHashTagData() {
        ((ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("Label", TvHashtagData.getText().toString()));
        Toast.makeText(context, R.string.toast_copied_to_clipboard, Toast.LENGTH_SHORT).show();
    }
}