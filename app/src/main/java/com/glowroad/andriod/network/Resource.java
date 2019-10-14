package com.glowroad.andriod.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.glowroad.andriod.Utils.Status;

import static com.glowroad.andriod.Utils.Status.ERROR;
import static com.glowroad.andriod.Utils.Status.LOADING;
import static com.glowroad.andriod.Utils.Status.NETWORK_ERROR;
import static com.glowroad.andriod.Utils.Status.SUCCESS;

public class Resource<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    private final String message;

    private int statusCode;


    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    private Resource(@NonNull Status status, int statuscode, @Nullable String message) {
        this.status = status;
        this.statusCode = statuscode;
        this.message = message;
        this.data = null;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }


    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> error(String msg, int statuscode) {
        return new Resource<>(ERROR, statuscode, msg);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(LOADING, null, null);
    }

    public static <T> Resource<T> networkError() {
        return new Resource<>(NETWORK_ERROR, null, "Please check network");
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

}