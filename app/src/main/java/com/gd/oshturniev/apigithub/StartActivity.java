package com.gd.oshturniev.apigithub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.AppSharedPreferenceManager;
import com.gd.oshturniev.apigithub.core.model.response.login.LoginErrorResponse;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.login.activity.LoginActivity;
import com.gd.oshturniev.apigithub.login.fragment.LoginFragment;
import com.gd.oshturniev.apigithub.repo.fragment.GitFragment;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends FragmentActivity implements Callback<UserResponse> {

    private final int SPLASH_DISPLAY_LENGTH = 1500;
    private Callback<UserResponse> userCallback;
    private ImageView image;
    private Gson gson;
    private LoginErrorResponse loginErrorResponse;
    private boolean isUserNotNull;
    boolean isTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        image = findViewById(R.id.image);
        userCallback = this;
        gson = new Gson();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ApiGitHubApplication.getRestClientInstance().getApiGit().getUser().enqueue(userCallback);
                isSharedpreferenseTrue();
            }
        }, SPLASH_DISPLAY_LENGTH);
//                StartActivity.this.finish();
    }

    public void isSharedpreferenseTrue() {
        isTrue = ApiGitHubApplication.getSharedPrefInstance().isAuth();
//        ApiGitHubApplication.getRestClientInstance().getApiGit().getUser().enqueue(userCallback);

//        if (isUserNotNull) {
//            if (isUserNotNull) {
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.fragment_container, GitFragment.newInstance(null)).commit();
//                StartActivity.this.finish();
//            }
//        } else {
//            Intent mainIntent = new Intent(StartActivity.this, LoginActivity.class);
//            StartActivity.this.startActivity(mainIntent);
//            StartActivity.this.finish();
//        }
    }

    @Override
    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
        UserResponse user = response.body();
        if (user != null) {
            if (isTrue) {
//                isUserNotNull = true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, GitFragment.newInstance(user)).commit();
//                GitFragment.newInstance(user);
//                StartActivity.this.finish();
                image.setVisibility(View.GONE);
            }
        } else {
            loginErrorResponse = gson.fromJson(response.errorBody().charStream(), LoginErrorResponse.class);
            Intent mainIntent = new Intent(StartActivity.this, LoginActivity.class);
            StartActivity.this.startActivity(mainIntent);
//            StartActivity.this.finish();
        }
    }

    @Override
    public void onFailure(Call<UserResponse> call, Throwable t) {

    }
}
