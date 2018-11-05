package com.gd.oshturniev.apigithub.login.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.User;
import com.gd.oshturniev.apigithub.auth.RestClient;
import com.gd.oshturniev.apigithub.core.model.request.LoginModelRequest;
import com.gd.oshturniev.apigithub.databinding.FragmentLoginBinding;
import com.gd.oshturniev.apigithub.login.viewModel.LoginViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements Callback<User> { //implements Callback<User>

    final String LOG_TAG = "myLogs";

    private LoginViewModel viewModel;
    FragmentLoginBinding fragmentBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        View view = fragmentBinding.getRoot();

//        View view = LayoutInflater.from(getActivity()).inflate(inflater, R.layout.fragment_login, container, false);
//        ViewDataBinding binding = DataBindingUtil.bind(view);

        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        viewModel.getLoginModelRequest().observe(this, new Observer<LoginModelRequest>() {
            @Override
            public void onChanged(LoginModelRequest loginModelRequest) {
            Log.d(LOG_TAG, "onChanged: " + " " + loginModelRequest.getEmail());
                String encode = Base64.encodeToString((loginModelRequest.getEmail() + ":" + loginModelRequest.getPassword()).getBytes(),
                        Base64.DEFAULT).replace("\n", "");
                Call<User> call = RestClient.getApiGit().getUser("Basic " + encode);
                call.enqueue(LoginFragment.this);
                Log.d(LOG_TAG, "onChanged: " + " " + encode);
            }
        });

        fragmentBinding.setModel(viewModel);

        TextInputLayout til = (TextInputLayout) view.findViewById(R.id.text_input_layout_email);
        til.setError("You need to enter an email");

        TextInputLayout til2 = (TextInputLayout) view.findViewById(R.id.text_input_layout_password);
        til2.setError("You need to enter a password");

        return view;
    }

//    private FragmentLoginBinding fragmentBinding;
//    private void setupBindings() {
//        fragmentBinding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_login); // activity_main content_main
//        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
//        fragmentBinding.setModel(viewModel);
//        viewModel.getLoginModelRequest().observe(this, new Observer<LoginModelRequest>() {
//            @Override
//            public void onChanged(LoginModelRequest loginModelRequest) {
//                String encode = Base64.encodeToString((loginModelRequest.getEmail() + ":" + loginModelRequest.getPassword()).getBytes(),
//                        Base64.DEFAULT).replace("\n", "");
//                Call<User> call = RestClient.getApiGit().getUser("Basic " + encode);
//                call.enqueue(LoginFragment.this);
//            }
//        });
//    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        User user = response.body();
        if (user != null) {
            fragmentBinding.text.setText(user.getLogin());
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
