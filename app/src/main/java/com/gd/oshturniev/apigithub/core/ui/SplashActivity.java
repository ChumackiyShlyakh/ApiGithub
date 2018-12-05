package com.gd.oshturniev.apigithub.core.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        userCallback = this;
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
        if (user != null) {
            if (!ApiGitHubApplication.getSharedPrefInstance().isAuth()) {
                Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                SplashActivity.this.finish();
            } else {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        } else {
            Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            SplashActivity.this.finish();
        }
    }

    @Override
    public void onFailure(Call<UserResponse> call, Throwable t) {
        Toast.makeText(this, R.string.something_is_wrong, Toast.LENGTH_LONG).show();
    }
}
