package com.app.videodownloaderapp.Ui.Adapters;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Helper.QuoteHelper;
import com.app.videodownloaderapp.Models.QuoteModel;
import com.app.videodownloaderapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuoteDataAdapter extends RecyclerView.Adapter<QuoteDataAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<QuoteModel> categoryDbs;

    public QuoteDataAdapter(Context context, ArrayList<QuoteModel> categoryDbs) {
        this.context = context;
        this.categoryDbs = categoryDbs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_quote_data, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteDataAdapter.MyViewHolder holder, int position) {
        System.out.println("------- like : "+categoryDbs.get(position).getLikeQuote());
        System.out.println("------- quote : "+categoryDbs.get(position).getQuote());
        holder.TvQuoteDesc.setText(categoryDbs.get(position).getQuote());
        if (categoryDbs.get(position).getLikeQuote() == 1) {
            holder.IvFavourite.setImageResource(R.drawable.ic_favourite);
        } else {
            holder.IvFavourite.setImageResource(R.drawable.ic_unfavourite);
        }
        holder.IvWhatsappQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote = categoryDbs.get(position).getQuote();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setPackage(Constants.WHATSAPP_PKG);
                intent.putExtra(Intent.EXTRA_TEXT, quote);
                try {
                    context.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(context, context.getResources().getString(R.string.toast_whatsapp_not_installed), Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.IvShareQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quote = categoryDbs.get(position).getQuote();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.setPackage(Constants.WHATSAPP_PKG);
                intent.putExtra(Intent.EXTRA_TEXT, quote);
                context.startActivity(Intent.createChooser(intent, null));
            }
        });
        holder.IvCopyQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("key", categoryDbs.get(position).getQuote()));
                Toast.makeText(context, context.getResources().getString(R.string.text_copied_successfully), Toast.LENGTH_SHORT).show();
            }
        });
        holder.IvFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryDbs.get(position).getLikeQuote() == 1) {
                    categoryDbs.get(position).setLikeQuote(0);
                    new QuoteHelper(context).UpdateQuoteFav(categoryDbs.get(position));
                    notifyItemChanged(position);
                    return;
                }
                categoryDbs.get(position).setLikeQuote(1);
                new QuoteHelper(context).UpdateQuoteFav(categoryDbs.get(position));
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryDbs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView IvShareQuote, IvWhatsappQuote, IvCopyQuote, IvFavourite;
        private final TextView TvQuoteDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            IvShareQuote = itemView.findViewById(R.id.IvShareQuote);
            IvWhatsappQuote = itemView.findViewById(R.id.IvWhatsappQuote);
            IvCopyQuote = itemView.findViewById(R.id.IvCopyQuote);
            IvFavourite = itemView.findViewById(R.id.IvFavourite);
            TvQuoteDesc = itemView.findViewById(R.id.TvQuoteDesc);
        }
    }
}
