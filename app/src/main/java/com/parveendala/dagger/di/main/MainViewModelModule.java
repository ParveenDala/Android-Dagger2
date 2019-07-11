package com.parveendala.dagger.di.main;

import androidx.lifecycle.ViewModel;

import com.parveendala.dagger.di.ViewModelKey;
import com.parveendala.dagger.ui.main.MainViewModel;
import com.parveendala.dagger.ui.main.post.PostViewModel;
import com.parveendala.dagger.ui.main.profile.ProfileViewModel;

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
    public abstract ViewModel mainViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel profileViewModel(ProfileViewModel profileViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    public abstract ViewModel postViewModel(PostViewModel postViewModel);


}
