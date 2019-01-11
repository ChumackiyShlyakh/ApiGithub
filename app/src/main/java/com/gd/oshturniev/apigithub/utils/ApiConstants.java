package com.gd.oshturniev.apigithub.utils;

public class ApiConstants {

    // BASE URL
    public static final String BASE_GITHUB_URL = "https://api.github.com";

    // AUTHENTICATION
    public static final String GITHUB_USER_AUTHENTICATION = "/user";

    // AUTHENTICATION DELETE
    public static final String GITHUB_USER_AUTHENTICATION_DELETE = "/authorizations";

    // ENDPOINTS
    public static final String GITHUB_FOLLOWING = "users/{owner}/following";
    public static final String GITHUB_USER_REPOSITORIES = "users/{owner}/repos";

    // DEBUG
    public static final boolean isDebugging = true;
}
