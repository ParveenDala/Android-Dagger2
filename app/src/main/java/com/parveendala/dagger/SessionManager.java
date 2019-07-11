package com.parveendala.dagger;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.parveendala.dagger.models.User;
import com.parveendala.dagger.ui.login.LoginResource;

import javax.inject.Singleton;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Singleton
public class SessionManager {
    private static final String TAG = "SessionManager";
    private MediatorLiveData<LoginResource<User>> cachedUser = new MediatorLiveData<>();

    public void authenticateUserById(final LiveData<LoginResource<User>> source) {
        if (cachedUser != null) {
            cachedUser.setValue(LoginResource.loading((User) null));
            cachedUser.addSource(source, new Observer<LoginResource<User>>() {
                @Override
                public void onChanged(LoginResource<User> resource) {
                    cachedUser.setValue(resource);
                    cachedUser.removeSource(source);
                }
            });
        }
    }

    public void logout() {
        Log.d(TAG, "logout: ");
        cachedUser.setValue(LoginResource.<User>logout());
    }

    public LiveData<LoginResource<User>> getLoginUser() {
        return cachedUser;
    }
}
