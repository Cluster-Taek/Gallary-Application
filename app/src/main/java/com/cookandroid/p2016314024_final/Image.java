package com.cookandroid.p2016314024_final;

import android.graphics.Bitmap;

public class Image {
    int imageId;
    Bitmap picture;
    String detail;

    public Image() {

    }

    public Image(Bitmap picture, String detail) {
        this.picture = picture;
        this.detail = detail;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
