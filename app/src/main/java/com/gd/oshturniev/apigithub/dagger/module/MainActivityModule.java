package com.gd.oshturniev.apigithub.dagger.module;

import com.gd.oshturniev.apigithub.dagger.scopes.FragmentScoped;
import com.gd.oshturniev.apigithub.repo.fragment.GitFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract GitFragment messageFragment();
}