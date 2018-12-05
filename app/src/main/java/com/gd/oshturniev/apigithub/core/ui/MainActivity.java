package com.gd.oshturniev.apigithub.core.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.databinding.ActivityMainBinding;
import com.gd.oshturniev.apigithub.drawer.DrawerItems;
import com.gd.oshturniev.apigithub.login.activity.LoginActivity;
import com.gd.oshturniev.apigithub.login.fragment.LoginFragment;
import com.gd.oshturniev.apigithub.repo.fragment.GitFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements  Callback<UserResponse> { // NavigationView.OnNavigationItemSelectedListener,

    private DrawerLayout drawer;
    private Callback<UserResponse> userCallback;
    UserResponse user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        drawer = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        userCallback = this;

        DrawerItems drawerItems = new DrawerItems();

//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        ActivityMainBinding binding =
                DataBindingUtil.setContentView(
                        this,
                        R.layout.activity_main
                );
        binding.setDraweritems(drawerItems);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, GitFragment.newInstance(user)).commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        Fragment fragment = null;
//        Class fragmentClass = null;
//        int id = item.getItemId();
//
//        if (id == R.id.nav_followers) {
//            fragmentClass = GitFragment.class;
//
//        } else if (id == R.id.nav_following) {
//
//        } else if (id == R.id.nav_load) {
//
//        } else if (id == R.id.nav_log_out) {
//            ApiGitHubApplication.getSharedPrefInstance().clearPrefs();
//            ApiGitHubApplication.getRestClientInstance().getApiGit().delUser().enqueue(userCallback);
//
//            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(loginIntent);
//            MainActivity.this.finish();
//        } else if (id == R.id.nav_save) {
//
//        } else if (id == R.id.nav_exit) {
//        }
//        if (fragmentClass != null) {
//            try {
//                fragment = (Fragment) fragmentClass.newInstance();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
//        }
//
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    @Override
    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
        user = response.body();
    }

    @Override
    public void onFailure(Call<UserResponse> call, Throwable t) {

    }
}
