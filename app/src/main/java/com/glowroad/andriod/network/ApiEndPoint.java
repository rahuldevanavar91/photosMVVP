package com.glowroad.andriod.network;

import com.glowroad.andriod.Utils.Constants;
import com.glowroad.andriod.model.PhotosResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("services/rest/?api_key=" + Constants.API_KEY + "&per_page=" + Constants.PER_PAGE_COUNT +
            "&method=flickr.photos.search&format=json&nojsoncallback=1&text=india&extras=url_q")
    Single<PhotosResponse> getPhotosList(@Query("page") int page);
}
