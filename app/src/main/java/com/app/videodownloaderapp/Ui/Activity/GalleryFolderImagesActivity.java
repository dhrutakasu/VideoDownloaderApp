package com.app.videodownloaderapp.Ui.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Models.FolderImagesModel;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.FolderImagesAdapter;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;

public class GalleryFolderImagesActivity extends AppCompatActivity implements View.OnClickListener {
    private final int GALLERY_CODE = 200;
    private Context context;
    private TextView TvTitle;
    private ImageView IvBack;
    private String StrFolderName;
    private String StrBucketId;
    private ImageView IvNoImage;
    private RecyclerView RvFolderImage;
    private ProgressBar PbImage;
    private ArrayList<FolderImagesModel> folderImages = new ArrayList<>();
    private FolderImagesAdapter imagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_folder_images);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = (TextView) findViewById(R.id.TvTitle);
        IvBack = (ImageView) findViewById(R.id.IvBack);
        RvFolderImage = (RecyclerView) findViewById(R.id.RvFolderImage);
        PbImage = (ProgressBar) findViewById(R.id.PbImage);
        IvNoImage = (ImageView) findViewById(R.id.IvNoImage);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        StrFolderName = getIntent().getStringExtra(Constants.FOLDER_NAME);
        StrBucketId = getIntent().getStringExtra(Constants.BUCKET_ID);
        TvTitle.setText(StrFolderName);
        RvFolderImage.setLayoutManager(new GridLayoutManager(context, 3));
        PbImage.setVisibility(View.VISIBLE);
        new LoadImages().execute();
    }

    public class LoadImages extends AsyncTask<Void, Void, Void> {

        public final void onPreExecute() {
            super.onPreExecute();
            PbImage.setVisibility(View.VISIBLE);
            folderImages.clear();
        }

        public final Void doInBackground(Void[] objArr) {
            folderImages = new ArrayList<>();
            Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_id", "_display_name", "_size", "height", "width"}, "bucket_id like ? ", new String[]{"%" + StrBucketId + "%"}, null);
            try {
                cursor.moveToFirst();
                do {
                    FolderImagesModel folderImagesModel = new FolderImagesModel();
                    folderImagesModel.setPictureName(cursor.getString(cursor.getColumnIndexOrThrow("_display_name")));
                    folderImagesModel.setPicturePath(cursor.getString(cursor.getColumnIndexOrThrow("_data")));
                    folderImagesModel.setPictureSize(cursor.getString(cursor.getColumnIndexOrThrow("_size")));
                    folderImagesModel.setPictureDimension(cursor.getString(cursor.getColumnIndexOrThrow("width")) + " x " + cursor.getString(cursor.getColumnIndexOrThrow("height")));
                    folderImagesModel.setImageUri(ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cursor.getLong(cursor.getColumnIndexOrThrow("_id"))));
                    folderImages.add(folderImagesModel);
                } while (cursor.moveToNext());
                cursor.close();
                ArrayList arrayList = new ArrayList();
                for (int size = folderImages.size() - 1; size > -1; size--) {
                    arrayList.add(folderImages.get(size));
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public final void onPostExecute(Void obj) {
            super.onPostExecute((Void) obj);
            if (folderImages.size() == 0) {
                RvFolderImage.setVisibility(View.GONE);
                IvNoImage.setVisibility(View.VISIBLE);
                PbImage.setVisibility(View.GONE);
                return;
            }
            RvFolderImage.setVisibility(View.VISIBLE);
            IvNoImage.setVisibility(View.GONE);
            imagesAdapter = new FolderImagesAdapter(context, folderImages, (position, folderImagesModel) ->
                    UCrop.of(folderImagesModel.getImageUri(), Uri.fromFile(new File(context.getCacheDir(), "saveImage.png")))
                            .withAspectRatio(1.0f, 1.0f)
                            .start(GalleryFolderImagesActivity.this));
            RvFolderImage.setAdapter(imagesAdapter);
            PbImage.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UCrop.REQUEST_CROP:
                switch (resultCode) {
                    case RESULT_OK:
                        Uri output = UCrop.getOutput(data);
                        System.out.println("------------  datra fff Ucrop output: " + output.getPath());
                        if (output != null) {
                           Intent intent=new Intent();
                           intent.putExtra(Constants.SELECTED_PATH,output.getPath());
                           setResult(RESULT_OK,intent);
                           finish();
                            return;
                        }
                        Toast.makeText(context, getResources().getString(R.string.cannot_retrieve_cropped_image), Toast.LENGTH_SHORT).show();

                        break;
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IvBack:
                finish();
                break;
            case R.id.IvInstagramTop:
                finish();
                break;
            case R.id.IvUseTop:
                finish();
                break;
        }
    }
}