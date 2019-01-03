package com.gd.oshturniev.apigithub.login.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.login.fragment.LoginFragment;

import dagger.android.support.DaggerAppCompatActivity;


public class LoginActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_login, new LoginFragment(),
                LoginFragment.class.getName()).commit();
    }
}