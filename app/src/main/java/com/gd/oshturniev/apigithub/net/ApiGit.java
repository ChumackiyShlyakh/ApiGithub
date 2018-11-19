package com.gd.oshturniev.apigithub.net;

import com.gd.oshturniev.apigithub.core.model.response.User;
import com.gd.oshturniev.apigithub.utils.ApiConstants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiGit {

    @GET(ApiConstants.GITHUB_USER_AUTHENTICATION)
    Call<User> getUser();
}
