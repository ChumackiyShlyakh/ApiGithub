package com.gd.oshturniev.apigithub.repo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.databinding.Bindable;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import com.gd.oshturniev.apigithub.BR;
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.arch.BaseAndroidViewModel;
import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;
import com.gd.oshturniev.apigithub.repo.adapter.RepoAdapter;
import com.gd.oshturniev.apigithub.room.Resource;
import com.gd.oshturniev.apigithub.room.UserDataRepository;

import java.util.List;

import javax.inject.Inject;

public class RepoViewModel extends BaseAndroidViewModel {

    private RepoAdapter adapter;
    private LiveData<Resource<List<ReposResponse>>> repos;
    private UserDataRepository userDataRepository;

    @Inject
    public RepoViewModel(@NonNull Application application) { // , RepoAdapter adapter, UserDataRepository userDataRepository
        super(application);
//        this.adapter = adapter;
//        this.userDataRepository = userDataRepository;
        adapter = new RepoAdapter();
        userDataRepository = new UserDataRepository(application);
        repos = userDataRepository.loadUser(ApiGitHubApplication.getSharedPrefInstance().getUserName());
    }

    @Bindable
    public RepoAdapter getAdapter() {
        return adapter;
    }

    public void setUpData(List<ReposResponse> data) {
        adapter.setRepoResponse(data);
        notifyPropertyChanged(BR.adapter);
    }

    public LiveData<Resource<List<ReposResponse>>> getRepos() {
        return repos;
    }
}
