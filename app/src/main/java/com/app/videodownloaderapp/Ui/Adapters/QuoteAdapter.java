package com.app.videodownloaderapp.Ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.videodownloaderapp.Models.CategoryDb;
import com.app.videodownloaderapp.Helper.QuoteHelper;
import com.app.videodownloaderapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<CategoryDb> categoryDbs;
    private final getQuote quote;

    public QuoteAdapter(Context context, ArrayList<CategoryDb> categoryDbs, getQuote quote) {
        this.context = context;
        this.categoryDbs = categoryDbs;
        this.quote = quote;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_quote, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteAdapter.MyViewHolder holder, int position) {
        holder.TvCategoryTitle.setText(categoryDbs.get(position).getName());
        Glide.with(context).load(Integer.valueOf(context.getResources().getIdentifier("category" + categoryDbs.get(position).getId(), "drawable", context.getPackageName()))).into(holder.IvCategoryImage);
        holder.TvQuoteLength.setText(String.valueOf(new QuoteHelper(context).getQuoteId(categoryDbs.get(position).getId()).size()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quote.SetClickQuote(position, categoryDbs.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryDbs.size();
    }

    public interface getQuote {
        void SetClickQuote(int position, CategoryDb statusType);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView IvCategoryImage;
        private final TextView TvQuoteLength;
        private final TextView TvCategoryTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            IvCategoryImage = itemView.findViewById(R.id.IvCategoryImage);
            TvQuoteLength = itemView.findViewById(R.id.TvQuoteLength);
            TvCategoryTitle = itemView.findViewById(R.id.TvCategoryTitle);
        }
    }
}
