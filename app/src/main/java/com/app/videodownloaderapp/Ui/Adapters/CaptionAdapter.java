package com.app.videodownloaderapp.Ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.Models.Category;
import com.app.videodownloaderapp.Models.StatusType;
import com.app.videodownloaderapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CaptionAdapter extends RecyclerView.Adapter<CaptionAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<StatusType> statusTypes;
    private final getStatusModel tagModel;

    public CaptionAdapter(Context context, ArrayList<StatusType> statusTypes, getStatusModel statusModel) {
        this.context = context;
        this.statusTypes = statusTypes;
        this.tagModel = statusModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_caption_msg, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.TvCaptionNumber.setText(statusTypes.get(position).getNumber());
        holder.TvCaptionTitle.setText(statusTypes.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagModel.SetClickHashTag(position, statusTypes.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return statusTypes.size();
    }

    public interface getStatusModel {
        void SetClickHashTag(int position, StatusType statusType);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView TvCaptionTitle;
        private final TextView TvCaptionNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TvCaptionTitle = itemView.findViewById(R.id.TvCaptionTitle);
            TvCaptionNumber = itemView.findViewById(R.id.TvCaptionNumber);
        }
    }
}
