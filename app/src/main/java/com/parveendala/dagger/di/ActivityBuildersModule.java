package com.parveendala.dagger.di;

import com.parveendala.dagger.di.login.LoginModule;
import com.parveendala.dagger.di.login.LoginViewModelModule;
import com.parveendala.dagger.di.main.MainViewModelModule;
import com.parveendala.dagger.ui.login.LoginActivity;
import com.parveendala.dagger.ui.main.MainActivity;
import com.parveendala.dagger.ui.main.MainViewModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {LoginViewModelModule.class, LoginModule.class})
    abstract LoginActivity loginActivity();


    @ContributesAndroidInjector(modules = {MainViewModel.class, MainViewModelModule.class})
    public abstract MainActivity mainActivity();

}