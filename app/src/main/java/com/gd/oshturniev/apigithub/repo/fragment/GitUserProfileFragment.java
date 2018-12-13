package com.gd.oshturniev.apigithub.repo.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gd.oshturniev.apigithub.R;

public class GitUserProfileFragment extends Fragment {

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        FragmentGitUserProfileBinding binding = DataBindingUtil.inflate(inflater,
//                R.layout.fragment_git_user_profile, container, false);
//        userCallback = this;
//        ApiGitHubApplication.getRestClientInstance().getApiGit().getRepos(ApiGitHubApplication.getSharedPrefInstance().getUserName()).enqueue(userCallback);
//
//        repoViewModel = ViewModelProviders.of(this).get(RepoViewModel.class);
//        binding.setRepo(repoViewModel);
//        binding.gitList.setLayoutManager(new LinearLayoutManager(getContext()));
//        View view = binding.getRoot();
//        hideKeyboard(getActivity());
//        return view;
//    }
}
