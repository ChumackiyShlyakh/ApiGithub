package com.gd.oshturniev.apigithub.following;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.databinding.Bindable;
import android.databinding.ObservableInt;

import com.gd.oshturniev.apigithub.BR;
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.arch.BaseAndroidViewModel;
import com.gd.oshturniev.apigithub.core.model.response.followers.FollowingResponse;
import com.gd.oshturniev.apigithub.room.Resource;

import java.util.List;

import javax.inject.Inject;

public class FollowingViewModel extends BaseAndroidViewModel {

    private FollowingAdapter adapter;
    private LiveData<Resource<List<FollowingResponse>>> following;
    private FollowingDataRepository followingDataRepository;

    @Inject
    public FollowingViewModel(Application application) {
        super(application);
        adapter = new FollowingAdapter();
        followingDataRepository = new FollowingDataRepository(application);
        following = followingDataRepository.loadFollowing(ApiGitHubApplication.getSharedPrefInstance().getUserName());
    }

    @Bindable
    public FollowingAdapter getAdapter() {
        return adapter;
    }

    public void setUpFollowing(List<FollowingResponse> data) {
        adapter.setFollowingResponse(data);
        notifyPropertyChanged(BR.adapter);
    }

    public LiveData<Resource<List<FollowingResponse>>> getFollowing() {
        return following;
    }
}
