package com.gd.oshturniev.apigithub.app;

import android.annotation.SuppressLint;

import com.gd.oshturniev.apigithub.core.data.AppSharedPreferenceManager;
import com.gd.oshturniev.apigithub.dagger.Application;
import com.gd.oshturniev.apigithub.net.RestClient;

public class ApiGitHubApplication extends Application {

    private static RestClient restClient;
    @SuppressLint("StaticFieldLeak")
    private static AppSharedPreferenceManager appSharedPreferenceManager;

//    @Inject
//    public ApiGitHubApplication() {
//        this.restClient = restClient;
//        this.appSharedPreferenceManager = new AppSharedPreferenceManager(this);
//    }

//    @Override
//    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
//        return DaggerAppComponent.builder().application(this).build();
//    }

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