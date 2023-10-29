package com.app.videodownloaderapp.Ui.Adapters;

import android.content.Context;

import com.app.videodownloaderapp.Models.DpMakerModelItem;
import com.app.videodownloaderapp.Ui.Fragments.DpFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class DPMakerPagerAdapter extends FragmentPagerAdapter {
    private final Context context;
    private final ArrayList arry;

    public DPMakerPagerAdapter(FragmentManager supportFragmentManager, Context context, ArrayList<DpMakerModelItem> arrayList) {
        super(supportFragmentManager);
        this.context = context;
        this.arry = arrayList;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return new DpFragment().newInstance(arry,position);
    }

    @Override
    public int getCount() {
        return arry.size();
    }
}
