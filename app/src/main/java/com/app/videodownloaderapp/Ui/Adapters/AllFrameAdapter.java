package com.app.videodownloaderapp.Ui.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.videodownloaderapp.Models.DpMakerModelItem;
import com.app.videodownloaderapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllFrameAdapter extends RecyclerView.Adapter<AllFrameAdapter.MyViewHolder> {
    public Context context;
    public ArrayList<String> stringArrayList;
    public DpMakerModelItem dpMakerModelItem;
    public AllFrameClicklistener listener;

    public AllFrameAdapter(Context context2, ArrayList list, DpMakerModelItem dpMakerModelItem, AllFrameClicklistener allFrameClicklistener) {
        context = context2;
        stringArrayList = list;
        this.dpMakerModelItem = dpMakerModelItem;
        listener = allFrameClicklistener;
    }

    public final MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_all_frame, viewGroup, false));
    }

    public final int getItemCount() {
        return stringArrayList.size();
    }

    public final void onBindViewHolder(MyViewHolder holder, final int i) {
        StringBuilder link = new StringBuilder();
        link.append("https://api.appcodiz.com/DPMaker/");
        link.append(dpMakerModelItem.getPath());
        link.append("/");
        link.append(stringArrayList.get(i));
        Picasso.get().load(link.toString()).placeholder(R.drawable.ic_image_placeholder).error(R.drawable.ic_image_placeholder).into(holder.IvAllFrame);
        holder.itemView.setOnClickListener(view -> listener.AllFrameListner(i, dpMakerModelItem));
    }

    public interface AllFrameClicklistener {
        void AllFrameListner(int i, DpMakerModelItem videoDownloaderCategoryModel);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView IvAllFrame;

        public MyViewHolder(View view) {
            super(view);
            IvAllFrame = (ImageView) view.findViewById(R.id.IvAllFrame);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) view.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels / 4;
            view.getLayoutParams().height = i;
            view.getLayoutParams().width = i;
        }
    }

}
