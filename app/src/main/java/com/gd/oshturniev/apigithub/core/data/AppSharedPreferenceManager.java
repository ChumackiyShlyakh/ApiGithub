package com.gd.oshturniev.apigithub.core.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.gd.oshturniev.apigithub.utils.Constants;

import static android.content.Context.MODE_PRIVATE;

public class AppSharedPreferenceManager {

    private static final String USER_EMAIL = "Email";
    private static final String USER_PASSWORD = "Password";
    private static final String IS_AUTH = "isAuth";
    private static final String MY_PREFS = "myPreferences";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AppSharedPreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveLoginDetails(String email, String password) {
        editor.putString(USER_EMAIL, email);
        editor.putString(USER_PASSWORD, password);
        editor.apply();
    }

    public void setAuthState(boolean isAuth) {
        editor.putBoolean(IS_AUTH, isAuth);
        editor.apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(USER_EMAIL, Constants.EMPTY);
    }

    public String getPassword() {
        return sharedPreferences.getString(USER_PASSWORD, Constants.EMPTY);
    }

    public boolean isAuth() {
        return sharedPreferences.getBoolean(IS_AUTH, false);
    }

    public void clearPrefs() {
        editor.clear();
        editor.commit();
    }
}