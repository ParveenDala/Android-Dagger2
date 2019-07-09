package com.parveendala.dagger.di;

import android.app.Application;

import com.parveendala.dagger.MyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Parveen Dala
 * Android-Dagger2
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class
        , ActivityBuildersModule.class, AppModule.class})
interface AppComponent extends AndroidInjector<MyApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
