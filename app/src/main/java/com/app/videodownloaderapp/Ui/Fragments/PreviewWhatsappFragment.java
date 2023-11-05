package com.app.videodownloaderapp.Ui.Fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.videodownloaderapp.Const.Constants;
import com.app.videodownloaderapp.R;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.view.CropImageView;

import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

public class PreviewWhatsappFragment extends Fragment {
    public CircleImageView IvWhatsappPreview;
    public CircleImageView IvWhatsappBorder;

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = layoutInflater.inflate(R.layout.fragment_preview_whatsapp, viewGroup, false);
        InitActions(view);
        return view;
    }

    private void InitActions(View view) {
        IvWhatsappBorder = (CircleImageView) view.findViewById(R.id.IvWhatsappBorder);
        IvWhatsappPreview = (CircleImageView) view.findViewById(R.id.IvWhatsappPreview);

        String perview = getArguments().getString(Constants.PERVIEW);
        String border = getArguments().getString(Constants.SELECTED_BORDER);
        Float fliphori = Float.valueOf(getArguments().getFloat(Constants.FLIP_HORIZONTAL, CropImageView.DEFAULT_ASPECT_RATIO));
        Float flipver = Float.valueOf(getArguments().getFloat(Constants.FLIP_VERTICAL, CropImageView.DEFAULT_ASPECT_RATIO));
        Float rotate = Float.valueOf(getArguments().getFloat(Constants.ROTATE, CropImageView.DEFAULT_ASPECT_RATIO));

        IvWhatsappPreview.setImageBitmap(BitmapFactory.decodeFile(perview));
        Picasso.get().load("https://api.appcodiz.com/DPMaker/" + border).into(IvWhatsappBorder);

        IvWhatsappPreview.setRotationX(flipver.floatValue());
        IvWhatsappPreview.setRotationY(fliphori.floatValue());
        IvWhatsappBorder.setRotation(rotate.floatValue());

    }
}
