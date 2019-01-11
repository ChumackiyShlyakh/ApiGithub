package com.gd.oshturniev.apigithub.app;

import android.annotation.SuppressLint;
import android.app.Application;

import com.gd.oshturniev.apigithub.core.data.AppSharedPreferenceManager;
import com.gd.oshturniev.apigithub.net.RestClient;

public class ApiGitHubApplication extends Application {

    private static RestClient restClient;
    @SuppressLint("StaticFieldLeak")
    private static AppSharedPreferenceManager appSharedPreferenceManager;

    @Override
    public void onCreate() {
        super.onCreate();
        restClient = new RestClient();
        appSharedPreferenceManager = new AppSharedPreferenceManager(this);
    }

    public static RestClient getRestClientInstance() {
        return restClient;
    }

    public static AppSharedPreferenceManager getSharedPrefInstance() {
        return appSharedPreferenceManager;
    }
}