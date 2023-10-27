package com.app.videodownloaderapp.Ui.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.app.videodownloaderapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class InstagramAllFragment extends Fragment {
    private Context context;
    private View view;
    private Switch SwitchPrivateAcc;
    private ProgressBar PbAllStory;
    private LinearLayout llLoginView;
    private RelativeLayout RlLogin;
    private ImageView TvNotFound;
    private RecyclerView RvAllStory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_instagram_all_story, container, false);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
        return view;
    }

    private void VideoInitViews() {
        context = getContext();
        SwitchPrivateAcc= (Switch) view.findViewById(R.id.SwitchPrivateAcc);
        PbAllStory= (ProgressBar) view.findViewById(R.id.PbAllStory);
        llLoginView= (LinearLayout) view.findViewById(R.id.llLoginView);
        RlLogin=(RelativeLayout)view.findViewById(R.id.RlLogin);
        TvNotFound= (ImageView) view.findViewById(R.id.TvNotFound);
        RvAllStory= (RecyclerView) view.findViewById(R.id.RvAllStory);
    }

    private void VideoInitListerns() {
    }

    private void VideoInitActions() {
    }
}
