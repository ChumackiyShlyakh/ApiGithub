package com.gd.oshturniev.apigithub.following.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.gd.oshturniev.apigithub.core.model.response.followers.FollowingResponse;

import java.util.List;

public class RoomDBFollowing {

    private RoomDaoFollowing roomDaoFollowing;
    private LiveData<List<FollowingResponse>> allFollowing;

    public RoomDBFollowing(Application application) {
        RoomDBFollowingCreate db = RoomDBFollowingCreate.getDatabase(application);
        this.roomDaoFollowing = db.roomDaoFollowing();
        allFollowing = roomDaoFollowing.getAllFollowing();
    }

    public void insert(List<FollowingResponse> repositories) {
        new InsertAsyncTask(roomDaoFollowing).execute(repositories);
    }

    public LiveData<List<FollowingResponse>> getAllFollowing() {
        return allFollowing;
    }

    public void deleteAll()  {
        new DeleteAllWordsAsyncTask(roomDaoFollowing).execute();
    }

    private static class InsertAsyncTask extends AsyncTask<List<FollowingResponse>, Void, Void> {

        private RoomDaoFollowing asyncTaskDao;

        InsertAsyncTask(RoomDaoFollowing dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<FollowingResponse>... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {

        private RoomDaoFollowing asyncTaskDao;

        DeleteAllWordsAsyncTask(RoomDaoFollowing dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncTaskDao.deleteAll();
            return null;
        }
    }





}
