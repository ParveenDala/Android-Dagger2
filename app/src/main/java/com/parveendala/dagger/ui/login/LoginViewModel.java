package com.parveendala.dagger.ui.login;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public class LoginViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";

    @Inject
    public LoginViewModel() {
        Log.d(TAG, "LoginViewModel: LoginViewModel Created");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
    }
}