package com.gd.oshturniev.apigithub.dagger.module;

import com.gd.oshturniev.apigithub.core.ui.MainActivity;
import com.gd.oshturniev.apigithub.core.ui.SplashActivity;
import com.gd.oshturniev.apigithub.dagger.scopes.ActivityScoped;
import com.gd.oshturniev.apigithub.login.activity.LoginActivity;
import com.gd.oshturniev.apigithub.repo.fragment.GitFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity messageActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity loginActivity();

//    @ActivityScoped
//    @ContributesAndroidInjector(modules = GitFragmentModule.class)
//    abstract GitFragment bindRepoAdapter();
}