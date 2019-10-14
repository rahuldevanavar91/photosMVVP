package com.glowroad.andriod.model;

import com.google.gson.annotations.SerializedName;

public class PhotoItem {

    private int viewType;

    private String id;

    private String title;

    @SerializedName("url_q")
    private String imageUrl;

    @SerializedName("height_q")
    private int imageHeight;

    @SerializedName("width_q")
    private int imageWidth;

    public String getId() {
        return id;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public String getTitle() {
        return title;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}

