package com.app.videodownloaderapp.Ui.Adapters;

import android.os.Bundle;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.Ui.Fragments.PreviewFacebookFragment;
import com.app.videodownloaderapp.Ui.Fragments.PreviewInstagramFragment;
import com.app.videodownloaderapp.Ui.Fragments.PreviewWhatsappFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PreviewAdapter extends FragmentPagerAdapter {
    public Float flipHorizontal;
    public Float flipVertical;
    public String previewBorder;
    public String previewpath;
    public Float rotate;
    public int tabCount;

    public PreviewAdapter(FragmentManager supportFragmentManager, String previewImagePath, String previewImageBorder, Float flipHorizontal, Float flipVertical, Float rotate, int tabCount) {
        super(supportFragmentManager);
        this.tabCount = tabCount;
        this.previewpath = previewImagePath;
        this.previewBorder = previewImageBorder;
        this.flipHorizontal = flipHorizontal;
        this.flipVertical = flipVertical;
        this.rotate = rotate;
    }

    public final int getCount() {
        return tabCount;
    }

    public final Fragment getItem(int i) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PERVIEW, previewpath);
        bundle.putString(Constants.SELECTED_BORDER, previewBorder);
        bundle.putFloat(Constants.FLIP_HORIZONTAL, flipHorizontal.floatValue());
        bundle.putFloat(Constants.FLIP_VERTICAL, flipVertical.floatValue());
        bundle.putFloat(Constants.ROTATE, rotate.floatValue());
        Fragment fragment = null;
        switch (i) {
            case 0: {
                fragment = new PreviewInstagramFragment();
                fragment.setArguments(bundle);
            }
            break;
            case 1: {
                fragment = new PreviewFacebookFragment();
                fragment.setArguments(bundle);
            }
            break;
            case 2: {
                fragment = new PreviewWhatsappFragment();
                fragment.setArguments(bundle);
            }
            break;
        }
        return fragment;
    }
}
