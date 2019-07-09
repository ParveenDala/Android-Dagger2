package com.parveendala.dagger.ui.login;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.google.android.material.textfield.TextInputEditText;
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

    private TextInputEditText etUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserId = findViewById(R.id.user_id);
        setLogo();
        loginViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(LoginViewModel.class);
    }

    private void setLogo() {
        glideManager.load(appLogo).into((ImageView) findViewById(R.id.app_logo));
    }

    public void onButtonClicked(View view) {
        if (!TextUtils.isEmpty(etUserId.getText().toString().trim())) {
            loginViewModel.getUser(Integer.parseInt(etUserId.getText().toString().trim()));
        } else {
            Toast.makeText(this, "Please enter user id.", Toast.LENGTH_SHORT).show();
        }
    }
}
