package com.cookandroid.p2016314024_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class Fragment2 extends Fragment {
    ViewGroup viewGroup;
    RecyclerView recyclerView;
    ImageAdapter adapter;
    Context context;
    TabHost.OnTabChangeListener listener;

    Button btn1, btn2;
    SQLiteDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_fragment2,container,false);
        initUI(viewGroup);

        loadImageListData();
        return viewGroup;
    }

    private void initUI(ViewGroup viewGroup) {

        recyclerView = viewGroup.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        adapter = new ImageAdapter();

        adapter.addItem(new Image(0,"123.jpg","첫 디테일"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnImageItemClickListner() {
            @Override
            public void onItemClick(ImageAdapter.ViewHolder holder, View view, int position) {
                Image item = adapter.getItem(position);

            }
        });

    }
    public int loadImageListData() {

        String sql = "select _id, PICTURE, DETAIL";

        int recordCount = -1;
        ImageDatabase database = ImageDatabase.getInstance(context);
        if (database != null) {
            Cursor outCursor = database.rawQuery(sql);

            recordCount = outCursor.getCount();

            ArrayList<Image> items = new ArrayList<Image>();

            for (int i = 0; i < recordCount; i++) {
                outCursor.moveToNext();

                int _id = outCursor.getInt(0);
                String picture = outCursor.getString(1);
                String detail = outCursor.getString(2);

                items.add(new Image(_id, picture, detail));
            }

            outCursor.close();

            adapter.setItems(items);
            adapter.notifyDataSetChanged();

        }

        return recordCount;
    }
}
