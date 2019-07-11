package com.parveendala.dagger.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.parveendala.dagger.SessionManager;
import com.parveendala.dagger.models.User;
import com.parveendala.dagger.ui.login.LoginResource;

import javax.inject.Inject;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    private final SessionManager sessionManager;


    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public LiveData<LoginResource<User>> observeLoginUser() {
        return sessionManager.getLoginUser();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
    }
}
