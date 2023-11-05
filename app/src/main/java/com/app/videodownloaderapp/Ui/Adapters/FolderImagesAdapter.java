package com.app.videodownloaderapp.Ui.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.Models.FolderImagesModel;
import com.app.videodownloaderapp.Models.ImageFolderModel;
import com.app.videodownloaderapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class FolderImagesAdapter extends RecyclerView.Adapter<FolderImagesAdapter.MyViewHolder> {
    private final Context context;
    public ArrayList<FolderImagesModel> imageModels;
    private final FolderListener folderListener;
    private Boolean isImage = true;

    public FolderImagesAdapter(Context context, ArrayList<FolderImagesModel> imageModels, FolderListener folderListener) {
        this.context = context;
        this.imageModels = imageModels;
        this.folderListener = folderListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_images, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(imageModels.get(position).getImageUri()).into(holder.IvAlbumPhoto);
        holder.IvPlaceholder.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(view -> folderListener.FolderClick(position, imageModels.get(position)));
    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }

    public interface FolderListener {
        void FolderClick(int position, FolderImagesModel folderImagesModel);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView IvPlaceholder;
        private final ImageView IvAlbumPhoto;
        private final ConstraintLayout ConsSelect;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ConsSelect = itemView.findViewById(R.id.ConsSelect);
            IvPlaceholder = itemView.findViewById(R.id.IvPlaceholder);
            IvAlbumPhoto = itemView.findViewById(R.id.IvAlbumPhoto);
        }
    }
}
