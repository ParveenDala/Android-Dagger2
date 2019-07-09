package com.parveendala.dagger;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Parveen Dala
 * Android-Dagger2
 */
public class LoginActivity extends DaggerAppCompatActivity {

    @Inject
    Drawable appLogo;

    @Inject
    RequestManager glideManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setLogo();
    }

    private void setLogo() {
        glideManager.load(appLogo).into((ImageView) findViewById(R.id.app_logo));
    }


}
