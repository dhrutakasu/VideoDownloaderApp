package com.app.videodownloaderapp.Ui.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.Models.ImageFolderModel;
import com.app.videodownloaderapp.Models.ImageModel;
import com.app.videodownloaderapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoldersAdapter extends RecyclerView.Adapter<FoldersAdapter.MyViewHolder> {
    private final Context context;
    public ArrayList<ImageFolderModel> imageModels;
    private final FolderListener folderListener;
    private Boolean isImage = true;

    public FoldersAdapter(Context context, ArrayList<ImageFolderModel> imageModels, FolderListener folderListener) {
        this.context = context;
        this.imageModels = imageModels;
        this.folderListener = folderListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_folders, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.TvFolderName.setText(imageModels.get(position).getFolderName());
        StringBuilder builder = new StringBuilder();
        builder.append(imageModels.get(position).getNumberOfPics());
        builder.append(isImage.booleanValue() ? " photos" : " videos");
        holder.TvPhoto.setText(builder.toString());
        Picasso.get().load(imageModels.get(position).getUri()).into(holder.IvAlbumPhoto);
        holder.IvPlaceholder.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(view -> folderListener.FolderClick(imageModels.get(position).getPath(), imageModels.get(position).getFolderName(), imageModels.get(position).getBucketId()));

    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }

    public interface FolderListener {
        void FolderClick(String path, String fName, String Bid);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView TvFolderName;
        private final TextView TvPhoto;
        private final ImageView IvPlaceholder;
        private final ImageView IvAlbumPhoto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TvFolderName = itemView.findViewById(R.id.TvFolderName);
            TvPhoto = itemView.findViewById(R.id.TvPhoto);
            IvPlaceholder = itemView.findViewById(R.id.IvPlaceholder);
            IvAlbumPhoto = itemView.findViewById(R.id.IvAlbumPhoto);
        }
    }
}
