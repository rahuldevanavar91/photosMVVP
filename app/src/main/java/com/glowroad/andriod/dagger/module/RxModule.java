package com.glowroad.andriod.dagger.module;

import com.glowroad.andriod.network.RxSingleSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {

    @Provides
    public RxSingleSchedulers providesScheduler() {
        return RxSingleSchedulers.DEFAULT;
    }
}
