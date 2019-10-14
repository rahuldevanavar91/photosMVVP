package com.glowroad.andriod.dagger.module;


import androidx.lifecycle.ViewModelProvider;

import com.glowroad.andriod.Utils.Constants;
import com.glowroad.andriod.dagger.component.ViewModelSubComponent;
import com.glowroad.andriod.network.ApiEndPoint;
import com.glowroad.andriod.viewModel.ProjectViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {
    @Singleton
    @Provides
    ApiEndPoint providePhotoService() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiEndPoint.class);
    }


    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new ProjectViewModelFactory(viewModelSubComponent.build());
    }


}
