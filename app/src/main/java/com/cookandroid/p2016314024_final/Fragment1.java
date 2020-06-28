package com.cookandroid.p2016314024_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class Fragment1 extends Fragment {

    ViewGroup viewGroup;
    Button button;
    ImageView iv = null;
    final static int TAKE_PICTURE = 1;
    MyDBHelper myDBHelper;
    SQLiteDatabase db;
    String fileName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_fragment1,container,false);


        button = (Button)viewGroup.findViewById(R.id.button);
        iv = (ImageView)viewGroup.findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch(v.getId()){
                    case R.id.button:
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, TAKE_PICTURE);
                        break;
                }

            }
        });
        return viewGroup;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK && intent.hasExtra("data")) {
                    Bitmap bitmap = (Bitmap) intent.getExtras().get("data");
                    if (bitmap != null) {
                        fileName = String.valueOf(System.currentTimeMillis())+".png";
                        File file = new File(Environment
                                .getExternalStorageDirectory().getAbsolutePath()+"/Pictures/"+fileName);
                        FileOutputStream out = null;
                        try {
                            file.createNewFile();
                            out = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                            out.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        iv.setImageBitmap(bitmap);
                    }

                }
                /*
                db = myDBHelper.getWritableDatabase();
                ContentValues row = new ContentValues();
                row.put("picture", fileName);
                row.put("detail","");
                db.insert("imagelist", null, row);
                db.close();*/
                break;
        }
    }

    private class MyDBHelper extends SQLiteOpenHelper {
        public MyDBHelper(Context context) {
            super(context, "IMAGE", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE imagelist ( imageid INTEGER PRIMARY KEY autoincrement, picture text, detail text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS imagelist");
            onCreate(db);
        }
    }
}
