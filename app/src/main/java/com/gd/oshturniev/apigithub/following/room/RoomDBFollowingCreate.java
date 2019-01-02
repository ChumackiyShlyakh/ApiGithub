package com.gd.oshturniev.apigithub.following.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.gd.oshturniev.apigithub.core.model.response.followers.FollowingResponse;

@Database(entities = {FollowingResponse.class}, version = 1)
public abstract class RoomDBFollowingCreate extends RoomDatabase{

    public abstract RoomDaoFollowing roomDaoFollowing();

    private static volatile RoomDBFollowingCreate INCTANCE;

    public static RoomDBFollowingCreate getDatabase(final Context context) {
        if (INCTANCE == null) {
            synchronized (RoomDBFollowingCreate.class) {
                if (INCTANCE == null) {
                    // create database here
                    INCTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDBFollowingCreate.class, "following_database").addCallback(sRoomDatabaseCallback).build();
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

        private final RoomDaoFollowing roomDaoFollowing;

        PopulateDbAsync(RoomDBFollowingCreate db) {
            roomDaoFollowing = db.roomDaoFollowing();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            return null;
        }
    }
}
