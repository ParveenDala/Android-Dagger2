package com.parveendala.dagger.di.main;

import com.parveendala.dagger.ui.main.post.PostFragment;
import com.parveendala.dagger.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Parveen Dala
 * Android-Dagger2
 */
@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    public abstract ProfileFragment profileFragment();

    @ContributesAndroidInjector
    public abstract PostFragment postFragment();


}
