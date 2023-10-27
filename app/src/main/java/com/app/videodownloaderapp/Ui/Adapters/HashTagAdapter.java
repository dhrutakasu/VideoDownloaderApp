package com.app.videodownloaderapp.Ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.videodownloaderapp.Models.Category;
import com.app.videodownloaderapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HashTagAdapter extends RecyclerView.Adapter<HashTagAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Category> HashTagModel;
    private final getHashTagModel tagModel;

    public HashTagAdapter(Context context, ArrayList<Category> HashTagModel, getHashTagModel tagModel) {
        this.context = context;
        this.HashTagModel = HashTagModel;
        this.tagModel = tagModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_hashtag, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HashTagAdapter.MyViewHolder holder, int position) {
        holder.TvHashtagName.setText(HashTagModel.get(position).getName().replace(" / ", " ").replace("/", " "));
        holder.TvHashtagName.setSelected(true);
        Glide.with(context).load(Integer.valueOf(context.getResources().getIdentifier("ic_" +
                        HashTagModel.get(position).getName().toLowerCase().trim().replace(" / ", "/")
                                .replace("/", "_").replace(" ", "_") + "_new", "drawable",
                context.getPackageName()))).into(holder.IvHashTagImg);

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
        void SetClickHashTag(int position, Category hashTagModel);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView IvHashTagImg;
        private final TextView TvHashtagName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            IvHashTagImg = itemView.findViewById(R.id.IvHashTagImg);
            TvHashtagName = itemView.findViewById(R.id.TvHashtagName);
        }
    }
}
