package com.parveendala.dagger.di;

import com.parveendala.dagger.di.login.LoginModule;
import com.parveendala.dagger.di.login.LoginScope;
import com.parveendala.dagger.di.login.LoginViewModelModule;
import com.parveendala.dagger.di.main.FragmentBuildersModule;
import com.parveendala.dagger.di.main.MainModule;
import com.parveendala.dagger.di.main.MainScope;
import com.parveendala.dagger.di.main.MainViewModelModule;
import com.parveendala.dagger.ui.login.LoginActivity;
import com.parveendala.dagger.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Module
public abstract class ActivityBuildersModule {

    @LoginScope
    @ContributesAndroidInjector(modules = {LoginViewModelModule.class, LoginModule.class})
    abstract LoginActivity loginActivity();


    @MainScope
    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class, MainModule.class, MainViewModelModule.class})
    public abstract MainActivity mainActivity();

}