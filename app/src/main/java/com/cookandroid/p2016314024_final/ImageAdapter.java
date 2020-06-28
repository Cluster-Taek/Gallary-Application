package com.cookandroid.p2016314024_final;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>
        implements OnImageItemClickListner {
    ArrayList<Image> items = new ArrayList<Image>();
    OnImageItemClickListner listener;
    int layoutType = 0;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(itemView, this, layoutType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Image item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Image item) {
        items.add(item);
    }

    public void setItems(ArrayList<Image> items) {
        this.items = items;
    }

    public Image getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnImageItemClickListner listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public void switchLayout(int position) {
        layoutType = position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv2;
        LinearLayout layout1;

        public ViewHolder(View itemView, final OnImageItemClickListner listener, int layoutType) {
            super(itemView);

            layout1 = itemView.findViewById(R.id.layout1);
            iv2 = itemView.findViewById(R.id.imageView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Image item) {

            String picturePath = item.getPicture();
            if (picturePath != null && !picturePath.equals("")) {
                iv2.setVisibility(View.VISIBLE);
                iv2.setImageURI(Uri.parse("file://" + picturePath));

            } else {
                iv2.setVisibility(View.GONE);
                iv2.setImageResource(R.drawable.noimagefound);

            }
        }

    }
}
