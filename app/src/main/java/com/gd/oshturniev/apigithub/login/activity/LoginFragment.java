package com.gd.oshturniev.apigithub.login.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.User;
import com.gd.oshturniev.apigithub.core.model.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.model.request.LoginModelRequest;
import com.gd.oshturniev.apigithub.databinding.FragmentLoginBinding;
import com.gd.oshturniev.apigithub.login.viewModel.LoginViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements Callback<User> { //implements Callback<User>

    final String LOG_TAG = "myLogs";

    private LoginViewModel viewModel;
    private FragmentLoginBinding fragmentBinding;
    public String encode;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        fragmentBinding.setModel(viewModel);
        viewModel.getLoginModelRequest().observe(this, new Observer<LoginModelRequest>() {
            @Override
            public void onChanged(LoginModelRequest loginModelRequest) {
                Log.d(LOG_TAG, "LoginFragment viewModel.getLoginModelRequest(): " + " " + loginModelRequest.getEmail() +
                        " " + loginModelRequest.getPassword());
                ApiGitHubApplication.getSharedPrefInstance().saveLoginDetails(loginModelRequest.getEmail(), loginModelRequest.getPassword());
                ApiGitHubApplication.getRestClientInstance().getApiGit().getUser();
            }
        });
        return fragmentBinding.getRoot();
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        User user = response.body();
        if (user != null) {
            Log.d(LOG_TAG, "if: " + " " + user.getUrl());
        } else {
            Log.d(LOG_TAG, "else: " + " ");
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Toast.makeText(getActivity(), "Something is wrong! Please check your credeentials!", Toast.LENGTH_LONG).show();
    }
}
