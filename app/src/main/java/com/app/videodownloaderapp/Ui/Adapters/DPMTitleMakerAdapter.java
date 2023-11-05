package com.app.videodownloaderapp.Ui.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.videodownloaderapp.Models.DpMakerModelItem;
import com.app.videodownloaderapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DPMTitleMakerAdapter extends RecyclerView.Adapter<DPMTitleMakerAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<DpMakerModelItem> statusTypes;
    private final getStatusModel tagModel;
    public int selectedPosition = 0;

    public DPMTitleMakerAdapter(Context context, ArrayList<DpMakerModelItem> statusTypes, getStatusModel statusModel) {
        this.context = context;
        this.statusTypes = statusTypes;
        this.tagModel = statusModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_dp_maker_title, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.TvDPTitleName.setText(statusTypes.get(position).getName());
        if (selectedPosition == position) {
            holder.TvDPTitleName.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.gradient_selected));
            holder.TvDPTitleName.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.TvDPTitleName.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.gradient_notselected));
            holder.TvDPTitleName.setTextColor(Color.parseColor("#43000000"));
        }
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
        void SetClickHashTag(int position, DpMakerModelItem statusType);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView TvDPTitleName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            TvDPTitleName = itemView.findViewById(R.id.TvDPTitleName);
        }
    }
}
