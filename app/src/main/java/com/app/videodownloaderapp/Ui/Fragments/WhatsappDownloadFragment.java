package com.app.videodownloaderapp.Ui.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager2.widget.ViewPager2;

import com.app.videodownloaderapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WhatsappDownloadFragment extends androidx.fragment.app.Fragment {
    private Context context;
    private View view;
    private TabLayout TabWhatsapp;
    private ViewPager2 PagerWhatsapp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_whatsapp_download, container, false);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
        return view;
    }

    private void VideoInitViews() {
        context = getContext();
        TabWhatsapp = (TabLayout) view.findViewById(R.id.TabWhatsapp);
        PagerWhatsapp = (ViewPager2) view.findViewById(R.id.PagerWhatsapp);
    }

    private void VideoInitListerns() {
    }

    private void VideoInitActions() {
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
