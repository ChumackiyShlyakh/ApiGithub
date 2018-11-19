package com.gd.oshturniev.apigithub.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.gd.oshturniev.apigithub.utils.Constants;

import static android.content.Context.MODE_PRIVATE;

public class AppSharedPreferenceManager {

    public static final String USER_EMAIL = "Email";
    public static final String USER_PASSWORD = "Password";
    public static final String MY_PREFS = "myPreferences";

    private SharedPreferences sharedPreferences;
    private Context context;
    private SharedPreferences.Editor editor;

    public AppSharedPreferenceManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveLoginDetails(String email, String password) {
        editor.putString(USER_EMAIL, email);
        editor.putString(USER_PASSWORD, password);
        editor.apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(USER_EMAIL, Constants.EMPTY);
    }

    public String getPassword() {
        return sharedPreferences.getString(USER_PASSWORD, Constants.EMPTY);
    }

    public boolean isAuth() {
        boolean isEmailEmpty = sharedPreferences.getString(USER_EMAIL, Constants.EMPTY).isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString(USER_PASSWORD, Constants.EMPTY).isEmpty();
        return !(isEmailEmpty || isPasswordEmpty);
    }
}