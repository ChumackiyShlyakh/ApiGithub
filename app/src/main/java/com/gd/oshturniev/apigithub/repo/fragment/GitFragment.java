package com.gd.oshturniev.apigithub.repo.fragment;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;
import com.gd.oshturniev.apigithub.databinding.FragmentGitBinding;
import com.gd.oshturniev.apigithub.login.viewmodel.LoginViewModel;
import com.gd.oshturniev.apigithub.repo.adapter.RepoAdapter;
import com.gd.oshturniev.apigithub.repo.viewmodel.RepoViewModel;
import com.gd.oshturniev.apigithub.utils.Constants;

import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

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
        binding.setRepo(repoViewModel);
        binding.gitList.setLayoutManager(new LinearLayoutManager(getContext()));
        View view = binding.getRoot();

        spinner = view.findViewById(R.id.progressBar);


        hideKeyboard(getActivity());
        return view;
    }



    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

    }
}
