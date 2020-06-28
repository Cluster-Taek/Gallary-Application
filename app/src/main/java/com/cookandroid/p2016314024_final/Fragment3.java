package com.cookandroid.p2016314024_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment3 extends Fragment {

    public static Fragment3 newInstance() {
        return new Fragment3();
    }
    ViewGroup viewGroup;
    ImageView iv3;
    TextView tv1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_fragment3,container,false);
        iv3 = (ImageView)viewGroup.findViewById(R.id.imageView3);
        tv1 = (TextView)viewGroup.findViewById(R.id.textView);

        if(getArguments() != null) {
            String fileName = getArguments().getString("fileName");
            String detail = getArguments().getString("detail");//bundle.getString("detail");
            Bitmap bmp = BitmapFactory.decodeFile(fileName);
            tv1.setText(detail);
            iv3.setImageBitmap(bmp);
        }
        return viewGroup;
    }
}
