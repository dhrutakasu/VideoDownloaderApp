package com.app.videodownloaderapp.Ui.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.videodownloaderapp.Models.DpMakerModelItem;
import com.app.videodownloaderapp.R;

import java.util.ArrayList;

public class CategoryNameAdapter extends RecyclerView.Adapter<CategoryNameAdapter.MyViewHolder> {
    public Context context;
    public ArrayList<DpMakerModelItem> dpMakerModelItems;
    public CategoryClickListener listener;
    public int selectedItem;

    public CategoryNameAdapter(Context context, int pos, ArrayList list, CategoryClickListener categoryClickListener) {
        this.context = context;
        this.dpMakerModelItems = list;
        this.selectedItem = pos;
        this.listener = categoryClickListener;
    }

    public final MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.layout_dp_category, viewGroup, false));
    }

    public final int getItemCount() {
        return this.dpMakerModelItems.size();
    }

    public final void onBindViewHolder(MyViewHolder holder, @SuppressLint({"RecyclerView"}) final int i) {
        holder.tvCategoryName.setText(dpMakerModelItems.get(i).getName());
        if (this.selectedItem == i) {
            holder.tvCategoryName.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.gradient_selected));
            holder.tvCategoryName.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tvCategoryName.setText(dpMakerModelItems.get(i).getName());
        } else {
            holder.tvCategoryName.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.gradient_notselected));
            holder.tvCategoryName.setTextColor(Color.parseColor("#43000000"));
        }
        holder.itemView.setOnClickListener(view -> {
            selectedItem = i;
            notifyDataSetChanged();
            listener.seCategoryListner(dpMakerModelItems.get(i));
        });
    }

    public interface CategoryClickListener {
        void seCategoryListner(DpMakerModelItem videoDownloaderCategoryModel);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCategoryName;

        public MyViewHolder(View view) {
            super(view);
            this.tvCategoryName = (TextView) view.findViewById(R.id.Tv_CategoryName);
        }
    }
}
