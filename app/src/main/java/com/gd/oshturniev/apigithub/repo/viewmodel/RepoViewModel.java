package com.gd.oshturniev.apigithub.repo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.gd.oshturniev.apigithub.core.arch.BaseAndroidViewModel;
import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;
import com.gd.oshturniev.apigithub.repo.adapter.RepoAdapter;
import com.gd.oshturniev.apigithub.room.RoomDBRepository;

import java.util.List;

public class RepoViewModel extends BaseAndroidViewModel {

    private RepoAdapter adapter;
    private RoomDBRepository roomRepository;
    private LiveData<List<ReposResponse>> repos;

    public RepoViewModel(@NonNull Application application) {
        super(application);
        adapter = new RepoAdapter();
        roomRepository = new RoomDBRepository(application);
        repos = roomRepository.getAllRepos();
    }

    @Bindable
    public RepoAdapter getAdapter() {
        return adapter;
    }

    public void setUp(List<ReposResponse> data) {
        adapter.setRepoResponse(data);
        roomRepository.insert(data);
    }

    public LiveData<List<ReposResponse>> getRepos() {
        return repos;
    }

    public  void deleteDB() {
        roomRepository.delete();
    }
}
