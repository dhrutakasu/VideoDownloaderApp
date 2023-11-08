package com.app.videodownloaderapp.Ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.app.videodownloaderapp.Models.StatusModel;
import com.app.videodownloaderapp.R;
import com.bumptech.glide.Glide;

import java.net.URLConnection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WhatsappRecentAdapter extends RecyclerView.Adapter<WhatsappRecentAdapter.MyViewHolder> {
    public List<StatusModel> arrayList;
    public Context context;
    public OnItemClickListener listener;
    public OnCheckboxListener onCheckboxListener;
    public int width;

    public interface OnCheckboxListener {
        void onCheckboxListener(List list);
    }

    public interface OnItemClickListener {
        void onClick(StatusModel StatusModel, int i);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkbox;
        public ImageView gridImage;
        public ImageView play;

        public MyViewHolder(View view) {
            super(view);
            this.gridImage = (ImageView) view.findViewById(R.id.gridImage);
            this.play = (ImageView) view.findViewById(R.id.play);
            this.checkbox = (CheckBox) view.findViewById(R.id.checkbox);
        }
    }

    public WhatsappRecentAdapter(Context context2, List<StatusModel> list, OnCheckboxListener onCheckboxListener2, OnItemClickListener onItemClickListener) {
        this.context = context2;
        this.arrayList = list;
        this.listener = onItemClickListener;
        LayoutInflater layoutInflater = (LayoutInflater) context2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.width = context2.getResources().getDisplayMetrics().widthPixels;
        this.onCheckboxListener = onCheckboxListener2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_recent, parent, false));
    }


    public final int getItemCount() {
        return this.arrayList.size();
    }

    public final void onBindViewHolder(MyViewHolder holder, int i) {
        StatusModel StatusModel = arrayList.get(i);
        String guessContentTypeFromName = URLConnection.guessContentTypeFromName(arrayList.get(i).getFilePath());
        if (guessContentTypeFromName != null && guessContentTypeFromName.startsWith("video")) {
            holder.play.setVisibility(View.VISIBLE);
        } else {
            holder.play.setVisibility(View.GONE);
        }
        holder.itemView.getLayoutParams().height = width / 3;
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.width = width / 3;
        Glide.with(context).load(arrayList.get(i).getFilePath()).into(holder.gridImage);
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                arrayList.get(i).setSelected(z);
                if (onCheckboxListener != null) {
                    onCheckboxListener.onCheckboxListener(arrayList);
                }
            }
        });
        if (arrayList.get(i).isSelected()) {
            holder.checkbox.setChecked(true);
        } else {
            holder.checkbox.setChecked(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            public final void onClick(View view) {
                listener.onClick(StatusModel, i);
            }
        });
    }
}
