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

    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        Log.d(TAG, "provideMainApi: ");
        return retrofit.create(MainApi.class);
    }

    @Provides
    static PostRecyclerAdapter postRecyclerAdapter() {
        Log.d(TAG, "postRecyclerAdapter: ");
        return new PostRecyclerAdapter();
    }

}
