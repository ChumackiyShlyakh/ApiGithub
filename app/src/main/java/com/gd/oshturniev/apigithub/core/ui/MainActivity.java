package com.gd.oshturniev.apigithub.core.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gd.oshturniev.apigithub.R;
import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.databinding.ActivityMainBinding;
import com.gd.oshturniev.apigithub.drawer.viewmodel.DrawerItemsViewModel;
import com.gd.oshturniev.apigithub.login.activity.LoginActivity;
import com.gd.oshturniev.apigithub.repo.fragment.GitFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<UserResponse> {

    private DrawerItemsViewModel viewModel;
    private Callback<UserResponse> userCallback;
    UserResponse user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userCallback = this;

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(DrawerItemsViewModel.class);
        binding.setDraweritems(viewModel);

        viewModel.getLoginModelRequest().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {

                switch (integer) {
                    case R.id.nav_log_out:
                        ApiGitHubApplication.getSharedPrefInstance().clearPrefs();
                        ApiGitHubApplication.getRestClientInstance().getApiGit().delUser().enqueue(userCallback);

                        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        MainActivity.this.finish();
                }
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, GitFragment.newInstance(user)).commit();
    }

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
