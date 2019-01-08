package com.gd.oshturniev.apigithub.login.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.model.request.LoginModelRequest;
import com.gd.oshturniev.apigithub.core.model.response.login.LoginErrorResponse;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.core.ui.MainActivity;
import com.gd.oshturniev.apigithub.databinding.FragmentLoginBinding;
import com.gd.oshturniev.apigithub.login.viewmodel.LoginViewModel;
import com.gd.oshturniev.apigithub.utils.Utils;
import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends DaggerFragment implements Callback<UserResponse> {

    private Callback<UserResponse> userCallback;
//    private LoginViewModel viewModel;
    private Gson gson;

    @Inject
    LoginViewModel loginViewModel;

    @Inject
    MainActivity mainActivity;

    @Inject
    public LoginFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentLoginBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        fragmentBinding.setModel(loginViewModel);
        userCallback = this;
        gson = new Gson();
        loginViewModel.getLoginModelRequest().observe(this, new Observer<LoginModelRequest>() {
            @Override
            public void onChanged(LoginModelRequest loginModelRequest) {
                if (Utils.isNetworkConnected(getContext())) {
                    ApiGitHubApplication.getSharedPrefInstance().saveLoginDetails(loginModelRequest.getEmail(), loginModelRequest.getPassword());
                    ApiGitHubApplication.getRestClientInstance().getApiGit().getUser().enqueue(userCallback);
                } else {
                    Toast.makeText(getContext(), R.string.check_network_connection, Toast.LENGTH_LONG).show();
                }
            }
        });
        return fragmentBinding.getRoot();
    }

    @Override
    public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
        UserResponse user = response.body();
        if (user != null) {
            ApiGitHubApplication.getSharedPrefInstance().saveUserName(user.getLogin());
            ApiGitHubApplication.getSharedPrefInstance().setAuthState(true);
            Intent loginIntent = new Intent(getActivity(), MainActivity.class);
            startActivity(loginIntent);
            getActivity().finish();
        } else {

            LoginErrorResponse loginErrorResponse = gson.fromJson(response.errorBody().charStream(), LoginErrorResponse.class);
            Toast.makeText(getContext(), loginErrorResponse.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
        Toast.makeText(getActivity(), R.string.something_is_wrong, Toast.LENGTH_LONG).show();
    }
}