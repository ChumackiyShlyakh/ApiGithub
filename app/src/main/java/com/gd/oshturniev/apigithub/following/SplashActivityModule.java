package com.gd.oshturniev.apigithub.following;

import com.gd.oshturniev.apigithub.core.ui.MainActivity;
import com.gd.oshturniev.apigithub.following.scopes.FragmentScoped;
import com.gd.oshturniev.apigithub.repo.fragment.GitFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SplashActivityModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainActivity messageFragment();
}
