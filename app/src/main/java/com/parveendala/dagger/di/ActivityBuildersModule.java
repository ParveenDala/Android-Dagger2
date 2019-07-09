package com.parveendala.dagger.di;

import com.parveendala.dagger.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract LoginActivity loginActivity();

}