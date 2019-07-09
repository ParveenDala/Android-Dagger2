package com.parveendala.dagger.di;

import androidx.lifecycle.ViewModelProvider;

import com.parveendala.dagger.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Module
public abstract class ViewModelFactoryModule {


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

}
