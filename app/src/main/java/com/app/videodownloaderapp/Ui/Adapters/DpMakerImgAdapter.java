package com.app.videodownloaderapp.Ui.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.videodownloaderapp.Models.DpMakerModelItem;
import com.app.videodownloaderapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class DpMakerImgAdapter extends RecyclerView.Adapter<DpMakerImgAdapter.MyViewHolder> {
    private final Context context;
    private final List<String> imageList;
    private final DpMakerModelItem dpList;
    private final DpMakerListen dpMakerListen;

    public DpMakerImgAdapter(Context context, DpMakerModelItem dpList, List<String> images, DpMakerListen dpMakerListen) {
        this.context = context;
        this.dpList = dpList;
        this.imageList = images;
        this.dpMakerListen = dpMakerListen;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.layout_dp_maker_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StringBuilder m = new StringBuilder();
        m.append("https://api.appcodiz.com/DPMaker/");
        m.append(dpList.getPath());
        m.append("/");
        m.append(imageList.get(position));
        System.out.println("------- - -- srr mm : " + m.toString());

        try {
            Glide.with(context).load("https://api.appcodiz.com/DPMaker/storage/Animal%20Print/1.webp")
                    .placeholder(R.drawable.ic_image_placeholder)
                    .error(R.drawable.ic_image_placeholder)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            System.out.println("------- - -- srr eroor : " + e.getMessage());
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.IvFramePlaceHolder.setVisibility(View.GONE);
                            return false;
                        }
                    }).load(holder.IvFrame);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------- - -- srr err : " + e.getMessage());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dpMakerListen.DpList(position, dpList, imageList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public interface DpMakerListen {
        void DpList(int pos, DpMakerModelItem dpList, List<String> images);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView IvFramePlaceHolder, IvFrame;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            IvFramePlaceHolder = (ImageView) itemView.findViewById(R.id.IvFramePlaceHolder);
            IvFrame = (ImageView) itemView.findViewById(R.id.IvFrame);
        }
    }
}
