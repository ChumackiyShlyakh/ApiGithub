package com.gd.oshturniev.apigithub;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class FragmentHelper {

//    public static void replaceFragments(Class fragmentClass, Context context) {
//        Fragment fragment = null;
//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // Insert the fragment by replacing any existing fragment
//        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
//                .commit();
//    }

    public static FragmentManager getFragmentManager(Context context) {
        return ((AppCompatActivity) context).getSupportFragmentManager();
    }

    public static void openFragment(Context context, int frameId, Fragment fragment) {
        getFragmentManager(context).beginTransaction()
                .replace(frameId, fragment, fragment.getClass().toString())
                .addToBackStack(null).commit();
    }

    public static void addFragment(Context context, int frameId, Fragment fragment) {
        getFragmentManager(context).beginTransaction()
                .add(frameId, fragment, fragment.getClass().toString())
                .addToBackStack(null).commit();
    }
}
