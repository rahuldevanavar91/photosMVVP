package com.glowroad.andriod.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.glowroad.andriod.View.adapter.PhotoRecyclerAdapter;
import com.glowroad.andriod.network.RxSingleSchedulers;
import com.glowroad.andriod.model.PhotoItem;
import com.glowroad.andriod.model.Photos;
import com.glowroad.andriod.model.PhotosResponse;
import com.glowroad.andriod.network.ApiEndPoint;
import com.glowroad.andriod.network.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class PhotosViewModel extends ViewModel {

    private final RxSingleSchedulers mRxSingleSchedulers;
    private CompositeDisposable disposable;

    private ApiEndPoint apiEndPoint;
    private MutableLiveData<Resource> mutableLiveData = new MutableLiveData<>();
    private List<PhotoItem> mPhotosList;

    @Inject
    public PhotosViewModel(ApiEndPoint service, RxSingleSchedulers rxSingleSchedulers) {
        this.apiEndPoint = service;
        mRxSingleSchedulers = rxSingleSchedulers;
        disposable = new CompositeDisposable();
        mPhotosList = new ArrayList<>();
    }

    public MutableLiveData<Resource> getMutableLiveData() {
        return mutableLiveData;
    }

    public void fetchPhotos(int pageNumber) {
        disposable.add(apiEndPoint.getPhotosList(pageNumber)
                .doOnEvent((newsList, throwable) -> onLoading())
                .compose(mRxSingleSchedulers.applySchedulers())
                .subscribe(this::onSuccess,
                        this::onError));
    }

    private void onError(Throwable throwable) {
        mutableLiveData.postValue(Resource.networkError());
    }

    private void onSuccess(PhotosResponse response) {
        if (response != null && response.getStatus().equalsIgnoreCase("ok")) {
            prepareList(response.getPhotos());
            mutableLiveData.postValue(Resource.success(response));
        } else {
            checkViewMorePresent(null);
            mutableLiveData.postValue(Resource.error(response.getMessage(), response));
        }
    }

    private void onLoading() {
        mutableLiveData.postValue(Resource.loading());
    }

    private void prepareList(Photos photos) {
        checkViewMorePresent(photos);
        if (photos.getPage() == 1) {
            mPhotosList.clear();
        }
        mPhotosList.addAll(photos.getPhoto());
        photos.setPhoto(mPhotosList);
    }

    private void checkViewMorePresent(Photos photos) {
        if (!mPhotosList.isEmpty() && mPhotosList.get(mPhotosList.size() - 1).getViewType() == PhotoRecyclerAdapter.VIEW_TYPE_MORE_LOADING) {
            mPhotosList.remove(mPhotosList.size() - 1);
        }
        if (hasMoreData(photos)) {
            PhotoItem loadMoreItem = new PhotoItem();
            loadMoreItem.setViewType(PhotoRecyclerAdapter.VIEW_TYPE_MORE_LOADING);
            photos.getPhoto().add(loadMoreItem);
        }
    }

    private boolean hasMoreData(Photos photos) {
        return (photos != null && photos.getPage() != photos.getPages());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
