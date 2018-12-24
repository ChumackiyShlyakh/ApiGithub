package com.gd.oshturniev.apigithub.repo.fragment;

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
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;
import com.gd.oshturniev.apigithub.databinding.FragmentGitBinding;
import com.gd.oshturniev.apigithub.repo.viewmodel.RepoViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.gd.oshturniev.apigithub.utils.Utils.hideKeyboard;

public class GitFragment extends Fragment implements retrofit2.Callback<List<ReposResponse>> {

    private retrofit2.Callback<List<ReposResponse>> userCallback;

    private RepoViewModel repoViewModel;
    private ProgressBar spinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentGitBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_git, container, false);
        userCallback = this;
        ApiGitHubApplication.getRestClientInstance().getApiGit().getRepos(ApiGitHubApplication.getSharedPrefInstance().getUserName()).enqueue(userCallback);
        repoViewModel = ViewModelProviders.of(this).get(RepoViewModel.class);

        repoViewModel.getRepos().observe(this, new Observer<List<ReposResponse>>() {
            @Override
            public void onChanged(@Nullable final List<ReposResponse> reposResponse) {
                // Update the cached copy of the words in the adapter.
                repoViewModel.setUp(reposResponse);
            }
        });

        binding.setRepo(repoViewModel);
        binding.gitList.setLayoutManager(new LinearLayoutManager(getContext()));
        spinner = binding.progressBar;
        hideKeyboard(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onResponse(Call<List<ReposResponse>> call, Response<List<ReposResponse>> response) {
        spinner.setVisibility(View.VISIBLE);
        List<ReposResponse> reposResponse = response.body();
        repoViewModel.setUp(reposResponse);
        spinner.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(Call<List<ReposResponse>> call, Throwable t) {
        Toast.makeText(getActivity(), R.string.something_is_wrong, Toast.LENGTH_LONG).show();
        spinner.setVisibility(View.GONE);
    }
}