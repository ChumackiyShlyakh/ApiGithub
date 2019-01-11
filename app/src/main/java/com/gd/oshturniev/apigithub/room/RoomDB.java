package com.gd.oshturniev.apigithub.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.gd.oshturniev.apigithub.core.model.response.followers.FollowingResponse;
import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;

@Database(entities = {ReposResponse.class, FollowingResponse.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {

    public abstract RoomDao roomDao();
    public abstract RoomDaoFollowing roomDaoFollowing();

    private static volatile RoomDB INCTANCE;

    public static RoomDB getDatabase(final Context context) {
        if (INCTANCE == null) {
            synchronized (RoomDB.class) {
                if (INCTANCE == null) {
                    // create database here
                    INCTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, "repo_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INCTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INCTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RoomDao roomDao;
        private final RoomDaoFollowing roomDaoFollowing;

        PopulateDbAsync(RoomDB db) {
            roomDao = db.roomDao();
            roomDaoFollowing = db.roomDaoFollowing();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            return null;
        }
    }
}
