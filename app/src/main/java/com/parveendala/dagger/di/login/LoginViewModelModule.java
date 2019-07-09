package com.parveendala.dagger.di.login;

import androidx.lifecycle.ViewModel;

import com.parveendala.dagger.di.ViewModelKey;
import com.parveendala.dagger.ui.login.LoginViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Module
public abstract class LoginViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    public abstract ViewModel loginViewModel(LoginViewModel loginViewModel);

}
