package com.gd.oshturniev.apigithub.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;

import java.util.List;

public class RoomDBRepository {

    private RoomDao roomDao;
    private LiveData<List<ReposResponse>> allRepos;

    public RoomDBRepository(Application application) {
        RoomDB db = RoomDB.getDatabase(application);
        this.roomDao = db.wordDao();
        allRepos = roomDao.getAllRepos();
    }

    public void insert(List<ReposResponse> repositories) {
        new InsertAsyncTask(roomDao).execute(repositories);
    }

   public LiveData<List<ReposResponse>> getAllRepos() {
        return allRepos;
    }

    public void delete() {
        roomDao.deleteAll();
    }

    private static class InsertAsyncTask extends AsyncTask<List<ReposResponse>, Void, Void> {

        private RoomDao asyncTaskDao;

        InsertAsyncTask(RoomDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<ReposResponse>... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
