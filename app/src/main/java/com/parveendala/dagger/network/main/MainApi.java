package com.parveendala.dagger.network.main;

import com.parveendala.dagger.models.Post;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public interface MainApi {

    @GET("/posts")
    Call<List<Post>> getPosts(@Query("userId") int userid);

    @GET("/posts")
    Flowable<List<Post>> getPostsRx(@Query("userId") int userid);

}
