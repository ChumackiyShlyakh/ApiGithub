package com.gd.oshturniev.apigithub.following;

import android.arch.lifecycle.Observer;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.core.model.response.followers.FollowingResponse;
import com.gd.oshturniev.apigithub.databinding.FragmentFollowingBinding;
import com.gd.oshturniev.apigithub.room.Resource;

import java.util.List;

public class FollowingFragment extends Fragment {

    private FollowingViewModel followingViewModel;
    private ProgressBar spinner;

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
        followingViewModel = ViewModelProviders.of(this).get(FollowingViewModel.class);
        spinner = binding.progressBar;
        binding.setViewmodel(followingViewModel);
        binding.gitList.setLayoutManager(new LinearLayoutManager(getContext()));
        followingViewModel.getFollowing().observe(this, new Observer<Resource<List<FollowingResponse>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<FollowingResponse>> listResource) {
                if (listResource.status == Resource.Status.LOADING) {
                    spinner.setVisibility(View.VISIBLE);
                }
                if (listResource.status == Resource.Status.SUCCESS) {
                    followingViewModel.setUpFollowing(listResource.data);
                    spinner.setVisibility(View.GONE);
                } else if (listResource.status == Resource.Status.ERROR) {
                    spinner.setVisibility(View.GONE);
                    Toast.makeText(getContext(), listResource.message, Toast.LENGTH_LONG).show();
                }
            }
        });
        return binding.getRoot();
    }
}
