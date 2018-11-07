package com.gd.oshturniev.apigithub.auth;

import com.gd.oshturniev.apigithub.User;
import com.gd.oshturniev.apigithub.utils.ApiConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiGit {

    @GET(ApiConstants.GITHUB_USER_AUTHENTICATION)
    Call<User> getUser();
}
