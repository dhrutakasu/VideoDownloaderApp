package com.app.videodownloaderapp.Ui.Fragments;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Models.VideoDownloaderDownloadModel;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.VideoDownloaderDownloadAdapter;
import com.app.videodownloaderapp.Util.FinalJsonGet;
import com.app.videodownloaderapp.Util.FinalJsonPrivate;
import com.app.videodownloaderapp.Util.MediaType;
import com.app.videodownloaderapp.Util.PrivateFinalUrlList;
import com.app.videodownloaderapp.Util.PublicFinalUrlList;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
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
    private ArrayList<VideoDownloaderDownloadModel> downloadModels = new ArrayList();
    private VideoDownloaderDownloadAdapter downloadAdapter;
    private int allComplete = 0;
    public String cookie = "null";
    private String downloadFileName;
    private Dialog imageDownloadDialog;

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
        ConsProgressKit = (ConstraintLayout) view.findViewById(R.id.ConsProgressKit);
        ProgressKit = (SpinKitView) view.findViewById(R.id.ProgressKit);
        TvMsg = (TextView) view.findViewById(R.id.TvMsg);
    }

    private void VideoInitListerns() {
        CardPaste.setOnClickListener(this);
        ConsDownload.setOnClickListener(this);
        IvClear.setOnClickListener(this);
    }

    private void VideoInitActions() {
        this.requestQueue = Volley.newRequestQueue(context);
        this.filename = new ArrayList<>();
        this.cookie = getActivity().getSharedPreferences("cookie", 0).getString("flag", (String) null);
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

    @Override
    public void onResume() {
        super.onResume();
        this.cookie = getActivity().getSharedPreferences("cookie", 0).getString("instagram", (String) null);
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
        inputUrl = str;
        MediaType mediaType = new MediaType(str);
        if (mediaType.isValidLink().equals("notValid")) {
            ConsProgressKit.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getResources().getString(R.string.enter_instagram_url), Toast.LENGTH_SHORT).show();
        } else if (mediaType.isValidLink().equals("highlight")) {
            ConsProgressKit.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getResources().getString(R.string.toast_stroy_highligth_notsupported), Toast.LENGTH_SHORT).show();
        } else if (mediaType.isValidLink().equals("post")) {
            String m2 = inputUrl.split(Pattern.quote("?"))[0] + "?__a=1&__d=dis";
            if (Constants.isInternetConnected(getActivity())) {
                ConsDownload.setClickable(false);
                inputUrl = EdtUrl.getText().toString().trim();
                requestQueue.add(new JsonObjectRequest(0, m2, (JSONObject) null, obj -> {
                    JSONObject jSONObject = (JSONObject) obj;
                    if (String.valueOf(jSONObject).contains("{\"title\":\"Restricted Video\",\"description\":\"You must be 18 years old or over to see this video\"}")) {
                        PrivateDataProcess(obj.toString());
                        return;
                    }
                    PublicfinalJsonGet = (FinalJsonGet) new GsonBuilder().create().fromJson(String.valueOf(jSONObject), FinalJsonGet.class);
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
                        String str1 = list.get(i);
                        if (Constants.access$2000(str1)) {
                            new DownloadVideoAsyncTask(context).execute(new String[]{str1});
                        } else {
                            new DownloadImageAsyncTask(context).execute(new String[]{str1});
                        }
                    }
                }, volleyError -> PrivateDataProcess(volleyError.toString())));
                return;
            }
            ConsProgressKit.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        } else {
            ConsProgressKit.setVisibility(View.GONE);
            Toast.makeText(getActivity(), getResources().getString(R.string.open_story_highligth), Toast.LENGTH_SHORT).show();
        }
    }

    public final void PrivateDataProcess(String str) {
        this.requestQueue.add(new JsonObjectRequest(str, new Response.Listener<JSONObject>() {
            /* JADX WARNING: type inference failed for: r6v10, types: [java.util.List<com.basic.videodownloader.allmovie.model.VideoDownloaderDownloadModel>, java.util.ArrayList] */
            public void onResponse(JSONObject obj) {
                JSONObject jSONObject = (JSONObject) obj;
                if (String.valueOf(jSONObject).contains("{\"title\":\"Restricted Video\",\"description\":\"You must be 18 years old or over to see this video\"}")) {
                    ConsProgressKit.setVisibility(View.GONE);
                    ConsDownload.setClickable(true);
//                    loginDialog();
                    return;
                }
                PrivatefinalJsonGet = (FinalJsonPrivate) new GsonBuilder().create().fromJson(String.valueOf(jSONObject), FinalJsonPrivate.class);
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
                        new DownloadVideoAsyncTask(context).execute(new String[]{str});
                    } else {
                        new DownloadImageAsyncTask(context).execute(new String[]{str});
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
//                    loginDialog();
                } else if (volleyError instanceof AuthFailureError) {
//                    loginDialog();
                } else if (volleyError instanceof ParseError) {
//                    loginDialog();
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
            String[] strArr = (String[]) objArr;
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
                    File file = new File(Constants.RootImageDirectoryInsta);
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
                        j += (long) read;
                        publishProgress(new String[]{"" + ((int) ((100 * j) / ((long) contentLength)))});
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
            super.onPostExecute((String) obj);
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
        String[] strArr = (String[]) objArr;
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
            String[] strArr = (String[]) objArr;
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
                        j += (long) read;
                        publishProgress(new String[]{"" + ((int) ((100 * j) / ((long) contentLength)))});
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
            super.onPostExecute((String) obj);
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
            String[] strArr = (String[]) objArr;
            super.onProgressUpdate(strArr);
            System.out.println("---------- DownloadVideo : onProgressUpdate " + strArr[0]);
//        progressDownload.setProgress(Integer.parseInt(strArr[0]));
        }
    }
}
