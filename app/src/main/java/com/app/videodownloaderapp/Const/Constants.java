package com.app.videodownloaderapp.Const;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.videodownloaderapp.R;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class Constants {

    public static final String WHATSAPP_PKG = "com.whatsapp";
    public static String DP_MAKER_FOLDER_NAME = "Dp Maker";
    public static String ROOT_PATH_DP_MAKER;
    public static String ROOT_PATH_FACEBOOK_VIDEO;
    public static String ROOT_PATH_IMAGESTORY;
    public static String ROOT_PATH_INSTAGRAM_IMAGE;
    public static String ROOT_PATH_INSTAGRAM_VIDEO;
    public static String ROOT_PATH_TWITTER_IMAGE;
    public static String ROOT_PATH_TWITTER_VIDEO;
    public static String ROOT_PATH_VIDEOSTORY;
    public static String ROOT_PATH_WHATAPP_IMAGE;
    public static String ROOT_PATH_WHATAPP_VIDEO;
    public static String HashCategoryId = "hash_category_id";
    public static String HashCategoryName = "hash_category_name";
    public static String HashCategoryData = "hash_category_data";
    public static String HashCategoryDetail = "hash_category_detail";
    public static String CaptionCategory = "caption_category";
    public static String QuoteId = "quote_id";
    public static String QuoteName = "quote_name";
    public static String DPMakerPos = "dp_maker_pos";
    public static String DPMakerItem = "dp_maker_item";
    public static String DPMakerValue = "dp_maker_value";
    public static String DpMakerName = "dp_maker_name";
    public static String DpEdit = "dp_maker_edit";
    public static String DpMakerItemList = "dp_maker_item_list";

    public static String RootDirectoryDpMaker;
    public static String RootDirectoryFacebook;
    public static String RootDirectoryImageStoryShow;
    public static String RootDirectoryInsta;
    public static String RootDirectoryTwitter;
    public static String RootDirectoryVideoStoryShow;
    public static String RootDirectoryWhatsapp;
    public static String RootImageDirectoryFacebookStory;
    public static String RootImageDirectoryInsta;
    public static String RootImageDirectoryTwitter;
    public static String RootImageDirectoryWhatsapp;
    public static String RootVideoDirectoryFacebookStory;
    public static String ISFromWhere = "isFromWhere";

    public static int[] Fb_Image = {R.drawable.fb3, R.drawable.fb4};
    public static int[] Fb_Text = {R.string.open_facebook, R.string.facebook_copy_link, R.string.open_app};
    public static int[] In_Image = {R.drawable.insta3, R.drawable.insta4};
    public static int[] In_Text = {R.string.open_instagram, R.string.copy_link, R.string.open_app};
    public static int[] Twit_Image = {R.drawable.tw3, R.drawable.tw4};
    public static int[] Twit_Text = {R.string.open_twitter, R.string.facebook_copy_link, R.string.open_app};

    public static boolean isInternetConnected(final Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            return true;
        }
        showAlertDialog(context, context.getResources().getString(R.string.alert), context.getResources().getString(R.string.no_internet_message), context.getResources().getString(R.string.alert_exit_yes), context.getResources().getString(R.string.alert_exit_no), "", new OnAlertDialogButtonListener() {
            public final void onNegativeButtonClick(Dialog dialog) {
                dialog.dismiss();
            }

            public final void onNeutralButtonClick(Dialog dialog) {
            }

            public final void onPositiveButtonClick(Dialog dialog) {
                dialog.dismiss();
                context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            }
        });
        return false;
    }

    public static void createFileFolder() {
        if (!new File(RootDirectoryInsta).exists()) {
            new File(RootDirectoryInsta).mkdirs();
        }
        if (!new File(RootImageDirectoryInsta).exists()) {
            new File(RootImageDirectoryInsta).mkdirs();
        }
        if (!new File(RootDirectoryFacebook).exists()) {
            new File(RootDirectoryFacebook).mkdirs();
        }
        if (!new File(RootDirectoryTwitter).exists()) {
            new File(RootDirectoryTwitter).mkdirs();
        }
        if (!new File(RootImageDirectoryTwitter).exists()) {
            new File(RootImageDirectoryTwitter).mkdirs();
        }
    }

    public static boolean access$2000(String str) {
        String str2;
        try {
            String path = new URL(str).getPath();
            str2 = path.substring(path.lastIndexOf("/") + 1, path.length());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            str2 = null;
        }
        return URLConnection.guessContentTypeFromName(str2).startsWith("video");
    }


    public interface OnAlertDialogButtonListener {
        void onNegativeButtonClick(Dialog dialog);

        void onNeutralButtonClick(Dialog dialog);

        void onPositiveButtonClick(Dialog dialog);
    }


    public static void showAlertDialog(Context context, String str, String str2, String str3, String str4, String str5, OnAlertDialogButtonListener onAlertDialogButtonListener) {
        final Dialog AlertDialog = new Dialog(context);
        AlertDialog.requestWindowFeature(1);
        AlertDialog.setContentView(R.layout.dialog_alert_layout);
        AlertDialog.setCanceledOnTouchOutside(false);
        AlertDialog.setCancelable(false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Window window = AlertDialog.getWindow();
        window.setLayout(displayMetrics.widthPixels - 100, -2);
        AlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        TextView TvNoAlert = (TextView) AlertDialog.findViewById(R.id.TvNoAlert);
        TextView TvYesAlert = (TextView) AlertDialog.findViewById(R.id.TvYesAlert);
        TextView TvRateUsAlert = (TextView) AlertDialog.findViewById(R.id.TvRateUsAlert);
        ((LinearLayout) AlertDialog.findViewById(R.id.LLAdsAlert)).setVisibility(View.GONE);
        ((TextView) AlertDialog.findViewById(R.id.TvTitleAlert)).setText(str);
        ((TextView) AlertDialog.findViewById(R.id.TvMsgAlert)).setText(str2);
        TvNoAlert.setText(str4);
        TvYesAlert.setText(str3);
        TvRateUsAlert.setText(str5);
        if (str3.equals("")) {
            TvYesAlert.setVisibility(View.GONE);
        } else {
            TvYesAlert.setVisibility(View.VISIBLE);
        }
        if (str4.equals("")) {
            TvNoAlert.setVisibility(View.GONE);
        } else {
            TvNoAlert.setVisibility(View.VISIBLE);
        }
        if (str5.equals("")) {
            TvRateUsAlert.setVisibility(View.GONE);
        } else {
            TvRateUsAlert.setVisibility(View.VISIBLE);
        }
        TvNoAlert.setOnClickListener(view -> onAlertDialogButtonListener.onNegativeButtonClick(AlertDialog));
        TvYesAlert.setOnClickListener(view -> onAlertDialogButtonListener.onPositiveButtonClick(AlertDialog));
        TvRateUsAlert.setOnClickListener(view -> onAlertDialogButtonListener.onNeutralButtonClick(AlertDialog));
        AlertDialog.show();
    }

    public static void GetFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.DIRECTORY_MOVIES);
        String str = File.separator;
        sb.append(str);
        sb.append("Social Status Saver Basic");
        sb.append(str);
        sb.append("Instagram");
        ROOT_PATH_INSTAGRAM_VIDEO = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Environment.DIRECTORY_PICTURES);
        sb2.append(str);
        sb2.append("Social Status Saver Basic");
        sb2.append(str);
        sb2.append("Instagram");
        ROOT_PATH_INSTAGRAM_IMAGE = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(Environment.DIRECTORY_MOVIES);
        sb3.append(str);
        sb3.append("Social Status Saver Basic");
        sb3.append(str);
        sb3.append("Story");
        ROOT_PATH_VIDEOSTORY = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append(Environment.DIRECTORY_PICTURES);
        sb4.append(str);
        sb4.append("Social Status Saver Basic");
        sb4.append(str);
        sb4.append("Story");
        ROOT_PATH_IMAGESTORY = sb4.toString();
        RootDirectoryInsta = Environment.getExternalStorageDirectory() + str + ROOT_PATH_INSTAGRAM_VIDEO;
        RootImageDirectoryInsta = Environment.getExternalStorageDirectory() + str + ROOT_PATH_INSTAGRAM_IMAGE;
        RootDirectoryVideoStoryShow = Environment.getExternalStorageDirectory() + str + ROOT_PATH_VIDEOSTORY;
        RootDirectoryImageStoryShow = Environment.getExternalStorageDirectory() + str + ROOT_PATH_IMAGESTORY;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(Environment.DIRECTORY_MOVIES);
        sb5.append(str);
        sb5.append("Social Status Saver Basic");
        sb5.append(str);
        sb5.append("Facebook");
        ROOT_PATH_FACEBOOK_VIDEO = sb5.toString();
        String str2 = Environment.DIRECTORY_PICTURES;
        RootVideoDirectoryFacebookStory = Environment.getExternalStorageDirectory() + str + ROOT_PATH_VIDEOSTORY;
        RootImageDirectoryFacebookStory = Environment.getExternalStorageDirectory() + str + ROOT_PATH_IMAGESTORY;
        RootDirectoryFacebook = Environment.getExternalStorageDirectory() + str + ROOT_PATH_FACEBOOK_VIDEO;
        Objects.toString(Environment.getExternalStorageDirectory());
        StringBuilder sb6 = new StringBuilder();
        sb6.append(Environment.DIRECTORY_MOVIES);
        sb6.append(str);
        sb6.append("Social Status Saver Basic");
        sb6.append(str);
        sb6.append("Whatsapp");
        ROOT_PATH_WHATAPP_VIDEO = sb6.toString();
        StringBuilder sb7 = new StringBuilder();
        sb7.append(Environment.DIRECTORY_PICTURES);
        sb7.append(str);
        sb7.append("Social Status Saver Basic");
        sb7.append(str);
        sb7.append("Whatsapp");
        ROOT_PATH_WHATAPP_IMAGE = sb7.toString();
        RootDirectoryWhatsapp = Environment.getExternalStorageDirectory() + str + ROOT_PATH_WHATAPP_VIDEO;
        RootImageDirectoryWhatsapp = Environment.getExternalStorageDirectory() + str + ROOT_PATH_WHATAPP_IMAGE;
        StringBuilder sb8 = new StringBuilder();
        sb8.append(Environment.DIRECTORY_PICTURES);
        sb8.append(str);
        sb8.append("Social Status Saver Basic");
        sb8.append(str);
        sb8.append(DP_MAKER_FOLDER_NAME);
        ROOT_PATH_DP_MAKER = sb8.toString();
        RootDirectoryDpMaker = Environment.getExternalStorageDirectory() + str + ROOT_PATH_DP_MAKER;
        StringBuilder sb9 = new StringBuilder();
        sb9.append(Environment.DIRECTORY_PICTURES);
        sb9.append(str);
        sb9.append("Social Status Saver Basic");
        sb9.append(str);
        sb9.append("Twitter");
        ROOT_PATH_TWITTER_VIDEO = sb9.toString();
        StringBuilder sb10 = new StringBuilder();
        sb10.append(Environment.DIRECTORY_PICTURES);
        sb10.append(str);
        sb10.append("Social Status Saver Basic");
        sb10.append(str);
        sb10.append("Twitter");
        ROOT_PATH_TWITTER_IMAGE = sb10.toString();
        RootDirectoryTwitter = Environment.getExternalStorageDirectory() + str + ROOT_PATH_TWITTER_VIDEO;
        RootImageDirectoryTwitter = Environment.getExternalStorageDirectory() + str + ROOT_PATH_TWITTER_IMAGE;

    }
}
