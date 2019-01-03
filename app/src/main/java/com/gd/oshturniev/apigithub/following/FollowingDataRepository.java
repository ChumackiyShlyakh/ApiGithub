package com.gd.oshturniev.apigithub.following;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.AppExecutors;
import com.gd.oshturniev.apigithub.core.model.response.followers.FollowingResponse;
import com.gd.oshturniev.apigithub.following.room.RoomDBFollowing;
import com.gd.oshturniev.apigithub.net.ApiGit;
import com.gd.oshturniev.apigithub.net.ApiResponse;
import com.gd.oshturniev.apigithub.room.NetworkBoundResource;
import com.gd.oshturniev.apigithub.room.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FollowingDataRepository {

    private ApiGit apiGit;
    private RoomDBFollowing roomDBFollowing;
    private AppExecutors appExecutors;

    @Inject
    public FollowingDataRepository(Application application) {
        this.apiGit = ApiGitHubApplication.getRestClientInstance().getApiGit();
        this.roomDBFollowing = new RoomDBFollowing(application);
        this.appExecutors = new AppExecutors();
    }

    public LiveData<Resource<List<FollowingResponse>>> loadFollowing(final String userOwner) {

        return new NetworkBoundResource<List<FollowingResponse>, List<FollowingResponse>>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull List<FollowingResponse> item) {
                roomDBFollowing.insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<FollowingResponse> data) {
                return data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<FollowingResponse>> loadFromDb() {
                return roomDBFollowing.getAllFollowing();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<FollowingResponse>>> createCall() {
                return apiGit.getFollowing(userOwner);
            }
        }.asLiveData();
    }
}
