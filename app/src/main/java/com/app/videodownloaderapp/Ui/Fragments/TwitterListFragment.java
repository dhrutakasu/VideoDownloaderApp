package com.app.videodownloaderapp.Ui.Fragments;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.app.videodownloaderapp.Models.InstaDataProvider;
import com.app.videodownloaderapp.Models.VideoDownloaderDownloadModel;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.VideoDownloaderDownloadAdapter;
import com.app.videodownloaderapp.Util.FinalJsonGet;
import com.app.videodownloaderapp.Util.FinalJsonPrivate;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

public class TwitterListFragment extends androidx.fragment.app.Fragment implements View.OnClickListener {

    private Context context;
    private View view;
    private EditText EdtUrl;
    private ImageView IvClear, IvNotFound;
    private CardView CardPaste, ConsDownload;
    private RecyclerView RvDownLoaded, RvYOurDownload;
    private TextView TvDownloadViewTitle, TvSeeAllYourDownload, TvMsg;
    private LinearLayout LlDownload;
    private RelativeLayout RlDownloadView;
    private ConstraintLayout ConsProgressKit;
    private SpinKitView ProgressKit;
    private String inputUrl;
    private RequestQueue requestQueue;
    public FinalJsonPrivate PrivatefinalJsonGet;
    private FinalJsonGet PublicfinalJsonGet;
    public ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> filename;
    private String mainUrl;
    private String caption = "";
    private String hastag = "";
    private String username;
    private String picUrl;
    private String profileUrl;
    private final ArrayList<VideoDownloaderDownloadModel> downloadModels = new ArrayList();
    private VideoDownloaderDownloadAdapter downloadAdapter;
    private int allComplete = 0;
    public String cookie = "null";
    private String downloadFileName;
    private Dialog imageDownloadDialog;
    private WebView browser;
    int webProgress = 0;

    boolean isWebViewLoaded = false;
    private int counter = 0;
    List<InstaDataProvider> instaDataProviderList = new ArrayList<>();
    List<InstaDataProvider> instaDownloadedList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_twitter_list, container, false);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
        return view;
    }

    private void VideoInitViews() {
        context = getContext();
        EdtUrl = view.findViewById(R.id.EdtUrl);
        IvClear = view.findViewById(R.id.IvClear);
        CardPaste = view.findViewById(R.id.CardPaste);
        ConsDownload = view.findViewById(R.id.ConsDownload);
        RvDownLoaded = view.findViewById(R.id.RvDownLoaded);
        TvDownloadViewTitle = view.findViewById(R.id.TvDownloadViewTitle);
        LlDownload = view.findViewById(R.id.LlDownload);
        RlDownloadView = view.findViewById(R.id.RlDownloadView);
        TvSeeAllYourDownload = view.findViewById(R.id.TvSeeAllYourDownload);
        RvYOurDownload = view.findViewById(R.id.RvYOurDownload);
        IvNotFound = view.findViewById(R.id.IvNotFound);
        ConsProgressKit = view.findViewById(R.id.ConsProgressKit);
        ProgressKit = view.findViewById(R.id.ProgressKit);
        TvMsg = view.findViewById(R.id.TvMsg);
        browser = view.findViewById(R.id.browser);
    }

    private void VideoInitListerns() {
        CardPaste.setOnClickListener(this);
        ConsDownload.setOnClickListener(this);
        IvClear.setOnClickListener(this);
    }

    private void VideoInitActions() {
        requestQueue = Volley.newRequestQueue(context);
        filename = new ArrayList<>();
        instaDownloadedList = new ArrayList<>();
        cookie = getActivity().getSharedPreferences("cookie", 0).getString("flag", null);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.CardPaste:
                GotoPasteLink();
                break;
            case R.id.IvClear:
                GotoClear();
                break;
            case R.id.ConsDownload:
                GotoDownload();
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        this.cookie = getActivity().getSharedPreferences("cookie", 0).getString("facebook", null);
        if (instaDownloadedList.size()<0){
            IvNotFound.setVisibility(View.VISIBLE);
            RvDownLoaded.setVisibility(View.GONE);
            RvYOurDownload.setVisibility(View.GONE);
            return;
        }
        IvNotFound.setVisibility(View.GONE);
        RvDownLoaded.setVisibility(View.VISIBLE);
        RvYOurDownload.setVisibility(View.VISIBLE);
    }

    private void GotoPasteLink() {
        ClipboardManager service = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (service.getPrimaryClip() != null) {
            ClipData.Item at = service.getPrimaryClip().getItemAt(0);
            String url = at.getText().toString() != null ? at.getText().toString() : "";
            if (Patterns.WEB_URL.matcher(url).matches()) {
                EdtUrl.setText(url);
            }
        }
    }

    private void GotoClear() {
        EdtUrl.getText().clear();
    }

    private void GotoDownload() {

    }
}
