package com.gd.oshturniev.apigithub.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gd.oshturniev.apigithub.app.ApiGitHubApplication;
import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;
import com.gd.oshturniev.apigithub.net.ApiGit;

import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataRepository {

    private final ApiGit apiGit;
    private final RoomDao roomDao;

    public UserDataRepository(ApiGit apiGit, RoomDao roomDao) {
        this.apiGit = apiGit;
        this.roomDao = roomDao;
    }

    public LiveData<ReposResponse> getUser(int userId) {
        final MutableLiveData<ReposResponse> data = new MutableLiveData<>();

        apiGit.getRepos(ApiGitHubApplication.getSharedPrefInstance().getUserName()).enqueue(new Callback <List<ReposResponse>>() {
            @Override
            public void onResponse(Call<List<ReposResponse>> call, Response<List<ReposResponse>> response) {
                List<ReposResponse> reposResponse = response.body();

            }

            @Override
            public void onFailure(Call<List<ReposResponse>> call, Throwable t) {

            }
        });
        return data;
    }

//    public LiveData<ReposResponse> getUser(String userId) {
//        refreshUser(userId);
//        // Returns a LiveData object directly from the database.
//        return userDao.load(userId);
//    }
//
//    private void refreshUser(final String userId) {
//        // Runs in a background thread.
//        executor.execute(() -> {
//            // Check if user data was fetched recently.
//            boolean userExists = userDao.hasUser(FRESH_TIMEOUT);
//            if (!userExists) {
//                // Refreshes the data.
//                Response<ReposResponse> response = apiGit.getUser(userId).execute();
//
//                // Check for errors here.
//
//                // Updates the database. The LiveData object automatically
//                // refreshes, so we don't need to do anything else here.
//                userDao.save(response.body());
//            }
//        });
//    }
}
