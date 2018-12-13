package com.gd.oshturniev.apigithub.repo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.support.annotation.NonNull;

import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;
import com.gd.oshturniev.apigithub.repo.adapter.RepoAdapter;

import java.util.List;

public class RepoViewModel extends AndroidViewModel implements Observable {

    private RepoAdapter adapter;

    public RepoViewModel(@NonNull Application application) {
        super(application);
        adapter = new RepoAdapter();
    }

    @Bindable
    public RepoAdapter getAdapter() {
        return adapter;
    }

    public void setUp(List<ReposResponse> data) {
        adapter.setRepoResponse(data);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
