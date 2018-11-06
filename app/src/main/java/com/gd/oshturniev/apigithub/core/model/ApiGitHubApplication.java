package com.gd.oshturniev.apigithub.core.model;

import android.app.Application;

import com.gd.oshturniev.apigithub.auth.RestClient;

public class ApiGitHubApplication extends Application {

    private static RestClient restClient;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static RestClient getInstance() {
        if (restClient == null){
            restClient = new RestClient();
        }
        return restClient;
    }
}
