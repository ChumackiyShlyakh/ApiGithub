package com.gd.oshturniev.apigithub.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;

import java.util.List;

@Dao
public interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ReposResponse> repositories);

    @Query("DELETE FROM repo_table")
    void deleteAll();

    @Query("SELECT * from repo_table")
    LiveData<List<ReposResponse>> getAllRepos();
}
