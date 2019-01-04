package com.gd.oshturniev.apigithub.dagger.module;

import com.gd.oshturniev.apigithub.core.ui.MainActivity;
import com.gd.oshturniev.apigithub.dagger.scopes.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SplashActivityModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainActivity messageFragment();
}
