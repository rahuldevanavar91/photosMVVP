package com.glowroad.andriod.dagger.module;

import com.glowroad.andriod.View.MainActivity;
import com.glowroad.andriod.dagger.module.FragmentBuildersModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();
}
