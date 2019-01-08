package com.gd.oshturniev.apigithub.login.activity;

import android.os.Bundle;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.dagger.ActivityUtils;
import com.gd.oshturniev.apigithub.login.fragment.LoginFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public class LoginActivity extends DaggerAppCompatActivity {

    @Inject
    LoginFragment loginFragment;

    @Inject
    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_login);
        if (fragment == null) {
            fragment = loginFragment;
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container_login);
        }
    }
}