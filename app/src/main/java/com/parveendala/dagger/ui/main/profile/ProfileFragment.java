package com.parveendala.dagger.ui.main.profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.parveendala.dagger.R;
import com.parveendala.dagger.models.User;
import com.parveendala.dagger.ui.login.LoginResource;
import com.parveendala.dagger.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


/**
 * Parveen Dala
 * Android-Dagger2
 */
public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    ProfileViewModel profileViewModel;

    private TextView tvName, tvEmail, tvWebsite;

    @Inject
    public ProfileFragment() {
    }

    private void setUserDetails(User user) {
        if (user != null) {
            tvName.setText(user.getName());
            tvEmail.setText(user.getEmail());
            tvWebsite.setText(user.getWebsite());
        }
    }

    private void setErrorDetails(String message) {
        tvName.setText(message);
    }

    private void observeLoginUser() {
        profileViewModel.observeLoginUser().removeObservers(getViewLifecycleOwner());
        profileViewModel.observeLoginUser().observe(getViewLifecycleOwner(), new Observer<LoginResource<User>>() {
            @Override
            public void onChanged(LoginResource<User> resource) {
                if (resource != null) {
                    switch (resource.status) {
                        case AUTHENTICATED:
                            setUserDetails(resource.data);
                            break;
                        case ERROR:
                            setErrorDetails(resource.message);
                            break;
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName = view.findViewById(R.id.name);
        tvEmail = view.findViewById(R.id.email);
        tvWebsite = view.findViewById(R.id.website);
        profileViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ProfileViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        observeLoginUser();
    }
}
