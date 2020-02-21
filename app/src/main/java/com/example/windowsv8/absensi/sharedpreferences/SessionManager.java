package com.example.windowsv8.absensi.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.windowsv8.absensi.ui.MainActivity;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    Boolean session = false;
    public static final String shared_preference_karyawan = "shared_preference_karyawan";
    public static final String TAG_SESSION = "session_status";
    public static final String TAG_ID = "id_karyawan";
    public static final String TAG_NAME = "name";
    public static final String TAG_IMAGE = "image";
    public static final String TAG_POSITION = "position";
    public static final String TAG_IMEI = "imei";
    public static final String TAG_CONDITION = "condition";
    SharedPreferences.Editor editor;
    private Context context;
//    public static final String TAG_TOKEN = "token";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(shared_preference_karyawan, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void createSession(String id_karyawan, String name, String image, String position, String imei, String condition){
        editor.putBoolean(TAG_SESSION, true);
        editor.putString(TAG_ID, id_karyawan);
        editor.putString(TAG_NAME, name);
        editor.putString(TAG_IMAGE, image);
        editor.putString(TAG_POSITION, position);
        editor.putString(TAG_IMEI, imei);
        editor.putString(TAG_CONDITION, condition);
        editor.commit();
    }

    public void checkUser(){
        session = sharedPreferences.getBoolean(TAG_SESSION, false);
        if (session){
            context.startActivity(new Intent(context, MainActivity.class));
            ((Activity)context).finish();
        }
    }

    public void closeSession(){
        editor.clear();
        editor.commit();
    }

    public HashMap<String, String> getDataUser(){
        HashMap<String, String> userData = new HashMap<>();
        userData.put(TAG_ID, sharedPreferences.getString(TAG_ID, null));
        userData.put(TAG_NAME, sharedPreferences.getString(TAG_NAME, null));
        userData.put(TAG_IMAGE, sharedPreferences.getString(TAG_IMAGE, null));
        userData.put(TAG_POSITION, sharedPreferences.getString(TAG_POSITION, null));
        userData.put(TAG_IMEI, sharedPreferences.getString(TAG_IMEI, null));
        userData.put(TAG_CONDITION, sharedPreferences.getString(TAG_CONDITION, null));
        return userData;
    }

    private boolean isLoggedIn() {
        return session = sharedPreferences.getBoolean(TAG_SESSION, false);
    }

    public void updateSession(String condition){
        editor.putString(TAG_CONDITION, condition);
        editor.apply();
    }
}

