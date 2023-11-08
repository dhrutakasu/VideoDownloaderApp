package com.app.videodownloaderapp.Ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.app.videodownloaderapp.Models.StatusModel;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.WhatsappRecentAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class WhatsappListFragment extends androidx.fragment.app.Fragment implements View.OnClickListener {
    private Context context;
    private View view;
    private EditText EdtUrl;
    private LinearLayout LlAccessBtn, LLDownloadIV, LlAction;
    private RelativeLayout Rlloader;
    private RelativeLayout RlEmpty;
    private RecyclerView RlImageGrid;
    private CheckBox CbSelectAll;
    private SharedPreferences mPreferences;
    private WhatsappRecentAdapter myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_whatsapp_list, container, false);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
        return view;
    }

    private void VideoInitViews() {
        context = getContext();
        LlAccessBtn = (LinearLayout) view.findViewById(R.id.LlAccessBtn);
        Rlloader = (RelativeLayout) view.findViewById(R.id.Rlloader);
        RlEmpty = (RelativeLayout) view.findViewById(R.id.RlEmpty);
        RlImageGrid = (RecyclerView) view.findViewById(R.id.RlImageGrid);
        LlAction = (LinearLayout) view.findViewById(R.id.LlAction);
        LLDownloadIV = (LinearLayout) view.findViewById(R.id.LLDownloadIV);
        CbSelectAll = (CheckBox) view.findViewById(R.id.CbSelectAll);
    }

    private void VideoInitListerns() {
        LlAccessBtn.setOnClickListener(this);
    }

    private void VideoInitActions() {
        mPreferences = context.getApplicationContext().getSharedPreferences("whatsapp_data", 0);
        if (!mPreferences.getString("whatsapp_tree_uri", "").equals("")) {
            LlAccessBtn.setVisibility(View.GONE);
//            populateGrid();
        } else {
            LlAccessBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.LlAccessBtn) {
            GotoPermission();
        }
    }

    private void GotoPermission() {
        Intent in;
        boolean b = false;
        try {
            getActivity().getApplicationContext().getPackageManager().getPackageInfo("com.whatsapp", 0);
            b = true;
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (b) {
            StorageManager manager = (StorageManager) requireActivity().getSystemService(Context.STORAGE_SERVICE);
            String builder = Environment.getExternalStorageDirectory() +
                    File.separator +
                    "Android/media/com.whatsapp/WhatsApp" +
                    File.separator +
                    "Media" +
                    File.separator +
                    ".Statuses";
            String isDir = new File(builder).isDirectory() ? "Android%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses" : "WhatsApp%2FMedia%2F.Statuses";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                in = manager.getPrimaryStorageVolume().createOpenDocumentTreeIntent();
                in.putExtra("android.provider.extra.INITIAL_URI", Uri.parse(((Uri) in.getParcelableExtra("android.provider.extra.INITIAL_URI")).toString().replace("/root/", "/document/") + "%3A" + isDir));
            } else {
                in = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
                in.putExtra("android.provider.extra.INITIAL_URI", Uri.parse("content://com.android.externalstorage.documents/document/primary%3A" + isDir));
            }
            in.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            in.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            in.addFlags(Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
            in.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            permissionAccessIntent.launch(in);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public ActivityResultLauncher<Intent> permissionAccessIntent = registerForActivityResult(new ActivityResultContract<Intent, Object>() {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Intent intent) {
            return intent;
        }

        @Override
        public Object parseResult(int i, @Nullable Intent intent) {
            return null;
        }
    }, new ActivityResultCallback<Object>() {
        @Override
        public void onActivityResult(Object result) {

            ActivityResult activityResult = (ActivityResult) result;
            if (((ActivityResult) result).getResultCode() == -1) {
                Uri data = activityResult.getData().getData();
                try {
                    requireActivity().getContentResolver().takePersistableUriPermission(data, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FragmentActivity activity = getActivity();
                String uri = data.toString();
                if (mPreferences == null) {
                    mPreferences = activity.getApplicationContext().getSharedPreferences("whatsapp_data", 0);
                }
                mPreferences.edit().putString("whatsapp_tree_uri", uri).apply();
                GetDatas();
            }
        }
    });

    private void GetDatas() {
        if (getFromSdcard() != null) {
            new loadDataAsync().execute();
            return;
        }
        LlAccessBtn.setVisibility(View.VISIBLE);
        RlImageGrid.setVisibility(View.GONE);
    }

    public class loadDataAsync extends AsyncTask<Void, Void, Void> {
        public DocumentFile[] allFiles;
        private ArrayList<StatusModel> statusModels;

        public loadDataAsync() {
        }

        public final Void doInBackground(Void[] objArr) {
            Void[] voidArr = (Void[]) objArr;
            this.allFiles = null;
           statusModels = new ArrayList<>();
            DocumentFile[] fromSdcard = getFromSdcard();
            this.allFiles = fromSdcard;
            Arrays.sort(fromSdcard, new Comparator<DocumentFile>() {
                public int compare(DocumentFile obj, DocumentFile obj2) {
                    return Long.compare(((DocumentFile) obj2).lastModified(), ((DocumentFile) obj).lastModified());
                }
            });
            int i = 0;
            while (true) {
                DocumentFile[] documentFileArr = this.allFiles;
                if (i >= documentFileArr.length - 1) {
                    return null;
                }
                if (!documentFileArr[i].getUri().toString().contains(".nomedia")) {
                    statusModels.add(new StatusModel(allFiles[i].getUri().toString()));
                }
                i++;
            }
        }

        public final void onPostExecute(Void obj) {
            super.onPostExecute((Void) obj);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ArrayList<StatusModel> arrayList = statusModels;
                    if (!(arrayList == null || arrayList.size() == 0)) {
                        WhatsappRecentAdapter whatsappRecentAdapter = myAdapter;
                        ArrayList<StatusModel> arrayList2 = statusModels;
                        ArrayList arrayList3 = new ArrayList();
                        whatsappRecentAdapter.arrayList = arrayList3;
                        arrayList3.addAll(arrayList2);
                        whatsappRecentAdapter.notifyDataSetChanged();
                        RlImageGrid.setVisibility(View.VISIBLE);
                    }
                    Rlloader.setVisibility(View.GONE);
                    ArrayList<StatusModel> arrayList4 = statusModels;
                    if (arrayList4 == null || arrayList4.size() == 0) {
                        RlEmpty.setVisibility(View.VISIBLE);
                    } else {
                        RlEmpty.setVisibility(View.GONE);
                    }
                }
            }, 1000);
        }

        public final void onPreExecute() {
            super.onPreExecute();
            Rlloader.setVisibility(View.VISIBLE);
            RlImageGrid.setVisibility(View.GONE);
            LlAccessBtn.setVisibility(View.GONE);
            RlEmpty.setVisibility(View.GONE);
        }
    }
    public final DocumentFile[] getFromSdcard() {
        DocumentFile fromTreeUri = DocumentFile.fromTreeUri(getActivity(), Uri.parse(mPreferences.getString("whatsapp_tree_uri", "")));
        if (!fromTreeUri.exists() || !fromTreeUri.isDirectory() || !fromTreeUri.canRead() || !fromTreeUri.canWrite()) {
            return null;
        }
        return fromTreeUri.listFiles();
    }
}

