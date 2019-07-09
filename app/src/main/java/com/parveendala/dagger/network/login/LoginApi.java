package com.parveendala.dagger.network.login;

import com.parveendala.dagger.models.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public interface LoginApi {

    @GET("/users/{id}")
    Flowable<User> getUser(@Path("id") int userId);
}