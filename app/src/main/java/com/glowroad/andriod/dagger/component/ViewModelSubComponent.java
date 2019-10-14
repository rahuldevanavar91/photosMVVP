package com.glowroad.andriod.dagger.component;

import com.glowroad.andriod.viewModel.PhotosViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    PhotosViewModel photoListViewModel();
}