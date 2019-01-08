package com.gd.oshturniev.apigithub.dagger.module;

import com.gd.oshturniev.apigithub.dagger.scopes.FragmentScoped;
import com.gd.oshturniev.apigithub.login.fragment.LoginFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LoginActivityModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract LoginFragment loginFragment();
}