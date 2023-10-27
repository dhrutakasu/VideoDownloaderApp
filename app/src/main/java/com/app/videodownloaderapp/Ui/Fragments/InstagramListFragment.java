package com.app.videodownloaderapp.Ui.Fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.videodownloaderapp.R;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.net.MediaType;
import com.google.gson.GsonBuilder;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InstagramListFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private View view;
    private EditText EdtUrl;
    private ImageView IvClear, IvNotFound;
    private CardView CardPaste, ConsDownload;
    private RecyclerView RvDownLoaded, RvYOurDownload;
    private TextView TvDownloadViewTitle, TvSeeAllYourDownload;
    private LinearLayout LlDownload;
    private RelativeLayout RlDownloadView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_instagram_list, container, false);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
        return view;
    }

    private void VideoInitViews() {
        context = getContext();
        EdtUrl = (EditText) view.findViewById(R.id.EdtUrl);
        IvClear = (ImageView) view.findViewById(R.id.IvClear);
        CardPaste = (CardView) view.findViewById(R.id.CardPaste);
        ConsDownload = (CardView) view.findViewById(R.id.ConsDownload);
        RvDownLoaded = (RecyclerView) view.findViewById(R.id.RvDownLoaded);
        TvDownloadViewTitle = (TextView) view.findViewById(R.id.TvDownloadViewTitle);
        LlDownload = (LinearLayout) view.findViewById(R.id.LlDownload);
        RlDownloadView = (RelativeLayout) view.findViewById(R.id.RlDownloadView);
        TvSeeAllYourDownload = (TextView) view.findViewById(R.id.TvSeeAllYourDownload);
        RvYOurDownload = (RecyclerView) view.findViewById(R.id.RvYOurDownload);
        IvNotFound = (ImageView) view.findViewById(R.id.IvNotFound);
    }

    private void VideoInitListerns() {
        CardPaste.setOnClickListener(this);
        ConsDownload.setOnClickListener(this);
        IvClear.setOnClickListener(this);
    }

    private void VideoInitActions() {
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
            case R.id.IvDownload:
                GotoDownload();
                break;
        }
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
       /* cl_spinkit.setVisibility(0);
        progressBar.setIndeterminateDrawable(new FadingCircle());
        String trim = EdtUrl.getText().toString().trim();
        inputUrl = trim;
        String str = "";
        if (trim.equals(str)) {
            cl_spinkit.setVisibility(8);
            Toast.makeText(getActivity(), getResources().getString(R.string.enter_url), 0).show();
            return;
        }
        for (String m : MediaType.extractUrls(inputUrl)) {
            str = Fragment$$ExternalSyntheticOutline0.m(str, m);
        }
        inputUrl = str;
        MediaType mediaType = new MediaType(str);
        if (mediaType.isValidLink().equals("notValid")) {
            cl_spinkit.setVisibility(8);
            Toast.makeText(getActivity(), getResources().getString(R.string.enter_instagram_url), 0).show();
        } else if (mediaType.isValidLink().equals("highlight")) {
            cl_spinkit.setVisibility(8);
            Toast.makeText(getActivity(), getResources().getString(R.string.toast_stroy_highligth_notsupported), 0).show();
        } else if (mediaType.isValidLink().equals("post")) {
            String m2 = Fragment$$ExternalSyntheticOutline0.m(new StringBuilder(), inputUrl.split(Pattern.quote("?"))[0], "?__a=1&__d=dis");
            if (NetworkUtils.isInternetConnected(getActivity())) {
                cv_download.setClickable(false);
                inputUrl = enterUrl.getText().toString().trim();
                requestQueue.add(new JsonObjectRequest(0, m2, (JSONObject) null, new Response.Listener<JSONObject>(m2) {
                    public final *//* synthetic *//* String val$str;

                    {
                        val$str = r2;
                    }

                    *//* JADX WARNING: type inference failed for: r6v10, types: [java.util.List<com.basic.videodownloader.allmovie.model.VideoDownloaderDownloadModel>, java.util.ArrayList] *//*
                    public final void onResponse(Object obj) {
                        JSONObject jSONObject = (JSONObject) obj;
                        if (String.valueOf(jSONObject).contains("{\"title\":\"Restricted Video\",\"description\":\"You must be 18 years old or over to see this video\"}")) {
                            PrivateDataProcess(val$str);
                            return;
                        }
                        PublicfinalJsonGet = (FinalJsonGet) new GsonBuilder().create().fromJson(String.valueOf(jSONObject), FinalJsonGet.class);
                        PublicFinalUrlList publicFinalUrlList = new PublicFinalUrlList(PublicfinalJsonGet);
                        list.clear();
                        list = publicFinalUrlList.getUrlList();
                        filename.clear();
                        caption = publicFinalUrlList.getCaption();
                        hastag = publicFinalUrlList.getHastag();
                        VideoDownloaderInstagramStoryDownloadFragment videoDownloaderInstagramStoryDownloadFragment = this;
                        mainUrl = inputUrl;
                        username = publicFinalUrlList.getUsername();
                        picUrl = publicFinalUrlList.getPicUrl();
                        profileUrl = publicFinalUrlList.getProfileUrl();
                        cl_spinkit.setVisibility(8);
                        downloadModels.clear();
                        FileInfo.createFileFolder();
                        downloadAdapter = new VideoDownloaderDownloadAdapter(getActivity(), downloadModels);
                        downloadView.setVisibility(0);
                        RecyclerView recyclerView = rcv_downloading;
                        recyclerView.setLayoutManager(new LinearLayoutManager(1));
                        rcv_downloading.setAdapter(downloadAdapter);
                        allComplete = 0;
                        Toast.makeText(getActivity(), getResources().getString(R.string.download_started), 0).show();
                        for (int i = 0; i < list.size(); i++) {
                            String str = list.get(i);
                            if (access$2000(this, str)) {
                                new DownloadVideoAsyncTask().execute(new String[]{str});
                            } else {
                                new DownloadImageAsyncTask().execute(new String[]{str});
                            }
                        }
                    }
                }, new Response.ErrorListener(m2) {
                    public final *//* synthetic *//* String val$str;

                    {
                        val$str = r2;
                    }

                    public final void onErrorResponse(VolleyError volleyError) {
                        PrivateDataProcess(val$str);
                    }
                }));
                return;
            }
            cl_spinkit.setVisibility(8);
            Toast.makeText(getActivity(), getResources().getString(R.string.no_internet_message), 0).show();
        } else {
            cl_spinkit.setVisibility(8);
            Toast.makeText(getActivity(), getResources().getString(R.string.open_story_highligth), 0).show();
        }*/
    }
}
