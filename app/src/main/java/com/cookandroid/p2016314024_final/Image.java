package com.cookandroid.p2016314024_final;

public class Image {
    int imageId;
    String picture;
    String detail;

    public Image(int imageId, String picture, String detail) {
        this.imageId = imageId;
        this.picture = picture;
        this.detail = detail;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
