package com.gd.oshturniev.apigithub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.AppSharedPreferenceManager;
import com.gd.oshturniev.apigithub.core.model.response.login.LoginErrorResponse;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.login.activity.LoginActivity;
import com.gd.oshturniev.apigithub.login.fragment.LoginFragment;
import com.gd.oshturniev.apigithub.repo.fragment.GitFragment;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener, Callback<UserResponse> {

    private final int SPLASH_DISPLAY_LENGTH = 500;
    private Callback<UserResponse> userCallback;
    private ImageView image;
    private Gson gson;
    private LoginErrorResponse loginErrorResponse;
    private boolean isUserNotNull;
    boolean isTrue;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        image = findViewById(R.id.image);
        userCallback = this;
        gson = new Gson();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ApiGitHubApplication.getRestClientInstance().getApiGit().getUser().enqueue(userCallback);
                isSharedpreferenseTrue();
            }
        }, SPLASH_DISPLAY_LENGTH);
//                StartActivity.this.finish();
    }

    public void isSharedpreferenseTrue() {
        isTrue = ApiGitHubApplication.getSharedPrefInstance().isAuth();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass = null;
        int id = item.getItemId();

        if (id == R.id.nav_followers) {
            fragmentClass = GitFragment.class;

        } else if (id == R.id.nav_following) {

        } else if (id == R.id.nav_load) {

        } else if (id == R.id.nav_log_out) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.fragment_container, new ExitFragment(),
//                    ExitFragment.class.getName()).commit();
            fragmentClass = LoginFragment.class;
            ApiGitHubApplication.getRestClientInstance().getApiGit().delUser().enqueue(userCallback);
            ApiGitHubApplication.getSharedPrefInstance().editor.clear().commit();
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

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
        UserResponse user = response.body();
        if (user != null) {
            if (isTrue) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, GitFragment.newInstance(user)).commit();
                image.setVisibility(View.GONE);
//                            StartActivity.this.finish();
            }
        } else {
            loginErrorResponse = gson.fromJson(response.errorBody().charStream(), LoginErrorResponse.class);
            Intent mainIntent = new Intent(StartActivity.this, LoginActivity.class);
            StartActivity.this.startActivity(mainIntent);
            StartActivity.this.finish();
        }
    }

    @Override
    public void onFailure(Call<UserResponse> call, Throwable t) {

    }
}
