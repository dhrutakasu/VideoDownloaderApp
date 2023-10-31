package com.app.videodownloaderapp.Ui.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.videodownloaderapp.Models.InstaDataProvider;
import com.app.videodownloaderapp.R;

import java.util.ArrayList;
import java.util.List;

public class TwitterDownloadFragment extends Fragment {
    private Context context;
    private View view;
    private RecyclerView RvDownloadTwitterData;
    private ImageView TvNoDataFound;
    private List<InstaDataProvider> instaDownloadedList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_twitter_download, container, false);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
        return view;
    }

    private void VideoInitViews() {
        context = getContext();
        RvDownloadTwitterData = (RecyclerView) view.findViewById(R.id.RvDownloadTwitterData);
        TvNoDataFound = (ImageView) view.findViewById(R.id.TvNoDataFound);
    }

    private void VideoInitListerns() {
    }

    private void VideoInitActions() {
    }
    @Override
    public void onResume() {
        super.onResume();
        if (instaDownloadedList.size()<0){
            TvNoDataFound.setVisibility(View.VISIBLE);
            RvDownloadTwitterData.setVisibility(View.GONE);
            return;
        }
        TvNoDataFound.setVisibility(View.GONE);
        RvDownloadTwitterData.setVisibility(View.VISIBLE);
    }
}
