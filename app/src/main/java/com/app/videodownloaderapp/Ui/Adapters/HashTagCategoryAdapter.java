package com.app.videodownloaderapp.Ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.videodownloaderapp.Models.Hashtag;
import com.app.videodownloaderapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HashTagCategoryAdapter extends RecyclerView.Adapter<HashTagCategoryAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Hashtag> HashTagModel;
    private final getHashTagModel tagModel;

    public HashTagCategoryAdapter(Context context, ArrayList<Hashtag> HashTagModel, getHashTagModel tagModel) {
        this.context = context;
        this.HashTagModel = HashTagModel;
        this.tagModel = tagModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_hashtag_category, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HashTagCategoryAdapter.MyViewHolder holder, int position) {
        holder.TvCategoryTagName.setText(HashTagModel.get(position).getTagName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagModel.SetClickHashTag(position, HashTagModel.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return HashTagModel.size();
    }

    public interface getHashTagModel {
        void SetClickHashTag(int position, Hashtag hashTagModel);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView TvCategoryTagName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TvCategoryTagName = itemView.findViewById(R.id.TvCategoryTagName);
        }
    }
}
