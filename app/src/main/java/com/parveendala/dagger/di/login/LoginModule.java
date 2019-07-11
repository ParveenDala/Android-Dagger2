package com.parveendala.dagger.di.login;

import com.parveendala.dagger.network.login.LoginApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Module
public class LoginModule {

    @Provides
    static LoginApi provideLoginApi(Retrofit retrofit) {
        return retrofit.create(LoginApi.class);
    }

}
