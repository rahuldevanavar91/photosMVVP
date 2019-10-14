package com.glowroad.andriod.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {
    private int page;

    private int pages;

    @SerializedName("perpage")
    private int perPage;

    private String total;

    private List<PhotoItem> photo;

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public int getPerPage() {
        return perPage;
    }

    public List<PhotoItem> getPhoto() {
        return photo;
    }

    public String getTotal() {
        return total;
    }

    public void setPhoto(List<PhotoItem> photo) {
        this.photo = photo;
    }
}
