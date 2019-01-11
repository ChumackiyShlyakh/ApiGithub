package com.gd.oshturniev.apigithub.following.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.databinding.Bindable;
import android.databinding.ObservableInt;

import com.gd.oshturniev.apigithub.BR;
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.arch.BaseAndroidViewModel;
import com.gd.oshturniev.apigithub.core.model.response.followers.FollowingResponse;
import com.gd.oshturniev.apigithub.following.FollowingDataRepository;
import com.gd.oshturniev.apigithub.following.adapter.FollowingAdapter;
import com.gd.oshturniev.apigithub.net.Resource;

import java.util.List;

public class FollowingViewModel extends BaseAndroidViewModel {

    private FollowingAdapter adapter;
    private LiveData<Resource<List<FollowingResponse>>> following;
    private FollowingDataRepository followingDataRepository;
    public final ObservableInt viewSpinnerFollowing = new ObservableInt();

    @Bindable
    private boolean hideProgress;

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