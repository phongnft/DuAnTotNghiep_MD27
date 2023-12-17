package com.example.duantotnghiep_md27.permission;


import android.content.Context;
import android.content.SharedPreferences;

import okhttp3.internal.cache.DiskLruCache;

public class LocalStorage {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String KEY_FIREBASE_TOKEN = "firebaseToken";
    public static final String KEY_USER = "User";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    private static final String IS_USER_LOGIN_GG = "IsUserLoggedInGoogle";


    public LocalStorage(Context context) {
        sharedPreferences = context.getSharedPreferences("Preferences", 0);
    }

    public String getUserLogin() {
        return sharedPreferences.getString(KEY_USER, "");
    }

    public void createUserLoginSession(String user) {
        editor = sharedPreferences.edit();
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_USER, user);
        editor.commit();
    }

    public void createUserLoginGoogle(String user) {
        editor = sharedPreferences.edit();
        editor.putBoolean(IS_USER_LOGIN_GG, true);
        editor.putString(KEY_USER, user);
        editor.commit();
    }

    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(IS_USER_LOGIN, false);
    }

    public boolean isUserLoggedInGoogle() {
        return sharedPreferences.getBoolean(IS_USER_LOGIN_GG, false);
    }

    public void logoutUser() {
        editor = sharedPreferences.edit();
        editor.remove(KEY_USER);
//        editor.remove(KEY_USER_ADDRESS);
        editor.remove(IS_USER_LOGIN);
        editor.remove(IS_USER_LOGIN_GG);
//        editor.remove(CART);
//        editor.remove(ORDER);
        editor.commit();
    }


    public String getFirebaseToken() {
        return sharedPreferences.getString(KEY_FIREBASE_TOKEN, null);
    }

}
