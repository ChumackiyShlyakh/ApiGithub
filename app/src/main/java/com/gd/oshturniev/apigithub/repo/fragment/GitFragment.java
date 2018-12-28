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
import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;
import com.gd.oshturniev.apigithub.databinding.FragmentGitBinding;
import com.gd.oshturniev.apigithub.repo.viewmodel.RepoViewModel;
import com.gd.oshturniev.apigithub.room.Resource;

import java.util.List;

import static com.gd.oshturniev.apigithub.utils.Utils.hideKeyboard;

public class GitFragment extends Fragment {

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
        repoViewModel = ViewModelProviders.of(this).get(RepoViewModel.class);
        spinner = binding.progressBar;
        binding.setRepo(repoViewModel);
        binding.gitList.setLayoutManager(new LinearLayoutManager(getContext()));
        repoViewModel.getRepos().observe(this, new Observer<Resource<List<ReposResponse>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<ReposResponse>> listResource) {
                if (listResource.status == Resource.Status.LOADING) {
                    spinner.setVisibility(View.VISIBLE);
                }
                if (listResource.status == Resource.Status.SUCCESS) {
                    repoViewModel.setUpData(listResource.data);
                    spinner.setVisibility(View.GONE);
                } else if(listResource.status == Resource.Status.ERROR){
                    spinner.setVisibility(View.GONE);
                    Toast.makeText(getContext(), listResource.message, Toast.LENGTH_LONG).show();
                }
            }
        });

        hideKeyboard(getActivity());
        return binding.getRoot();
    }
}