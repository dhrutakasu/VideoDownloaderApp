package com.app.videodownloaderapp.Ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.videodownloaderapp.Models.VideoDownloaderDownloadModel;
import com.app.videodownloaderapp.R;

import java.util.ArrayList;

public class VideoDownloaderDownloadAdapter extends RecyclerView.Adapter<VideoDownloaderDownloadAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<VideoDownloaderDownloadModel> downloadModels;

    public VideoDownloaderDownloadAdapter(Context context, ArrayList<VideoDownloaderDownloadModel> downloadModels) {
        this.context = context;
        this.downloadModels = downloadModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.downloading_dialog, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VideoDownloaderDownloadModel videoDownloaderDownloadModel = this.downloadModels.get(position);
        TextView textView = holder.stat;
        textView.setText(this.context.getResources().getString(R.string.downloaded) + videoDownloaderDownloadModel.getFile_size());
        holder.pending.setText(videoDownloaderDownloadModel.getStatus());
        holder.progress.setProgress(Integer.parseInt(videoDownloaderDownloadModel.getProgress()));
        if (videoDownloaderDownloadModel.getStatus().equalsIgnoreCase("resume")) {
            holder.pending.setText(this.context.getResources().getString(R.string.running));
        }
    }

    @Override
    public int getItemCount() {
        return downloadModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView pending;
        public ProgressBar progress;
        public TextView stat;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stat = (TextView) itemView.findViewById(R.id.stat);
            pending = (TextView) itemView.findViewById(R.id.pending);
            progress = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
}
