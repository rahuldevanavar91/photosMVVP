package com.glowroad.andriod.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.glowroad.andriod.R;
import com.glowroad.andriod.View.adapter.PhotoRecyclerAdapter;
import com.glowroad.andriod.dagger.Injectable;
import com.glowroad.andriod.model.PhotosResponse;
import com.glowroad.andriod.network.Resource;
import com.glowroad.andriod.viewModel.PhotosViewModel;

import javax.inject.Inject;

public class PhotosListFragment extends Fragment implements Injectable {

    private PhotoRecyclerAdapter mPhotoRecyclerAdapter;
    private RecyclerView mPhotosRecycler;
    private SwipeRefreshLayout mSwipeRefresh;
    private ProgressBar mProgressBar;
    private int mPageNumber = 1;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private PhotosViewModel mViewModel;

    public PhotosListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phots_list, container, false);
        intiWidgets(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(PhotosViewModel.class);
        mViewModel.getMutableLiveData().observe(this, this::onChanged);
        loadData();

    }


    private void intiWidgets(View view) {
        mPhotosRecycler = view.findViewById(R.id.photos_recycler);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setOnRefreshListener(this::loadData);
    }

    private void loadData() {
        mPageNumber = 1;
        requestForPhotoList();
    }

    private void requestForPhotoList() {
        mViewModel.fetchPhotos(mPageNumber);
    }

    private void loadMore() {
        mPageNumber++;
        requestForPhotoList();
    }

    private void onChanged(Resource<PhotosResponse> photosResponse) {
        switch (photosResponse.status) {
            case LOADING:
                mProgressBar.setVisibility(View.VISIBLE);
                break;
            case SUCCESS:
                mProgressBar.setVisibility(View.GONE);
                mSwipeRefresh.setRefreshing(false);
                if (mPhotoRecyclerAdapter == null) {
                    mPhotoRecyclerAdapter = new PhotoRecyclerAdapter(photosResponse.data.getPhotos().getPhoto(), this::loadMore);
                    mPhotosRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    mPhotosRecycler.setAdapter(mPhotoRecyclerAdapter);
                } else {
                    mPhotoRecyclerAdapter.updateData(photosResponse.data.getPhotos().getPhoto());
                }
                break;
            case NETWORK_ERROR:
                Toast.makeText(getContext(), photosResponse.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
                break;
        }
    }

}