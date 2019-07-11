package com.parveendala.dagger.di.main;

import android.util.Log;

import com.parveendala.dagger.network.main.MainApi;
import com.parveendala.dagger.ui.main.post.PostRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Module
public class MainModule {
    private static final String TAG = "MainModule";

    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @MainScope
    @Provides
    static PostRecyclerAdapter postRecyclerAdapter() {
        return new PostRecyclerAdapter();
    }

}
