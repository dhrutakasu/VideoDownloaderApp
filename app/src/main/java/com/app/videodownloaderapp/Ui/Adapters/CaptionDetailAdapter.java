package com.app.videodownloaderapp.Ui.Adapters;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Models.Status;
import com.app.videodownloaderapp.Models.StatusType;
import com.app.videodownloaderapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CaptionDetailAdapter extends RecyclerView.Adapter<CaptionDetailAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<Status> statuses;

    public CaptionDetailAdapter(Context context, ArrayList<Status> statuses) {
        this.context = context;
        this.statuses = statuses;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_caption_detail, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CaptionDetailAdapter.MyViewHolder holder, int position) {
        holder.TvCaptionDetailTitle.setText(statuses.get(position).getString());
        holder.IvCopy.setOnClickListener(view -> {
            ((ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText(statuses.get(position).getString(), statuses.get(position).getString()));
            Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show();
        });

        holder.IvShare.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, statuses.get(position).getString());
            intent.setType("text/plain");
            context.startActivity(Intent.createChooser(intent, null));
        });

        holder.IvWhatsapp.setOnClickListener(view -> {
            String status = statuses.get(position).getString();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.setPackage(Constants.WHATSAPP_PKG);
            intent.putExtra(Intent.EXTRA_TEXT, status);
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(context, R.string.toast_whatsapp_not_installed, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView IvWhatsapp, IvCopy, IvShare;
        private final TextView TvCaptionDetailTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            IvWhatsapp = itemView.findViewById(R.id.IvWhatsapp);
            IvCopy = itemView.findViewById(R.id.IvCopy);
            IvShare = itemView.findViewById(R.id.IvShare);
            TvCaptionDetailTitle = itemView.findViewById(R.id.TvCaptionDetailTitle);
        }
    }
}
