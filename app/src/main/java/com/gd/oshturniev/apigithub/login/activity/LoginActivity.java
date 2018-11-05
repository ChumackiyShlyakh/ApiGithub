package com.gd.oshturniev.apigithub.login.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gd.oshturniev.apigithub.GitFragment;
import com.gd.oshturniev.apigithub.OnBackPressedListener;
import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.User;
import com.gd.oshturniev.apigithub.auth.RestClient;
import com.gd.oshturniev.apigithub.core.model.request.LoginModelRequest;
import com.gd.oshturniev.apigithub.login.viewModel.LoginViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener { // , Callback<User>

    final String LOG_TAG = "myLogs";

    GitFragment gitFragment;

//    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setupBindings();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        Log.d(LOG_TAG, "onCreate: " + " ");
    }
//    private FragmentLoginBinding fragmentBinding;
//    private void setupBindings() {
//        fragmentBinding = DataBindingUtil.setContentView(this, R.layout.fragment_login); // activity_main content_main
//        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
//        fragmentBinding.setModel(viewModel);
//        viewModel.getLoginModelRequest().observe(this, new Observer<LoginModelRequest>() {
//            @Override
//            public void onChanged(LoginModelRequest loginModelRequest) {
//                String encode = Base64.encodeToString((loginModelRequest.getEmail() + ":" + loginModelRequest.getPassword()).getBytes(),
//                        Base64.DEFAULT).replace("\n", "");
//                Call<User> call = RestClient.getApiGit().getUser("Basic " + encode);
//                call.enqueue(LoginActivity.this);
//            }
//        });
//    }
//
//    @Override
//    public void onResponse(Call<User> call, Response<User> response) {
//        User user = response.body();
//        if (user != null) {
//            fragmentBinding.text.setText(user.getLogin());
//            Log.d(LOG_TAG, "if: " + " " + user.getUrl());
//        } else {
//            Log.d(LOG_TAG, "else: " + " ");
//        }
//    }
//
//    @Override
//    public void onFailure(Call<User> call, Throwable t) {
//        Toast.makeText(getApplication(), "Something is wrong! Please check your credeentials!", Toast.LENGTH_LONG).show();
//    }



    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        OnBackPressedListener backPressedListener = null;
        for (Fragment fragment : fm.getFragments()) {
            if (fragment instanceof OnBackPressedListener) {
                backPressedListener = (OnBackPressedListener) fragment;
                break;
            }
        }
        if (backPressedListener != null) {
            backPressedListener.onBackPressed();
        } else {
            Log.d(LOG_TAG, "onBackPressed_4 ");
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass = null;
        int id = item.getItemId();

        if (id == R.id.nav_followers) {
            fragmentClass = GitFragment.class;

        } else if (id == R.id.nav_following) {

        } else if (id == R.id.nav_load) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_save) {

        } else if (id == R.id.nav_exit) {

        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}