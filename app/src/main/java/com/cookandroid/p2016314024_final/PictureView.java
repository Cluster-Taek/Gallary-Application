package com.cookandroid.p2016314024_final;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PictureView extends LinearLayout {
    ImageView iv2;

    public PictureView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item, this, true);
        iv2 = findViewById(R.id.imageView2);
    }

    public void setPicture(Bitmap bm){
        iv2.setImageBitmap(bm);
    }



}
