package com.parveendala.dagger.ui.login;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.parveendala.dagger.R;
import com.parveendala.dagger.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public class LoginActivity extends DaggerAppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Inject
    Drawable appLogo;

    @Inject
    RequestManager glideManager;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setLogo();
        loginViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(LoginViewModel.class);
    }

    private void setLogo() {
        glideManager.load(appLogo).into((ImageView) findViewById(R.id.app_logo));
    }

    public void onButtonClicked(View view) {
    }
}
