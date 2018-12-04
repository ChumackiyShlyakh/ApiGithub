package com.gd.oshturniev.apigithub.core.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.model.response.login.LoginErrorResponse;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.login.activity.LoginActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends FragmentActivity implements Callback<UserResponse> {

    private final int SPLASH_DISPLAY_LENGTH = 500;
    private Callback<UserResponse> userCallback;
//    private Gson gson;
//    private LoginErrorResponse loginErrorResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        userCallback = this;
//        gson = new Gson();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ApiGitHubApplication.getRestClientInstance().getApiGit().getUser().enqueue(userCallback);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    @Override
    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
        UserResponse user = response.body();
//        if (user != null) {
            if (!ApiGitHubApplication.getSharedPrefInstance().isAuth()) {
//                loginErrorResponse = gson.fromJson(response.errorBody().charStream(), LoginErrorResponse.class);
                Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
                SplashActivity.this.startActivity(loginIntent);
                SplashActivity.this.finish();
            } else {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
//        }
    }

    @Override
    public void onFailure(Call<UserResponse> call, Throwable t) {

    }
}
