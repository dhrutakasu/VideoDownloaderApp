package com.app.videodownloaderapp.Ui.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Models.DpMakerModelItem;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Adapters.AllFrameAdapter;
import com.app.videodownloaderapp.Ui.Adapters.CategoryNameAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.view.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DpMakerEditActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private static final int CAMERA_CODE = 166;
    private static final int GALLERY_CODE = 177;
    private Context context;
    private TextView TvTitle;
    private ImageView IvBack, IvPreview, IvDone;
    private ImageView IvGallery, IvImgEdit, IvChooseImage;
    private ProgressBar ProgressEdit;
    private LinearLayout LlBorderView, LlFlipView, LlRotateView;
    private ConstraintLayout ConsFlipView, ConsRotateView;
    private TextView TvHorizontal, TvVertical, TvDegree, TvSeekValue;
    private SeekBar SeekRotate;
    private RecyclerView RvAllFrame, RvCategoryName;
    private String Images;
    private int Id;
    private String CatName;
    private String ImagesArray;
    private String Items;
    private DpMakerModelItem dpMakerModelItem;
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private int selectedPosition = 0;
    private CategoryNameAdapter categoryNameAdapter;
    private AllFrameAdapter allFrameAdapter;
    private ArrayList arrayList;
    private DpMakerModelItem fromJson;
    private LinearLayout LlBorder, LlFlip, LlRotate;
    private String selectedpath = "";
    private String IntentSelectedpath = "";
    private int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dp_maker_edit);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
    }

    private void VideoInitViews() {
        context = this;
        TvTitle = findViewById(R.id.TvTitle);
        IvBack = findViewById(R.id.IvBack);
        IvDone = findViewById(R.id.IvDone);
        IvPreview = findViewById(R.id.IvPreview);
        IvGallery = findViewById(R.id.IvGallery);
        IvImgEdit = findViewById(R.id.IvImgEdit);
        ProgressEdit = findViewById(R.id.ProgressEdit);
        IvChooseImage = findViewById(R.id.IvChooseImage);
        LlBorder = findViewById(R.id.LlBorder);
        LlFlip = findViewById(R.id.LlFlip);
        LlRotate = findViewById(R.id.LlRotate);
        LlBorderView = findViewById(R.id.LlBorderView);
        LlFlipView = findViewById(R.id.LlFlipView);
        LlRotateView = findViewById(R.id.LlRotateView);
        ConsFlipView = findViewById(R.id.ConsFlipView);
        ConsRotateView = findViewById(R.id.ConsRotateView);
        TvHorizontal = findViewById(R.id.TvHorizontal);
        TvVertical = findViewById(R.id.TvVertical);
        SeekRotate = findViewById(R.id.SeekRotate);
        TvDegree = findViewById(R.id.TvDegree);
        TvSeekValue = findViewById(R.id.TvSeekValue);
        RvAllFrame = findViewById(R.id.RvAllFrame);
        RvCategoryName = findViewById(R.id.RvCategoryName);
    }

    private void VideoInitListerns() {
        IvBack.setOnClickListener(this);
        IvGallery.setOnClickListener(this);
        LlBorderView.setOnClickListener(this);
        LlFlipView.setOnClickListener(this);
        LlRotateView.setOnClickListener(this);
        IvBack.setOnClickListener(this);
        TvHorizontal.setOnClickListener(this);
        TvVertical.setOnClickListener(this);
        IvPreview.setOnClickListener(this);
        SeekRotate.setOnSeekBarChangeListener(this);
    }

    private void VideoInitActions() {
        IvBack.setVisibility(View.VISIBLE);
        TvTitle.setText("Edit Image");
        IvPreview.setVisibility(View.VISIBLE);
        IvDone.setVisibility(View.VISIBLE);

        Images = getIntent().getStringExtra(Constants.DPMakerItem);
        Id = getIntent().getIntExtra(Constants.DPMakerPos, 0);
        CatName = getIntent().getStringExtra(Constants.DpMakerName);
        ImagesArray = getIntent().getStringExtra(Constants.DPMakerValue);
        Items = getIntent().getStringExtra(Constants.DpMakerItemList);

        loadAnimation();
        RvCategoryName.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        categoryNameAdapter = new CategoryNameAdapter(this, selectedPosition, arrayList, new CategoryNameAdapter.CategoryClickListener() {
            public final void seCategoryListner(DpMakerModelItem videoDownloaderCategoryModel) {
                AllFrameAdapter videoDownloaderAllFrameAdapter = allFrameAdapter;
                videoDownloaderAllFrameAdapter.dpMakerModelItem = videoDownloaderCategoryModel;
                ArrayList<String> arrayList2 = new ArrayList<>();
                videoDownloaderAllFrameAdapter.stringArrayList = arrayList2;
                arrayList2.addAll(videoDownloaderCategoryModel.getImages());
                videoDownloaderAllFrameAdapter.notifyDataSetChanged();
            }
        });

        RvCategoryName.getLayoutManager().scrollToPosition(selectedPosition);
        RvCategoryName.setAdapter(categoryNameAdapter);
        RvAllFrame.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        AllFrameAdapter allFrameAdapter = new AllFrameAdapter(context, stringArrayList, fromJson, (i, modelItem) -> {
            Images = modelItem.getPath() + "/" + modelItem.getImages().get(i);
            loadAnimation();
        });
        this.allFrameAdapter = allFrameAdapter;
        RvAllFrame.setAdapter(allFrameAdapter);
    }

    private void loadAnimation() {
        ProgressEdit.setVisibility(View.VISIBLE);
        StrictMode.VmPolicy.Builder Sbuilder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(Sbuilder.build());

        StringBuilder builder = new StringBuilder().append("https://api.appcodiz.com/DPMaker/");
        builder.append(Images);
        System.out.println("00000000000 L: " + builder);
        Picasso.get()
                .load(builder.toString())
                .into(IvImgEdit);
        IvImgEdit.post(new Runnable() {
            @Override
            public void run() {
                ProgressEdit.setVisibility(View.GONE);
                RotateAnimation rotateAnimation = new RotateAnimation(CropImageView.DEFAULT_ASPECT_RATIO, 360.0f, 1, 0.5f, 1, 0.5f);
                rotateAnimation.setDuration(1000);
                rotateAnimation.setInterpolator(new LinearInterpolator());
                IvImgEdit.startAnimation(rotateAnimation);
            }
        });
        fromJson = new Gson().fromJson(ImagesArray, DpMakerModelItem.class);
        dpMakerModelItem = fromJson;
        stringArrayList = (ArrayList) fromJson.getImages();
        arrayList = new Gson().fromJson(Items, new TypeToken<ArrayList<DpMakerModelItem>>() {
        }.getType());
        int i = 0;
        while (true) {
            if (i >= arrayList.size()) {
                break;
            } else if (((DpMakerModelItem) arrayList.get(i)).getName().equals(CatName)) {
                selectedPosition = i;
                break;
            } else {
                i++;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IvBack:
                finish();
                break;
            case R.id.IvGallery:
                SetGallery();
                break;
            case R.id.LlBorderView:
                SetBoarder();
                break;
            case R.id.LlFlipView:
                if (selectedpath.equalsIgnoreCase("")) {
                    Toast.makeText(context, context.getResources().getString(R.string.please_selectphoto), Toast.LENGTH_SHORT).show();
                } else {
                    SetFlip();
                }
                break;
            case R.id.LlRotateView:
                if (selectedpath.equalsIgnoreCase("")) {
                    Toast.makeText(context, context.getResources().getString(R.string.please_selectphoto), Toast.LENGTH_SHORT).show();
                } else {
                    SetRotate();
                }
                break;
            case R.id.TvHorizontal:
                if (selectedpath.equalsIgnoreCase("")) {
                    Toast.makeText(context, context.getResources().getString(R.string.please_selectphoto), Toast.LENGTH_SHORT).show();
                } else {
                    if (IvChooseImage.getRotationY() == CropImageView.DEFAULT_ASPECT_RATIO) {
                        IvChooseImage.setRotationY(180.0f);
                        IvChooseImage.getRotationY();
                        return;
                    }
                    IvChooseImage.setRotationY(CropImageView.DEFAULT_ASPECT_RATIO);
                    IvChooseImage.getRotationY();
                }
                break;
            case R.id.TvVertical:
                if (selectedpath.equalsIgnoreCase("")) {
                    Toast.makeText(context, context.getResources().getString(R.string.please_selectphoto), Toast.LENGTH_SHORT).show();
                } else {
                    if (IvChooseImage.getRotationX() == CropImageView.DEFAULT_ASPECT_RATIO) {
                        IvChooseImage.setRotationX(180.0f);
                        IvChooseImage.getRotationX();
                        return;
                    }
                    IvChooseImage.setRotationX(CropImageView.DEFAULT_ASPECT_RATIO);
                    IvChooseImage.getRotationX();
                }
                break;
            case R.id.IvPreview:
                if (selectedpath.equalsIgnoreCase("")) {
                    Toast.makeText(context, context.getResources().getString(R.string.please_selectphoto), Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, PreviewActivity.class);
                    intent.putExtra(Constants.PERVIEW, selectedpath);
                    intent.putExtra(Constants.SELECTED_BORDER, Images);
                    intent.putExtra(Constants.FLIP_HORIZONTAL, IvChooseImage.getRotationY());
                    intent.putExtra(Constants.FLIP_VERTICAL, IvChooseImage.getRotationX());
                    intent.putExtra(Constants.ROTATE, IvImgEdit.getRotation());
                    startActivity(intent);
                }
                break;
        }
    }

    private void SetGallery() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_gallery);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Window window = dialog.getWindow();
        window.setLayout(displayMetrics.widthPixels - 100, -2);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        TextView TvCamera = (TextView) dialog.findViewById(R.id.TvCamera);
        TextView TvGallery = (TextView) dialog.findViewById(R.id.TvGallery);
        TextView TvCancelGallery = (TextView) dialog.findViewById(R.id.TvCancelGallery);
        TvCamera.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                dialog.dismiss();
                String s3 = Manifest.permission.CAMERA;
                Dexter.withContext(context)
                        .withPermissions(s3)
                        .withListener(new MultiplePermissionsListener() {
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    checkCameraPermission();
                                }
                                if (report.isAnyPermissionPermanentlyDenied()) {
                                    Constants.showSettingsDialog(DpMakerEditActivity.this);
                                }
                            }

                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken permissionToken) {
                                Constants.showPermissionDialog(DpMakerEditActivity.this, permissionToken);
                            }
                        })
                        .onSameThread()
                        .check();
            }
        });
        TvGallery.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                onPositiveButtonClick(dialog);
            }
        });
        TvCancelGallery.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void onPositiveButtonClick(Dialog dialog) {
        dialog.dismiss();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            String s2 = Manifest.permission.READ_MEDIA_VIDEO;
            String s3 = Manifest.permission.READ_MEDIA_IMAGES;
            Dexter.withContext(context)
                    .withPermissions(s2, s3)
                    .withListener(new MultiplePermissionsListener() {
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()) {
                                checkGalleryPermission();
                            }
                            if (report.isAnyPermissionPermanentlyDenied()) {
                                Constants.showSettingsDialog(DpMakerEditActivity.this);
                            }
                        }

                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken permissionToken) {
                            Constants.showPermissionDialog(DpMakerEditActivity.this, permissionToken);
                        }
                    })
                    .onSameThread()
                    .check();
        } else {
            String s2 = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            String s3 = Manifest.permission.READ_EXTERNAL_STORAGE;
            Dexter.withContext(context)
                    .withPermissions(s2, s3)
                    .withListener(new MultiplePermissionsListener() {
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()) {
                                checkGalleryPermission();
                            }
                            if (report.isAnyPermissionPermanentlyDenied()) {
                                Constants.showSettingsDialog(DpMakerEditActivity.this);
                            }
                        }

                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken permissionToken) {
                            Constants.showPermissionDialog(DpMakerEditActivity.this, permissionToken);
                        }
                    })
                    .onSameThread()
                    .check();
        }
    }

    private void checkGalleryPermission() {
        startActivityForResult(new Intent(context, GalleryActivity.class), GALLERY_CODE);
    }

    private void checkCameraPermission() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        try {
            photoFile = createCameraImageFile();
        } catch (IOException ex) {
            ex.getMessage();
        }
        Uri photoURI = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", photoFile);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        startActivityForResult(cameraIntent, CAMERA_CODE);
    }

    private File createCameraImageFile() throws IOException {
        String imageFileName = "JPEG_SAVE_IMAGE";
        File storageDir = getFilesDir();
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        selectedpath = image.getAbsolutePath();
        return image;
    }

    private void SetBoarder() {
        RvCategoryName.setVisibility(View.VISIBLE);
        RvAllFrame.setVisibility(View.VISIBLE);
        ConsFlipView.setVisibility(View.GONE);
        ConsRotateView.setVisibility(View.GONE);

        LlBorder.setAlpha(1.0f);
        LlFlip.setAlpha(0.5f);
        LlRotate.setAlpha(0.5f);
    }

    private void SetFlip() {
        RvCategoryName.setVisibility(View.GONE);
        RvAllFrame.setVisibility(View.GONE);
        ConsRotateView.setVisibility(View.GONE);
        ConsFlipView.setVisibility(View.VISIBLE);

        LlBorder.setAlpha(0.5f);
        LlFlip.setAlpha(1.0f);
        LlRotate.setAlpha(0.5f);
    }

    private void SetRotate() {
        RvCategoryName.setVisibility(View.GONE);
        RvAllFrame.setVisibility(View.GONE);
        ConsFlipView.setVisibility(View.GONE);
        ConsRotateView.setVisibility(View.VISIBLE);

        LlBorder.setAlpha(0.5f);
        LlFlip.setAlpha(0.5f);
        LlRotate.setAlpha(1.0f);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        File f = new File(selectedpath);
                        System.out.println("------------  datra fff : " + f.getAbsolutePath());
                        UCrop.of(Uri.fromFile(new File(f.getAbsolutePath())), Uri.fromFile(new File(f.getAbsolutePath())))
                                .withAspectRatio(1, 1)
                                .start(DpMakerEditActivity.this);
                        break;
                }
                break;
            case UCrop.REQUEST_CROP:
                switch (resultCode) {
                    case RESULT_OK:
                        System.out.println("------------  datra fff Ucrop: " + data);
                        Uri output = UCrop.getOutput(data);
                        System.out.println("------------  datra fff Ucrop output: " + output.getPath());
                        if (output != null) {
                            System.out.println("------------  datra fff Ucrop outputq: " + output.getPath());
                            IvChooseImage.setImageBitmap(BitmapFactory.decodeFile(output.getPath()));
                            selectedpath = String.valueOf(output.getPath());
                            IntentSelectedpath = String.valueOf(output.getPath());
                            return;
                        }
                        Toast.makeText(context, getResources().getString(R.string.cannot_retrieve_cropped_image), Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case GALLERY_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        if (data.getStringExtra(Constants.SELECTED_PATH) != null) {
                            IvChooseImage.setImageBitmap(BitmapFactory.decodeFile(data.getStringExtra(Constants.SELECTED_PATH)));
                            selectedpath = String.valueOf(data.getStringExtra(Constants.SELECTED_PATH));
                            IntentSelectedpath = String.valueOf(data.getStringExtra(Constants.SELECTED_PATH));
                            return;
                        }
                        Toast.makeText(context, getResources().getString(R.string.cannot_retrieve_cropped_image), Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        progress = i;
        IvImgEdit.setRotation((float) i);
        IvImgEdit.getRotation();
        TvSeekValue.setText(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}