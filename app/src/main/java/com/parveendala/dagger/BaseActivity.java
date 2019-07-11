package com.parveendala.dagger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.parveendala.dagger.models.User;
import com.parveendala.dagger.ui.login.LoginActivity;
import com.parveendala.dagger.ui.login.LoginResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }

    private void subscribeObservers() {
        sessionManager.getLoginUser().observe(this, new Observer<LoginResource<User>>() {
                    @Override
                    public void onChanged(LoginResource<User> resource) {
                        if (resource != null) {
                            switch (resource.status) {
                                case LOADING:
                                    break;
                                case AUTHENTICATED:
                                    Log.d(TAG, "onChanged: LOGIN SUCCESS - " + resource.data.getEmail());
                                    break;
                                case NOT_AUTHENTICATED:
                                    Log.d(TAG, "onChanged: LOGOUT");
                                    navLoginScreen();
                                    break;
                                case ERROR:
                                    Log.d(TAG, "onChanged: ERROR - " + resource.message);
                                    break;
                            }
                        }
                    }
                }
        );
    }

    private void navLoginScreen() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
