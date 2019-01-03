package com.gd.oshturniev.apigithub.following;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.gd.oshturniev.apigithub.net.ApiGit;
import com.gd.oshturniev.apigithub.room.LiveDataCallAdapterFactory;
import com.gd.oshturniev.apigithub.room.RoomDB;
import com.gd.oshturniev.apigithub.room.RoomDao;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {
    // expose Application as an injectable context
    @Binds
    abstract Context bindContext(Application application);
}


//@Module(includes = ViewModelModule.class)
//class AppModule {
//    @Singleton
//    @Provides
//    ApiGit provideGithubService() {
//        return new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
//                .build()
//                .create(ApiGit.class);
//    }
//
//    @Singleton @Provides
//    RoomDB provideDb(Application app) {
//        return Room.databaseBuilder(app, RoomDB.class,"github.db").build();
//    }
//
//    @Singleton @Provides
//    RoomDao provideUserDao(RoomDB db) {
//        return db.wordDao();
//    }
//
////    @Singleton @Provides
////    RepoDao provideRepoDao(RoomDB db) {
////        return db.repoDao();
////    }
//}
