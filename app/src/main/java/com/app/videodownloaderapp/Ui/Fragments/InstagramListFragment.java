package com.app.videodownloaderapp.Ui.Fragments;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.android.volley.AuthFailureError;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Models.InstaDataProvider;
import com.app.videodownloaderapp.Models.VideoDownloaderDownloadModel;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Activity.InstagramLoginActivity;
import com.app.videodownloaderapp.Ui.Adapters.VideoDownloaderDownloadAdapter;
import com.app.videodownloaderapp.Util.FinalJsonGet;
import com.app.videodownloaderapp.Util.FinalJsonPrivate;
import com.app.videodownloaderapp.Util.MediaType;
import com.app.videodownloaderapp.Util.PrivateFinalUrlList;
import com.app.videodownloaderapp.Util.PublicFinalUrlList;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class InstagramListFragment extends Fragment implements View.OnClickListener {

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
    int downloadPostCount;
    private DownloadManager downloadManager;

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
//        loginDialog();
        String cookie = CookieManager.getInstance().getCookie("https://www.instagram.com/");
        System.out.println("-------   auth coockieee : "+cookie);
        cookie = getActivity().getSharedPreferences("cookie", 0).getString("flag", null);
    /*    downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setDomStorageEnabled(true);
        browser.getSettings().setAllowFileAccess(true);
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        browser.setWebChromeClient(new ChromeClient());
        browser.setWebViewClient(new WebClient());
        browser.loadUrl("https://savefrom.biz/in4/instagram-downloader");

        browser.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                System.out.println(">>>>>>>url21 : " + url);
                System.out.println(">>>>>>>url21 : " + userAgent);
                System.out.println(">>>>>>>url21 : " + contentDisposition);
                System.out.println(">>>>>>>url21 : " + mimetype);
                System.out.println(">>>>>>>url21 : " + contentLength);

                InstaDataProvider instaDataProvider = new InstaDataProvider();
                if (mimetype.contains("image/jpeg")) {
                    instaDataProvider.setVideo(false);
                } else {
                    instaDataProvider.setVideo(true);

                    String str = context.getFilesDir().getAbsolutePath() + File.separator + getString(R.string.app_name) + File.separator + "Instagram";
                    File file = new File(str);
                    if (!file.exists()) {
                        file.mkdirs();
                    }

                    if (str.contains("insta")) {
                        try {
                            url = URLDecoder.decode(url, "UTF-8").split("uri=")[1].split("&dl=1")[0];
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }

                System.out.println(">>>>>>>url210 : " + url);
                instaDataProvider.setTitle(URLUtil.guessFileName(url, null, null));
                instaDataProvider.setThumbnailUrl(url);
                instaDataProvider.setDownloadUrl(url);
                instaDataProvider.setDownloadCompleted(false);
                instaDataProviderList.add(instaDataProvider);
                downloadPostCount = instaDataProviderList.size();
                if (instaDataProviderList != null && instaDataProviderList.size() > 0 && downloadPostCount == instaDataProviderList.size()) {
                    counter = 0;
//                    SharedPreferences.Editor edit = context.getSharedPreferences("PRE_URL_DATA", 0).edit();
//                    edit.putString("data_url", new Gson().toJson(instaDataProviderList));
//                    edit.commit();
//                    sharedText = "";
//                    clearClipBoard();
                    startDownloading();
//                    displayViewPager();
                    return;
                }
                counter++;

                browser.evaluateJavascript("javascript:document.querySelectorAll('button.MuiButton-root')[" + counter + "].click()", null);
            }
        });*/
    }

    public void startDownloading() {
//        isInProcess = false;
        ContentValues contentValues = new ContentValues();
        if (Build.VERSION.SDK_INT >= 29) {
            String str = context.getFilesDir().getAbsolutePath() + File.separator + getString(R.string.app_name) + File.separator + "Instagram";
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            for (int i = 0; i < instaDataProviderList.size(); i++) {
                String downloadUrl = instaDataProviderList.get(i).getDownloadUrl();
                String title = instaDataProviderList.get(i).getTitle();
                System.out.println(">>>>>>>title : " + title);
                contentValues.put("_display_name", title);
                contentValues.put("relative_path", str);
                if (instaDataProviderList.get(i).isVideo()) {
                    contentValues.put("mime_type", "video/*");
                } else {
                    contentValues.put("mime_type", "image/*");
                }
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
                request.setAllowedNetworkTypes(3);
                request.setTitle("Downloading : " + title);
                request.setDescription("Saved At :  " + str);
                request.setVisibleInDownloadsUi(true);
                request.setNotificationVisibility(0);
                request.setNotificationVisibility(1);
                request.setAllowedOverRoaming(true);
                request.setDestinationUri(Uri.fromFile(new File(str, title)));
                instaDataProviderList.get(i).setDownloadRefId(downloadManager.enqueue(request));
                ConsProgressKit.setVisibility(View.GONE);

            }
        } else {
            String str2 = context.getFilesDir().getAbsolutePath() + File.separator + getString(R.string.app_name) + File.separator + "Instagram";
            File file2 = new File(str2);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            for (int i2 = 0; i2 < instaDataProviderList.size(); i2++) {
                String downloadUrl2 = instaDataProviderList.get(i2).getDownloadUrl();
                String title2 = instaDataProviderList.get(i2).getTitle();
                System.out.println(">>>>>>>title22 : " + title2);

                DownloadManager.Request request2 = new DownloadManager.Request(Uri.parse(downloadUrl2));
                request2.setAllowedNetworkTypes(3);
                request2.setTitle("Downloading : " + title2);
                request2.setDescription("Saved At :  " + str2);
                request2.setVisibleInDownloadsUi(true);
                request2.setNotificationVisibility(0);
                request2.setNotificationVisibility(1);
                request2.setAllowedOverRoaming(true);
                request2.setDestinationUri(Uri.fromFile(new File(str2, title2)));
                instaDataProviderList.get(i2).setDownloadRefId(downloadManager.enqueue(request2));
                ConsProgressKit.setVisibility(View.GONE);

            }
        }

    }

    public class ChromeClient extends WebChromeClient {
        private ChromeClient() {
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            webProgress = newProgress;

            super.onProgressChanged(view, newProgress);
        }
    }

    public class WebClient extends WebViewClient {
        private WebClient() {
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (webProgress != 100 || isWebViewLoaded) {
                return;
            }
            isWebViewLoaded = true;
        }
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
        this.cookie = getActivity().getSharedPreferences("cookie", 0).getString("instagram", null);
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
        ConsProgressKit.setVisibility(View.VISIBLE);
        ProgressKit.setIndeterminateDrawable(new FadingCircle());
        String trim = EdtUrl.getText().toString().trim();
        inputUrl = trim;
        String str = "";
        if (trim.equals(str)) {
            ConsProgressKit.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getResources().getString(R.string.enter_url), Toast.LENGTH_SHORT).show();
            return;
        }
        for (String m : MediaType.extractUrls(inputUrl)) {
            String builder = str + m;
            str = builder;
        }
        System.out.println("-------- - - - ssss str : " + str);
        inputUrl = str;
        MediaType mediaType = new MediaType(str);
        System.out.println("-------- - - - ssss ValidLink : " + mediaType.isValidLink());
        if (mediaType.isValidLink().equals("notValid")) {
            ConsProgressKit.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getResources().getString(R.string.enter_instagram_url), Toast.LENGTH_SHORT).show();
        } else if (mediaType.isValidLink().equals("highlight")) {
            ConsProgressKit.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getResources().getString(R.string.toast_stroy_highligth_notsupported), Toast.LENGTH_SHORT).show();
        } else if (mediaType.isValidLink().equals("post")) {
//            String m2 = inputUrl.split(Pattern.quote("?"))[0] + "?__a=1";
            String m2 = inputUrl.split(Pattern.quote("?"))[0] + "?__a=1&__d=dis";
            if (Constants.isInternetConnected(getActivity())) {
                ConsDownload.setClickable(false);
                inputUrl = EdtUrl.getText().toString().trim();
                System.out.println("-------- - - - ssss m2: " + m2);
               /* Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.instagram.com/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                InstagramAPI instagramAPI = retrofit.create(InstagramAPI.class);

                // Instagram shortcode (e.g., p/-vSJNUDKKD)
                String shortcode = "p/-vSJNUDKKD";

                // Make a request to get Instagram post data
                Call<JsonObject> call = instagramAPI.getPostData(shortcode);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                        Log.d("-TAG", "Image response: " + response.body());
                        if (response.isSuccessful()) {
                            JsonObject post = response.body();
                            if (post != null) {
                                String imageUrl = post.getAsJsonObject("graphql")
                                        .getAsJsonObject("shortcode_media")
                                        .get("display_url")
                                        .getAsString();
                                Log.d("-TAG", "Image URL: " + imageUrl);
                            }
                        } else {
                            Log.e("-TAG", "Error: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e("-TAG", "Errors: " + t.getMessage());
                    }
                });*/

          /*      StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.instagram.com/p/Cy_SOdwg6ej/?__a=1&__d=dis",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                System.out.println("-------- - - - ssss response: " + response.toString());
                                try {
//                                    // Parse the JSON response
                                    JSONObject jsonResponse = new JSONObject(response);
//
//                                    // Access the data you need, such as image download URLs
//                                    String imageUrl = jsonResponse.getJSONObject("graphql")
//                                            .getJSONObject("shortcode_media")
//                                            .getString("display_url");
//
//                                    Log.d("TAG", "Image URL: " + imageUrl);
//
//                                    // Handle the image URL as needed
                                } catch (JSONException e) {
                                    e.printStackTrace();

                                    System.out.println("-------- - - - ssss JSONException: " + e.getMessage());
                                    Log.e("TAG", "Error parsing JSON response");
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("TAG", "Error: " + error.getMessage());

                                System.out.println("-------- - - - ssss Error: " + error.getMessage());
                            }
                        });

                // Add the request to the RequestQueue
                requestQueue.add(stringRequest);*/


                requestQueue.add(new JsonObjectRequest(Request.Method.GET, "https://www.instagram.com/p/Cy8sr-aI9wK/?__a=1&__d=dis", null, obj -> {
                    JSONObject jSONObject = obj;

//                    if (String.valueOf(jSONObject).contains("{\"title\":\"Restricted Video\",\"description\":\"You must be 18 years old or over to see this video\"}")) {
//                        PrivateDataProcess(obj.toString());
//                        return;
//                    }
                    System.out.println("-------- - - - ssss obj: " + Arrays.toString(new GsonBuilder().create().fromJson(String.valueOf(jSONObject), com.app.videodownloaderapp.unknown2.Response.class).getItems().toArray()));
                    for (int i = 0; i < new GsonBuilder().create().fromJson(String.valueOf(jSONObject), com.app.videodownloaderapp.unknown2.Response.class).getItems().size(); i++) {
                        System.out.println("-------- - - - ssss obj for : " + Arrays.toString(new GsonBuilder().create().fromJson(String.valueOf(jSONObject), com.app.videodownloaderapp.unknown2.Response.class).getItems().get(i).getUsertags().getIn().toArray()));
//                        for (int j = 0; j < new GsonBuilder().create().fromJson(String.valueOf(jSONObject), com.app.videodownloaderapp.unknown2.Response.class).getItems().get(i).getCaption().getUser().getUsername().size(); j++) {
                            System.out.println("-------- - - - ssss obj for jj  : " + new GsonBuilder().create().fromJson(String.valueOf(jSONObject), com.app.videodownloaderapp.unknown2.Response.class).getItems().get(i).getCaption().getUser().getUsername());
//                        }
                    }
//                        System.out.println("-------- - - - ssss obj111: " + jSONObject.toString());
//                        if (jSONObject.get("carousel_media_count")!=null){
//                            System.out.println("-------- - - - ssss obj001: " + jSONObject.get("carousel_media_count").toString());
//                            System.out.println("-------- - - - ssss obj002: " + jSONObject.get("carousel_media").toString());
//
//                        }
                    /*  PublicfinalJsonGet = new GsonBuilder().create().fromJson(String.valueOf(jSONObject), FinalJsonGet.class);
                    System.out.println("------ PublicfinalJsonGet : " + PublicfinalJsonGet.toString());
                    PublicFinalUrlList publicFinalUrlList = new PublicFinalUrlList(PublicfinalJsonGet);
                    list.clear();
                    list = publicFinalUrlList.getUrlList();
                    filename.clear();
                    caption = publicFinalUrlList.getCaption();
                    hastag = publicFinalUrlList.getHastag();
                    mainUrl = inputUrl;
                    username = publicFinalUrlList.getUsername();
                    picUrl = publicFinalUrlList.getPicUrl();
                    profileUrl = publicFinalUrlList.getProfileUrl();
                    ConsProgressKit.setVisibility(View.GONE);
                    downloadModels.clear();
                    Constants.createFileFolder();
                    downloadAdapter = new VideoDownloaderDownloadAdapter(context, downloadModels);
                    RlDownloadView.setVisibility(View.VISIBLE);
                    RvDownLoaded.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                    RvDownLoaded.setAdapter(downloadAdapter);
                    allComplete = 0;
                    Toast.makeText(getActivity(), getResources().getString(R.string.download_started), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("-------- - - - ssss list: " + list.get(i));
                        String str1 = list.get(i);
                        if (Constants.access$2000(str1)) {
                            new DownloadVideoAsyncTask(context).execute(str1);
                        } else {
                            new DownloadImageAsyncTask(context).execute(str1);
                        }
                    }*/
                }, volleyError -> {
                    loginDialog();
                    Toast.makeText(context, "" + volleyError.networkResponse, Toast.LENGTH_SHORT).show();
                    PrivateDataProcess(volleyError.networkResponse.toString());
                }) {
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        System.out.println("-------   auth : " + cookie);
                        HashMap hashMap = new HashMap();
                        hashMap.put("Cookie", cookie);
                        return hashMap;
                    }
                });
                return;
            }
            ConsProgressKit.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        } else {
            ConsProgressKit.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getResources().getString(R.string.open_story_highligth), Toast.LENGTH_SHORT).show();
        }
    }

    interface InstagramAPI {
        @GET("{shortcode}/?__a=1")
        Call<JsonObject> getPostData(@Path("shortcode") String shortcode);
    }

    public final void PrivateDataProcess(String str) {
        System.out.println("-------- - - - ssss : " + str);
        this.requestQueue.add(new JsonObjectRequest(str, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject obj) {
                JSONObject jSONObject = obj;
                if (String.valueOf(jSONObject).contains("{\"title\":\"Restricted Video\",\"description\":\"You must be 18 years old or over to see this video\"}")) {
                    ConsProgressKit.setVisibility(View.GONE);
                    ConsDownload.setClickable(true);
                    loginDialog();
                    return;
                }
                PrivatefinalJsonGet = new GsonBuilder().create().fromJson(String.valueOf(jSONObject), FinalJsonPrivate.class);
                PrivateFinalUrlList privateFinalUrlList = new PrivateFinalUrlList(PrivatefinalJsonGet);
                list.clear();
                list = privateFinalUrlList.getUrlList();
                filename.clear();
                caption = privateFinalUrlList.getCaption();
                hastag = privateFinalUrlList.getHastag();
                mainUrl = inputUrl;
                username = privateFinalUrlList.getUsername();
                picUrl = privateFinalUrlList.getPicUrl();
                profileUrl = privateFinalUrlList.getProfileUrl();
                ConsProgressKit.setVisibility(View.GONE);
                downloadModels.clear();
                allComplete = 0;
                Constants.createFileFolder();
                downloadAdapter = new VideoDownloaderDownloadAdapter(context, downloadModels);
                RlDownloadView.setVisibility(View.VISIBLE);
                RvDownLoaded.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                RvDownLoaded.setAdapter(downloadAdapter);
                Toast.makeText(getActivity(), getResources().getString(R.string.download_started), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i);
                    if (Constants.access$2000(str)) {
                        new DownloadVideoAsyncTask(context).execute(str);
                    } else {
                        new DownloadImageAsyncTask(context).execute(str);
                    }
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                ConsProgressKit.setVisibility(View.GONE);
                ConsDownload.setClickable(true);
                if (volleyError instanceof NetworkError) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                } else if (volleyError instanceof ServerError) {
                    loginDialog();
                } else if (volleyError instanceof AuthFailureError) {
                    loginDialog();
                } else if (volleyError instanceof ParseError) {
                    loginDialog();
                } else if (volleyError instanceof Network) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                } else if (volleyError instanceof TimeoutError) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.slow_internet_connection), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("Cookie", cookie);
                return hashMap;
            }
        });
    }

    private void loginDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_login);
        dialog.setCancelable(true);
        DisplayMetrics metrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setLayout(metrics.widthPixels - 100, -2);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.show();
        ((TextView) dialog.findViewById(R.id.TvWhyLogins)).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                Dialog dial = new Dialog(getActivity());
                dial.setContentView(R.layout.dialog_why_login);
                dial.setCancelable(true);
                dial.show();
                ((TextView) dial.findViewById(R.id.TvGotItDialog)).setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        ProgressKit.setVisibility(View.GONE);
                    }
                });
            }
        });
        ((TextView) dialog.findViewById(R.id.TvLogins)).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                dialog.dismiss();
                ProgressKit.setVisibility(View.GONE);
                startActivity(new Intent(getActivity(), InstagramLoginActivity.class));
            }
        });
    }

    public class DownloadImageAsyncTask extends AsyncTask<String, String, String> {
        private final Context context;
        public int count;
        public FileOutputStream fileOutputStream = null;
        public String savedFileName = "";
        public String savedFilePath = "";

        public DownloadImageAsyncTask(Context context) {
            this.context = context;
        }

        public final String doInBackground(String[] objArr) {
            System.out.println("---------- DownloadImage : doInBackground ");
            String[] strArr = objArr;
            StringBuilder sb = new StringBuilder();
            sb.append(username);
            sb.append(System.currentTimeMillis());
            sb.append(MimeTypeMap.getFileExtensionFromUrl(strArr[0]).equals("mp4") ? ".mp4" : ".jpg");
            this.savedFileName = sb.toString();
            this.savedFilePath = Constants.RootImageDirectoryInsta + "/" + this.savedFileName;
            filename.add(this.savedFileName);
            String name = new File(this.savedFileName).getName();
            int lastIndexOf = name.lastIndexOf(".");
            if (lastIndexOf > 0 && lastIndexOf < name.length() - 1) {
                downloadFileName = name.substring(0, lastIndexOf);
            }
            try {
                URL url = new URL(strArr[0]);
                URLConnection openConnection = url.openConnection();
                openConnection.connect();
                int contentLength = openConnection.getContentLength();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream(), 8192);
                if (Build.VERSION.SDK_INT >= 29) {
                    ContentResolver contentResolver = requireActivity().getContentResolver();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_display_name", downloadFileName + ".jpg");
                    contentValues.put("mime_type", "image/jpeg");
                    contentValues.put("relative_path", Constants.ROOT_PATH_INSTAGRAM_IMAGE);
                    Uri insert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    Objects.requireNonNull(insert);
                    FileOutputStream fileOutputStream2 = (FileOutputStream) contentResolver.openOutputStream(insert);
                    this.fileOutputStream = fileOutputStream2;
                    Objects.requireNonNull(fileOutputStream2);
                } else {
                    File file = new File(context.getFilesDir().toString());
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    String absolutePath = file.getAbsolutePath();
                    this.fileOutputStream = new FileOutputStream(new File(absolutePath, downloadFileName + ".jpg"));
                }
                byte[] bArr = new byte[1024];
                long j = 0;
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    this.count = read;
                    if (read != -1) {
                        j += read;
                        publishProgress("" + ((int) ((100 * j) / ((long) contentLength))));
                        this.fileOutputStream.write(bArr, 0, this.count);
                    } else {
                        this.fileOutputStream.flush();
                        this.fileOutputStream.close();
                        bufferedInputStream.close();
                        return null;
                    }
                }
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                return null;
            }
        }

        public final void onPostExecute(String obj) {
            super.onPostExecute(obj);
            System.out.println("---------- DownloadImage : onPostExecute ");
         /*   addDowloadDataToDatabase(this.savedFileName, this.savedFilePath);
            if (allComplete == list.size() - 1) {
                ConsDownload.setClickable(true);
                EdtUrl.setText("");
                title1.setText(getResources().getString(R.string.recent_download));
                addIntoDatabase();
                Dialog dialog = imageDownloadDialog;
                if (dialog != null && dialog.isShowing()) {
                    imageDownloadDialog.dismiss();
                    imageDownloadDialog = null;
                }
                downloadImageModelArrayList = sqliteDatabaseHelper.getAllDownloadFile();
                if (ll_yourDownload.getVisibility() == 8) {
                    ll_yourDownload.setVisibility(0);
                    tv_noDataFound.setVisibility(8);
                }
                Collections.reverse(downloadImageModelArrayList);
                imageAdapter.addData(downloadImageModelArrayList);*/
            return;
        }
//            allComplete++;
    }

    public final void onPreExecute() {
        System.out.println("---------- DownloadImage : onPreExecute ");
//            if (imageDownloadDialog == null) {
//                imageDownloadDialog = new Dialog(getActivity());
//                imageDownloadDialog.requestWindowFeature(1);
//                imageDownloadDialog.getWindow().requestFeature(1);
//                imageDownloadDialog.setContentView(R.layout.dialog_custom_progress_layout);
//                imageDownloadDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//                imageDownloadDialog.setCanceledOnTouchOutside(false);
//                imageDownloadDialog.setCancelable(false);
//                DisplayMetrics displayMetrics = new DisplayMetrics();
//                requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//                Window window = imageDownloadDialog.getWindow();
//                Objects.requireNonNull(window);
//                window.setLayout(displayMetrics.widthPixels - 150, -2);
//                imageDownloadDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//            }
//            progressDownload = (ProgressBar) imageDownloadDialog.findViewById(R.id.progressDownload);
//            progressBar.setProgress(0);
//            TextView textView = (TextView) imageDownloadDialog.findViewById(R.id.tvCancel);
//            ((TextView) imageDownloadDialog.findViewById(R.id.tvTitle)).setText(getResources().getString(R.string.downloading));
//            ((TextView) imageDownloadDialog.findViewById(R.id.tvMessage)).setText(getResources().getString(R.string.wait_for_few_minute));
//            Dialog dialog = imageDownloadDialog;
//            if (dialog != null && !dialog.isShowing()) {
//                imageDownloadDialog.show();
//            }
    }

    public final void onProgressUpdate(String[] objArr) {
        String[] strArr = objArr;
        System.out.println("---------- DownloadImage : onProgressUpdate " + strArr[0]);
//            super.onProgressUpdate(strArr);
//            progressDownload.setProgress(Integer.parseInt(strArr[0]));
    }


    public class DownloadVideoAsyncTask extends AsyncTask<String, String, String> {
        private final Context context;
        public int count;
        public FileOutputStream fileOutputStream = null;
        public String savedFileName = "";
        public String savedFilePath = "";
        private String downloadFileName;
        private String username;
        private ArrayList<String> filename;

        public DownloadVideoAsyncTask(Context context) {
            this.context = context;
        }

        public final String doInBackground(String[] objArr) {
            System.out.println("---------- DownloadVideo : doInBackground ");
            String[] strArr = objArr;
            StringBuilder sb = new StringBuilder();
            sb.append(username);
            sb.append(System.currentTimeMillis());
            sb.append(MimeTypeMap.getFileExtensionFromUrl(strArr[0]).equals("mp4") ? ".mp4" : ".jpg");
            this.savedFileName = sb.toString();
            this.savedFilePath = Constants.RootDirectoryInsta + "/" + this.savedFileName;
            filename.add(this.savedFileName);
            String name = new File(this.savedFileName).getName();
            int lastIndexOf = name.lastIndexOf(".");
            if (lastIndexOf > 0 && lastIndexOf < name.length() - 1) {
                downloadFileName = name.substring(0, lastIndexOf);
            }
            try {
                URL url = new URL(strArr[0]);
                URLConnection openConnection = url.openConnection();
                openConnection.connect();
                int contentLength = openConnection.getContentLength();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream(), 8192);
                if (Build.VERSION.SDK_INT >= 29) {
                    ContentValues contentValues = new ContentValues();
                    ContentResolver contentResolver = context.getContentResolver();
                    contentValues.put("relative_path", Constants.ROOT_PATH_INSTAGRAM_VIDEO);
                    contentValues.put("_display_name", downloadFileName);
                    contentValues.put("mime_type", "video/mp4");
                    contentValues.put("date_added", Long.valueOf(System.currentTimeMillis() / 1000));
                    Uri insert = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
                    Objects.requireNonNull(insert);
                    FileOutputStream fileOutputStream2 = (FileOutputStream) contentResolver.openOutputStream(insert);
                    this.fileOutputStream = fileOutputStream2;
                    Objects.requireNonNull(fileOutputStream2);
                } else {
                    File file = new File(Constants.RootDirectoryInsta);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    String absolutePath = file.getAbsolutePath();
                    this.fileOutputStream = new FileOutputStream(new File(absolutePath, downloadFileName + ".mp4"));
                }
                byte[] bArr = new byte[1024];
                long j = 0;
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    this.count = read;
                    if (read != -1) {
                        j += read;
                        publishProgress("" + ((int) ((100 * j) / ((long) contentLength))));
                        this.fileOutputStream.write(bArr, 0, this.count);
                    } else {
                        this.fileOutputStream.flush();
                        this.fileOutputStream.close();
                        bufferedInputStream.close();
                        return null;
                    }
                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        public final void onPostExecute(String obj) {
            super.onPostExecute(obj);
            System.out.println("---------- DownloadVideo : onPostExecute ");
//        addDowloadDataToDatabase(this.savedFileName, this.savedFilePath);
//        if (allComplete == list.size() - 1) {
//            ConsDownload.setClickable(true);
//            EdtUrl.setText("");
//            title1.setText(getResources().getString(R.string.recent_download));
//            String str = Constants.RootDirectoryInsta;
//            addIntoDatabase();
//            Dialog dialog = dialog;
//            if (dialog != null && dialog.isShowing()) {
//                dialog.dismiss();
//                dialog = null;
//            }
//            downloadImageModelArrayList = sqliteDatabaseHelper.getAllDownloadFile();
//            if (LlDownload.getVisibility() == View.GONE) {
//                LlDownload.setVisibility(View.VISIBLE);
//                IvNotFound.setVisibility(View.GONE);
//            }
//            Collections.reverse(downloadImageModelArrayList);
//            imageAdapter.addData(downloadImageModelArrayList);
//            return;
//        }
//        allComplete++;
        }

        public final void onPreExecute() {
            super.onPreExecute();
            System.out.println("---------- DownloadVideo : onPreExecute ");
//        if (dialog == null) {
//            dialog = new Dialog(getActivity());
//            dialog.requestWindowFeature(1);
//            dialog.getWindow().requestFeature(1);
//            dialog.setContentView(R.layout.dialog_custom_progress_layout);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.setCancelable(false);
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            Window window = dialog.getWindow();
//            Objects.requireNonNull(window);
//            window.setLayout(displayMetrics.widthPixels - 150, -2);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//        }
//        progressDownload = (ProgressBar) dialog.findViewById(R.id.progressDownload);
//        progressBar.setProgress(0);
//        ((TextView) dialog.findViewById(R.id.tvTitle)).setText(getResources().getString(R.string.downloading));
//        ((TextView) dialog.findViewById(R.id.tvMessage)).setText(getResources().getString(R.string.wait_for_few_minute));
//        Dialog dialog = dialog;
//        if (dialog != null && !dialog.isShowing()) {
//            dialog.show();
//        }
        }

        public final void onProgressUpdate(String[] objArr) {
            String[] strArr = objArr;
            super.onProgressUpdate(strArr);
            System.out.println("---------- DownloadVideo : onProgressUpdate " + strArr[0]);
//        progressDownload.setProgress(Integer.parseInt(strArr[0]));
        }
    }
}
