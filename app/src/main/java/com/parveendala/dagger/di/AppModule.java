package com.parveendala.dagger.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.parveendala.dagger.R;
import com.parveendala.dagger.SessionManager;
import com.parveendala.dagger.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    static SessionManager sessionManager() {
        return new SessionManager();
    }

    @Singleton
    @Provides
    static Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    static GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(Application application, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory).build();
    }

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
