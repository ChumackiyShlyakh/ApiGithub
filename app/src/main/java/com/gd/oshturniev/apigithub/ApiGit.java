package com.gd.oshturniev.apigithub;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiGit {

    @GET("/user")
    Call<String> getUser(@Header("Authorization") String authorization);

    @GET("/user")
    Call<User> getLogin();
}
