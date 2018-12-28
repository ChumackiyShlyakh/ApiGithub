package com.gd.oshturniev.apigithub.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;

@Database(entities = {ReposResponse.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {

    public abstract RoomDao wordDao();

    private static volatile RoomDB INCTANCE;

    public static RoomDB getDatabase(final Context context) {
        if(INCTANCE == null) {
            synchronized (RoomDB.class) {
                if(INCTANCE == null) {
                    // create database here
                    INCTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, "repo_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INCTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INCTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RoomDao roomDao;

        PopulateDbAsync(RoomDB db) {
            roomDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
//            roomDao.deleteAll();
            return null;
        }
    }
}
