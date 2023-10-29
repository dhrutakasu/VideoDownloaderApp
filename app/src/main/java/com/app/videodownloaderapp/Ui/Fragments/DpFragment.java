package com.app.videodownloaderapp.Ui.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Models.DpMakerModelItem;
import com.app.videodownloaderapp.R;
import com.app.videodownloaderapp.Ui.Activity.DpMakerEditActivity;
import com.app.videodownloaderapp.Ui.Adapters.DpMakerImgAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DpFragment extends Fragment {
    private View view;
    private RecyclerView RvDPMakerView;
    private DpMakerModelItem DpList;

    public Fragment newInstance(ArrayList<DpMakerModelItem> arry, int position) {
        DpFragment fragment = new DpFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DPMakerPos, position);
        bundle.putString(Constants.DPMakerItem, new Gson().toJson((ArrayList<DpMakerModelItem>) arry));
        bundle.putString(Constants.DPMakerValue, new Gson().toJson((DpMakerModelItem) arry.get(position)));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dp_maker_view, container, false);
        VideoInitViews();
        VideoInitListerns();
        VideoInitActions();
        return view;
    }

    private void VideoInitViews() {
        RvDPMakerView = (RecyclerView) view.findViewById(R.id.RvDPMakerView);
    }

    private void VideoInitListerns() {


    }

    private void VideoInitActions() {
        DpList = (DpMakerModelItem) new Gson().fromJson(getArguments().getString(Constants.DPMakerValue), DpMakerModelItem.class);
        RvDPMakerView.setLayoutManager(new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false));
        System.out.println("------- - -- srr iii : "+DpList.getImages());
        DpMakerImgAdapter dpMakerImgAdapter = new DpMakerImgAdapter(getContext(), DpList, DpList.getImages(), new DpMakerImgAdapter.DpMakerListen() {
            @Override
            public void DpList(int pos, DpMakerModelItem dpList, List<String> images) {
                Intent intent = new Intent(getContext(), DpMakerEditActivity.class);
                intent.putExtra(Constants.DPMakerItem, dpList.getPath() + "/" + ((String) images.get(pos)));
                intent.putExtra(Constants.DPMakerPos, pos);
                intent.putExtra(Constants.DpMakerName, dpList.getName());
                intent.putExtra(Constants.DPMakerValue, new Gson().toJson((List<String>) images));
                intent.putExtra(Constants.DpEdit, true);
                intent.putExtra(Constants.DpMakerItemList, getArguments().getString(Constants.DPMakerItem));
                startActivity(intent);
            }
        });
        RvDPMakerView.setAdapter(dpMakerImgAdapter);
    }
}
