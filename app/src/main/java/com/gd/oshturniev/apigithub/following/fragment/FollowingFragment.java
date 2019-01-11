package com.gd.oshturniev.apigithub.following.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.core.model.response.followers.FollowingResponse;
import com.gd.oshturniev.apigithub.databinding.FragmentFollowingBinding;
import com.gd.oshturniev.apigithub.following.viewmodel.FollowingViewModel;
import com.gd.oshturniev.apigithub.net.Resource;

import java.util.List;

import javax.inject.Inject;

public class FollowingFragment extends Fragment {

    FollowingViewModel followingViewModel;
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    public FollowingFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentFollowingBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_following, container, false);

        followingViewModel = ViewModelProviders.of(this, viewModelFactory).get(FollowingViewModel.class);

        binding.setViewmodel(followingViewModel);
        binding.gitList.setLayoutManager(new LinearLayoutManager(getContext()));
        followingViewModel.getFollowing().observe(this, new Observer<Resource<List<FollowingResponse>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<FollowingResponse>> listResource) {
                if (listResource.status == Resource.Status.LOADING) {
                    followingViewModel.viewSpinnerFollowing.set(View.VISIBLE);
                }
                if (listResource.status == Resource.Status.SUCCESS) {
                    followingViewModel.setUpFollowing(listResource.data);
                    followingViewModel.viewSpinnerFollowing.set(View.GONE);
                } else if (listResource.status == Resource.Status.ERROR) {
                    followingViewModel.viewSpinnerFollowing.set(View.GONE);
                    Toast.makeText(getContext(), listResource.message, Toast.LENGTH_LONG).show();
                }
            }
        });
        return binding.getRoot();
    }
}
