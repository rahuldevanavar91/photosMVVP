package com.glowroad.andriod.dagger.module;


import com.glowroad.andriod.View.PhotosListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract PhotosListFragment contributeProjectListFragment();
}
