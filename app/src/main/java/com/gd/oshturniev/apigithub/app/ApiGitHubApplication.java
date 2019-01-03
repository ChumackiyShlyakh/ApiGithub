package com.gd.oshturniev.apigithub.app;

import android.annotation.SuppressLint;

import com.gd.oshturniev.apigithub.core.data.AppSharedPreferenceManager;
import com.gd.oshturniev.apigithub.following.DaggerAppComponent;
import com.gd.oshturniev.apigithub.net.RestClient;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class ApiGitHubApplication extends DaggerApplication {

    private static RestClient restClient;
    @SuppressLint("StaticFieldLeak")
    private static AppSharedPreferenceManager appSharedPreferenceManager;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

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
