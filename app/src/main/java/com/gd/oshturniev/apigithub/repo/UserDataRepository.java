package com.gd.oshturniev.apigithub.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.AppExecutors;
import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;
import com.gd.oshturniev.apigithub.net.ApiGit;
import com.gd.oshturniev.apigithub.net.ApiResponse;
import com.gd.oshturniev.apigithub.net.NetworkBoundResource;
import com.gd.oshturniev.apigithub.net.Resource;
import com.gd.oshturniev.apigithub.room.RoomDBRepository;

import java.util.List;

public class UserDataRepository {

   private ApiGit apiGit;
   private RoomDBRepository roomDBRepository;
   private AppExecutors appExecutors;

    public UserDataRepository(Application application) {
        this.apiGit = ApiGitHubApplication.getRestClientInstance().getApiGit();
        this.roomDBRepository = new RoomDBRepository(application);
        this.appExecutors = new AppExecutors();
    }

    public LiveData<Resource<List<ReposResponse>>> loadUser(final String userOwner) {

        return new NetworkBoundResource<List<ReposResponse>, List<ReposResponse>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<ReposResponse> item) {
                roomDBRepository.insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<ReposResponse> data) {
                return data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<ReposResponse>> loadFromDb() {
                return roomDBRepository.getAllRepos();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<ReposResponse>>> createCall() {
                return apiGit.getRepos(userOwner);
            }
        }.asLiveData();
    }
}
