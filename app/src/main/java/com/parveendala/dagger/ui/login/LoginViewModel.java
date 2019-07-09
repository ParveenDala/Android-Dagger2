package com.parveendala.dagger.ui.login;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.parveendala.dagger.models.User;
import com.parveendala.dagger.network.login.LoginApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public class LoginViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";

    private final LoginApi loginApi;

    @Inject
    public LoginViewModel(LoginApi loginApi) {
        this.loginApi = loginApi;
    }

    public void getUser(int userId) {
        Log.d(TAG, "getUser: ");
        loginApi.getUser(userId)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "onNext: " + user);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}