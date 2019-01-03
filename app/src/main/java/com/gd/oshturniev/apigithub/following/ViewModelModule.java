package com.gd.oshturniev.apigithub.following;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.gd.oshturniev.apigithub.following.scopes.AppScoped;
import com.gd.oshturniev.apigithub.repo.viewmodel.RepoViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(FollowingViewModel.class)
//    abstract ViewModel bindFollowingViewModel(FollowingViewModel followingViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel.class)
    abstract ViewModel bindRepoViewModel(RepoViewModel repoViewModel);

    @Binds
    @AppScoped
    abstract ViewModelProvider.Factory bindViewModelFactory(GithubViewModelFactory factory);
}
