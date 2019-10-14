package com.glowroad.andriod.dagger.component;

import android.app.Application;

import com.glowroad.andriod.GlowRoadApplication;
import com.glowroad.andriod.dagger.module.AppModule;
import com.glowroad.andriod.dagger.module.MainActivityModule;
import com.glowroad.andriod.dagger.module.RxModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class, RxModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(GlowRoadApplication application);
}
