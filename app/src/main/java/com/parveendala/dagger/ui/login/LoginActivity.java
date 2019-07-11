package com.parveendala.dagger.ui.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.google.android.material.textfield.TextInputEditText;
import com.parveendala.dagger.R;
import com.parveendala.dagger.models.User;
import com.parveendala.dagger.ui.main.MainActivity;
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
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserId = findViewById(R.id.user_id);
        progressBar = findViewById(R.id.progress_bar);
        setLogo();
        loginViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(LoginViewModel.class);
        observeUser();
    }

    private void setLogo() {
        glideManager.load(appLogo).into((ImageView) findViewById(R.id.app_logo));
    }

    private void observeUser() {
        loginViewModel.observeLoginState().observe(this, new Observer<LoginResource<User>>() {
            @Override
            public void onChanged(LoginResource<User> resource) {
                if (resource != null) {
                    switch (resource.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case AUTHENTICATED:
                            progressBar.setVisibility(View.GONE);
                            Log.d(TAG, "onChanged: LOGIN SUCCESS - " + resource.data.getEmail());
                            onLoginSuccess();
                            break;
                        case NOT_AUTHENTICATED:
                            progressBar.setVisibility(View.GONE);
                            Log.d(TAG, "onChanged: LOGOUT");
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, resource.message, Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onChanged: ERROR - " + resource.message);
                            break;
                    }
                }
            }
        });
    }

    private void onLoginSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void onButtonClicked(View view) {
        if (!TextUtils.isEmpty(etUserId.getText().toString().trim())) {
            loginViewModel.authenticateUser(Integer.parseInt(etUserId.getText().toString().trim()));
        } else {
            Toast.makeText(this, "Please enter user id.", Toast.LENGTH_SHORT).show();
        }
    }
}
