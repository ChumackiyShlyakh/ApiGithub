package com.gd.oshturniev.apigithub.utils;

public class ApiConstants {

    // BASE URL
    public static final String BASE_GITHUB_URL = "https://api.github.com";

    // AUTHENTICATION
    public static final String GITHUB_USER_AUTHENTICATION = "/user";

    // ENDPOINTS
    public static final String GITHUB_USER_ENDPOINT = "users/{owner}";
    public static final String GITHUB_FOLLOWERS_ENDPOINT = "users/{owner}/followers";

    // DEBUG
    public static final boolean isDebugging = true;
}
