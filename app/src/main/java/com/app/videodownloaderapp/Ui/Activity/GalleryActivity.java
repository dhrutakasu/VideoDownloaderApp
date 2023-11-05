package com.app.videodownloaderapp.Ui.Activity;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Models.ImageFolderModel;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.FoldersAdapter;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GalleryActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int GET_IMAGE_CODE = 188;
    private Context context;
    private TextView TvTitle;
    private ImageView IvBack, IvNoImageGallery;
    private RecyclerView RvFolderGallery;
    private ProgressBar PbGallery;
    private ArrayList<ImageFolderModel> imageModels = new ArrayList<>();
    private FoldersAdapter foldersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        RvFolderGallery = (RecyclerView) findViewById(R.id.RvFolderImage);
        PbGallery = (ProgressBar) findViewById(R.id.PbImage);
        IvNoImageGallery = (ImageView) findViewById(R.id.IvNoImageGallery);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText("Gallery");
        RvFolderGallery.setLayoutManager(new GridLayoutManager(context, 2, RecyclerView.VERTICAL, false));
        PbGallery.setVisibility(View.VISIBLE);
        new LoadImagesWithFolders().execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IvBack:
                finish();
                break;
        }
    }

    public class LoadImagesWithFolders extends AsyncTask<Void, Void, Void> {

        public final void onPreExecute() {
            super.onPreExecute();
            PbGallery.setVisibility(View.VISIBLE);
            imageModels.clear();
        }

        public final Void doInBackground(Void[] objArr) {
            imageModels = new ArrayList<>();
            ArrayList gallertList = new ArrayList();
            String id = "_id";
            Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{id, "_data", "_display_name", "bucket_display_name", "bucket_id"}, null, null, null);
            if (cursor != null) {
                try {
                    cursor.moveToFirst();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            while (true) {
                ImageFolderModel imageFolderModel;
                cursor.getString(cursor.getColumnIndexOrThrow("_display_name"));
                long LongId = cursor.getLong(cursor.getColumnIndexOrThrow(id));
                String FName = cursor.getString(cursor.getColumnIndexOrThrow("bucket_display_name"));
                String FPic = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                String BucketId = cursor.getString(cursor.getColumnIndexOrThrow("bucket_id"));
                System.out.println("------- FPIC 1: "+FName);
                System.out.println("------- FPIC 2: "+FPic);
                if (FName!=null) {
                    String path = FPic.substring(0, FPic.lastIndexOf(FName + "/")) + FName + "/";
                    System.out.println("------- FPIC 3: "+path);
                    if (!gallertList.contains(path)) {
                        Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, LongId);
                        gallertList.add(path);
                        imageFolderModel = new ImageFolderModel(FName, BucketId, FPic, path, uri);
                        imageFolderModel.IncreasePic();
                        imageModels.add(imageFolderModel);
                    } else {
                        for (int i = 0; i < imageModels.size(); i++) {
                            if (imageModels.get(i).getPath().equals(path)) {
                                imageModels.get(i).setFirstPic(FPic);
                                imageModels.get(i).IncreasePic();
                            }
                        }
                    }
                }else {
                    Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, LongId);
                    String path = FPic.substring(0, FPic.lastIndexOf("/")) + "/";
                    gallertList.add(path);
                    imageFolderModel = new ImageFolderModel(FName, BucketId, FPic, path, uri);
                    imageFolderModel.IncreasePic();
                    imageModels.add(imageFolderModel);
                }
                if (!cursor.moveToNext()) {
                    cursor.close();
                    return null;
                }
            }
        }

        public final void onPostExecute(Void obj) {
            super.onPostExecute((Void) obj);
            if (imageModels.size() == 0) {
                PbGallery.setVisibility(View.VISIBLE);
                RvFolderGallery.setVisibility(View.GONE);
                IvNoImageGallery.setVisibility(View.VISIBLE);
                return;
            }
            PbGallery.setVisibility(View.GONE);
            RvFolderGallery.setVisibility(View.VISIBLE);
            IvNoImageGallery.setVisibility(View.GONE);
            foldersAdapter = new FoldersAdapter(context, imageModels, (path, fName, Bid) -> {
                Intent intent = new Intent(context, GalleryFolderImagesActivity.class);
                intent.putExtra(Constants.PATH, path);
                intent.putExtra(Constants.FOLDER_NAME, fName);
                intent.putExtra(Constants.BUCKET_ID, Bid);
                intent.putExtra(Constants.TYPE, "Photo");
                startActivityForResult(intent, GET_IMAGE_CODE);
            });
            RvFolderGallery.setAdapter(foldersAdapter);
            PbGallery.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GET_IMAGE_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        Intent path = new Intent();
                            path.putExtra(Constants.SELECTED_PATH, data.getStringExtra(Constants.SELECTED_PATH));
                        setResult(RESULT_OK, path);
                        finish();
                        break;
                }
                break;
        }
    }
}