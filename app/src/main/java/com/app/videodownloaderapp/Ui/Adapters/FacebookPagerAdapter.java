package com.app.videodownloaderapp.Ui.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FacebookPagerAdapter extends FragmentPagerAdapter {
    private final Context con;
    private final ArrayList<Fragment> fragments;

    public FacebookPagerAdapter(FragmentManager manager, Context context, ArrayList<Fragment> fragments) {
        super(manager);
        this.con = context;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

}
