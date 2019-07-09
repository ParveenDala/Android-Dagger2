package com.parveendala.dagger.di;

import com.parveendala.dagger.di.login.LoginViewModelModule;
import com.parveendala.dagger.ui.login.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {LoginViewModelModule.class})
    abstract LoginActivity loginActivity();

}