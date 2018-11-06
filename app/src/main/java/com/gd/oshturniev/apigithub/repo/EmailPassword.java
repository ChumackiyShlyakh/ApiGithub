package com.gd.oshturniev.apigithub.repo;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class EmailPassword {

    private static String email;
    private String password;

    public static final String USER_EMAIL = "";
    public static final String USER_PASSWORD = "";
    public static final String USER_LOGIN = "";
    public static final String MY_PREFS = "MyPrefsFile";


//    public void setEmail(String email) {
//        this.email = email;
//    }
//
////    public String getPassword() {
////        return password;
////    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
////    SharedPreferences sharedPreferences;

    Context context;

    public EmailPassword() {
        this.context = context;
    }

    public void saveLoginDetails(String email, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_EMAIL, email);
        editor.putString(USER_PASSWORD, password);
        editor.commit();
    }

    public String getEmail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_EMAIL, "");
    }

    public String getPassword() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_PASSWORD, "");
    }

    public boolean isUserLogedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("Email", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password", "").isEmpty();
        return isEmailEmpty || isPasswordEmpty;
    }
}