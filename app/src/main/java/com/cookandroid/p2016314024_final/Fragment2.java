package com.cookandroid.p2016314024_final;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;


public class Fragment2 extends Fragment {
    ViewGroup viewGroup;
    GridView gridView;
    ImageAdapter adapter;
    ArrayList<File> imageList = new ArrayList<File>();
    File[] imageFiles;
    int index = 0;
    MyDBHelper myDBHelper;
    SQLiteDatabase db;
    Fragment3 fragment3;

    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_fragment2,container,false);
        gridView = viewGroup.findViewById(R.id.gridView);
        myDBHelper = new MyDBHelper(getActivity());
        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();
        //imageList = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();
        imageList.clear();
        for(int i = 0; i < imageFiles.length; i++) {
            imageList.add(imageFiles[i]);
        }
        //adapter = new ImageAdapter(getActivity(), imageFiles);
        adapter = new ImageAdapter(getActivity(), imageList);
        gridView.setAdapter(adapter);
        return viewGroup;
    }

    private class ImageAdapter extends BaseAdapter {

        ArrayList<File> items;
        Context context = null;

        public ImageAdapter(Context context, ArrayList<File> items) {
            index = 0;
            this.context = context;
            this.items = items;
        }
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ImageView imageView = null;

            if (null != view)
                imageView = (ImageView)view;
            else {
                Bitmap bmp = BitmapFactory.decodeFile(imageList.get(index++).toString());
                imageView = new ImageView(context);
                imageView.setAdjustViewBounds(true);
                imageView.setImageBitmap(bmp);
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db = myDBHelper.getReadableDatabase();
                    Cursor cursor = db.rawQuery("select * from imagelist where imageid ='"+ String.valueOf(i) +"'", null);
                    while(cursor.moveToNext()) {
                        Bundle bundle = new Bundle();
                        bundle.putString("fileName", cursor.getString(1));
                        bundle.putString("detail", cursor.getString(2));
                        fragment3 = new Fragment3();
                        fragment3.setArguments(bundle);
                    }
                    cursor.close();
                    db.close();
                    ((MainActivity)getActivity()).replaceFragment(fragment3);
                }
            });
            return imageView;
        }
    }
}
