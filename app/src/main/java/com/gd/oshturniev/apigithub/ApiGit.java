package com.gd.oshturniev.apigithub;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiGit {

    @GET("/user")
    Call<User> getUser(@Header("Authorization") String authorization);

    @GET("/user")
    Call<User> getLogin();
}
