package com.gd.oshturniev.apigithub.dagger.module;

import android.app.Application;
import android.content.Context;

import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;
import com.gd.oshturniev.apigithub.dagger.scopes.FragmentScoped;
import com.gd.oshturniev.apigithub.dagger.scopes.ListScope;
import com.gd.oshturniev.apigithub.repo.adapter.RepoAdapter;
import com.gd.oshturniev.apigithub.repo.fragment.GitFragment;
import com.gd.oshturniev.apigithub.repo.viewmodel.RepoViewModel;

import java.util.Arrays;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class GitFragmentModule {

//    @Provides
//    RepoViewModel repoViewModel() {
//        return new RepoViewModel();
//    }

//    @Provides
//    RepoAdapter provideRepoAdapter() {
//        return new RepoAdapter();
//    }
//
//    @ListScope
//    @Provides
//    List<ReposResponse> provideModels() {
//        return Arrays.asList();
//    }

//    @ListScope
//    @Provides
//    RepoAdapter provideRepoAdapter() {
//        return new RepoAdapter();
//    }
}
