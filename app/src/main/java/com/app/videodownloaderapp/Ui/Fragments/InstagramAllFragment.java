package com.app.videodownloaderapp.Ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.app.videodownloaderapp.Models.InstaDataProvider;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Activity.InstagramLoginActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class InstagramAllFragment extends Fragment {
    private Context context;
    private View view;
    private Switch SwitchPrivateAcc;
    private ProgressBar PbAllStory;
    private LinearLayout llLoginView;
    private ConstraintLayout RlLogin;
    private ImageView TvNotFound;
    private RecyclerView RvAllStory;
    private List<InstaDataProvider> instaDownloadedList = new ArrayList<>();

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
        SwitchPrivateAcc = (Switch) view.findViewById(R.id.SwitchPrivateAcc);
        PbAllStory = (ProgressBar) view.findViewById(R.id.PbAllStory);
        llLoginView = (LinearLayout) view.findViewById(R.id.llLoginView);
        RlLogin = (ConstraintLayout) view.findViewById(R.id.RlLogin);
        TvNotFound = (ImageView) view.findViewById(R.id.TvNotFound);
        RvAllStory = (RecyclerView) view.findViewById(R.id.RvAllStory);
    }

    private void VideoInitListerns() {
    }

    private void VideoInitActions() {
        SwitchPrivateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SwitchPrivateAcc.isChecked()) {
                    SwitchPrivateAcc.setChecked(false);
                } else {
                    startActivity(new Intent(getActivity(), InstagramLoginActivity.class));
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        llLoginView.setVisibility(View.VISIBLE);
        if (instaDownloadedList.size() > 0) {
            TvNotFound.setVisibility(View.VISIBLE);
            RvAllStory.setVisibility(View.GONE);
            return;
        }
        TvNotFound.setVisibility(View.GONE);
        RvAllStory.setVisibility(View.VISIBLE);
    }
}
