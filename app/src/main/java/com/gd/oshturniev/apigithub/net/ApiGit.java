package com.gd.oshturniev.apigithub.net;

import com.gd.oshturniev.apigithub.core.model.response.login.UserResponse;
import com.gd.oshturniev.apigithub.core.model.response.repos.ReposResponse;
import com.gd.oshturniev.apigithub.utils.ApiConstants;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiGit {

    @GET(ApiConstants.GITHUB_USER_AUTHENTICATION)
    Call<UserResponse> getUser();

    @DELETE(ApiConstants.GITHUB_USER_AUTHENTICATION_DELETE)
    Call<UserResponse> delUser();

    @GET(ApiConstants.GITHUB_USER_REPOSITORIES)
    Call<ReposResponse> getRepos(@Path("owner") String owner);
}
