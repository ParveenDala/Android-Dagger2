package com.parveendala.dagger.di.main;

import androidx.lifecycle.ViewModel;

import com.parveendala.dagger.di.ViewModelKey;
import com.parveendala.dagger.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel mainViewModel(MainViewModel mainViewModel);


}
