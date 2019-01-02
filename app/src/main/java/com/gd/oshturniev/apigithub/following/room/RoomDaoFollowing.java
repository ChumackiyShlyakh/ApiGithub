package com.gd.oshturniev.apigithub.following.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gd.oshturniev.apigithub.core.model.response.followers.FollowingResponse;

import java.util.List;

@Dao
public interface RoomDaoFollowing {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<FollowingResponse> followingResponses);

    @Query("DELETE FROM following_table")
    void deleteAll();

    @Query("SELECT * from following_table")
    LiveData<List<FollowingResponse>> getAllFollowing();
}
