package com.glowroad.andriod.model;


import com.google.gson.annotations.SerializedName;

public class PhotosResponse {


    private Photos photos;

    @SerializedName("stat")
    private String status;

    private String message;

    public Photos getPhotos() {
        return photos;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
