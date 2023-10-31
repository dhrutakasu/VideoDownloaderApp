package com.app.videodownloaderapp.Ui.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.videodownloaderapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class FacebookDownloadFragment extends androidx.fragment.app.Fragment {
    private Context context;
    private View view;
    private RecyclerView RvDownloadData;
    private ImageView TvNoDataFound;
    private List<String> instaDownloadedList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_facebook_download, container, false);
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

    @Override
    public void onResume() {
        super.onResume();
        if (instaDownloadedList.size()<0){
            TvNoDataFound.setVisibility(View.VISIBLE);
            RvDownloadData.setVisibility(View.GONE);
            return;
        }
        TvNoDataFound.setVisibility(View.GONE);
        RvDownloadData.setVisibility(View.VISIBLE);
    }
}
