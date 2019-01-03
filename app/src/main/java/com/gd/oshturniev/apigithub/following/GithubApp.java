//package com.gd.oshturniev.apigithub.following;
//
//import android.app.Activity;
//import android.app.Application;
//
//import javax.inject.Inject;
//
//public class GithubApp extends Application implements HasActivityInjector {
//
//    @Inject
//    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        if (BuildConfig.DEBUG) {
//            Timber.plant(new Timber.DebugTree());
//        }
//        AppInjector.init(this);
//    }
//
//    @Override
//    public DispatchingAndroidInjector<Activity> activityInjector() {
//        return dispatchingAndroidInjector;
//    }
//}
