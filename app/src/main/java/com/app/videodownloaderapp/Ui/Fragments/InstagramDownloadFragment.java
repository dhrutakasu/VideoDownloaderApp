package com.app.videodownloaderapp.Ui.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.videodownloaderapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class InstagramDownloadFragment extends Fragment {
    private Context context;
    private View view;
    private RecyclerView RvDownloadData;
    private ImageView TvNoDataFound;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_instagram_download, container, false);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
        return view;
    }

    private void VideoInitViews() {
        context = getContext();
        RvDownloadData = (RecyclerView) view.findViewById(R.id.RvDownloadData);
        TvNoDataFound = (ImageView) view.findViewById(R.id.TvNoDataFound);
    }

    private void VideoInitListerns() {
    }

    private void VideoInitActions() {
    }
}
