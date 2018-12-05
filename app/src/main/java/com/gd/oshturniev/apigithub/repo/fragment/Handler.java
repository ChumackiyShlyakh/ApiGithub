//package com.gd.oshturniev.apigithub.repo.fragment;
//
//import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.view.MenuItem;
//
//import com.gd.oshturniev.apigithub.R;
//import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
//import com.gd.oshturniev.apigithub.core.ui.MainActivity;
//import com.gd.oshturniev.apigithub.login.activity.LoginActivity;
//
//public class Handler {
//
//    public boolean onNavigationClick(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_log_out:
//                ApiGitHubApplication.getSharedPrefInstance().clearPrefs();
//                ApiGitHubApplication.getRestClientInstance().getApiGit().delUser().enqueue(userCallback);
//
//                Intent loginIntent = new Intent(MainActivity, LoginActivity.class);
//                MainActivity.startActivity(loginIntent);
//                MainActivity.finish();
//        }
//        return true;
//    }
//}
