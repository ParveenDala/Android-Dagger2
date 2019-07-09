package com.parveendala.dagger.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.parveendala.dagger.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions() {
        return RequestOptions
                .placeholderOf(R.color.colorWhite)
                .error(R.color.colorBlack);
    }

    @Singleton
    @Provides
    static RequestManager provideRequestManager(Application application, RequestOptions requestOptions) {
        return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideAppLogo(Application application) {
        return ContextCompat.getDrawable(application, R.drawable.ic_android_black);
    }

}
