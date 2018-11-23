package com.gd.oshturniev.apigithub.login.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Annotation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.core.model.response.UserResponse;
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.model.request.LoginModelRequest;
import com.gd.oshturniev.apigithub.core.model.response.login.LoginErrorResponse;
import com.gd.oshturniev.apigithub.databinding.FragmentLoginBinding;
import com.gd.oshturniev.apigithub.login.viewmodel.LoginViewModel;
import com.gd.oshturniev.apigithub.net.ApiGit;
import com.gd.oshturniev.apigithub.net.RestClient;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginFragment extends Fragment implements Callback<UserResponse> {

    private final String LOG_TAG = "myLogs";

    private Callback<UserResponse> userCallback;
    private Callback<LoginErrorResponse> userCalck;
    private LoginViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentLoginBinding fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        fragmentBinding.setModel(viewModel);
        userCallback = this;
        viewModel.getLoginModelRequest().observe(this, new Observer<LoginModelRequest>() {
            @Override
            public void onChanged(LoginModelRequest loginModelRequest) {
                Log.d(LOG_TAG, "LoginFragment viewModel.getLoginModelRequest(): " + " " + loginModelRequest.getEmail() +
                        " " + loginModelRequest.getPassword());
                ApiGitHubApplication.getSharedPrefInstance().saveLoginDetails(loginModelRequest.getEmail(), loginModelRequest.getPassword());
                ApiGitHubApplication.getRestClientInstance().getApiGit().getUser().enqueue(userCallback);
            }
        });
        return fragmentBinding.getRoot();
    }

    @Override
    public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
        UserResponse user = response.body();

//        Gson gson = new Gson();
//        LoginErrorResponse loginErrorResponse = gson.fromJson(response.errorBody().charStream(), LoginErrorResponse.class);
//        Toast.makeText(getContext(), loginErrorResponse.getMessage(), Toast.LENGTH_LONG).show();


//        ResponseBody responseBody = null;
//        if (response.isSuccessful()) {
////            responseBody = response.body();
//        } else {
//            responseBody = response.errorBody();
//}
        Retrofit retrofit = ApiGitHubApplication.getRestClientInstance().retrofit;
        LoginErrorResponse loginErrorResponse = null;
        try {
            loginErrorResponse = (LoginErrorResponse)retrofit.responseBodyConverter(
                    LoginErrorResponse.class, LoginErrorResponse.class.getAnnotations())
                    .convert(response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(getContext(), loginErrorResponse.getMessage(), Toast.LENGTH_LONG).show();

        if (user != null) {
            GitFragment.newInstance(user);
            Log.d(LOG_TAG, "LoginFragment onResponse if: " + " " + user.getUrl());
        } else {
            viewModel.checkLoginPassword();
        }
    }

    @Override
    public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
        Toast.makeText(getActivity(), getActivity().getString(R.string.something_is_wrong), Toast.LENGTH_LONG).show();
    }
}

//            JSONObject jObjError = new JSONObject(response.errorBody().string());
//            Toast.makeText(getContext(), jObjError.getString("message"), Toast.LENGTH_LONG).show();

//    Gson gson = new Gson();
//    LoginErrorResponse loginErrorResponse = gson.fromJson(response.errorBody().charStream(), LoginErrorResponse.class);
//        Toast.makeText(getContext(), loginErrorResponse.getMessage(), Toast.LENGTH_LONG).show();