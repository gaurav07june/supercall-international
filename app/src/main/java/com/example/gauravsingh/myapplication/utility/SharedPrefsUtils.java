package com.example.gauravsingh.myapplication.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SharedPrefsUtils {

    public static boolean getSharedPrefBoolean(Context pContext, String pFileName, String pKey, boolean pDefault) {
        SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
        return _sharedPref.getBoolean(pKey, pDefault);
    }

    public static void setSharedPrefBoolean(Context pContext, String pFileName, String pKey, boolean pValue) {
        SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
        Editor _editor = _sharedPref.edit();
        _editor.putBoolean(pKey, pValue);
        _editor.apply();
    }


    public static int getSharedPrefInt(Context pContext, String pFileName, String pKey, int pDefault) {
        SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
        return _sharedPref.getInt(pKey, pDefault);
    }


    public static void setSharedPrefInt(Context pContext, String pFileName, String pKey, int pValue) {
        SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
        Editor _editor = _sharedPref.edit();
        _editor.putInt(pKey, pValue);
        _editor.apply();
    }


    public static String getSharedPrefString(Context pContext, String pFileName, String pKey, String pDefault) {
        SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
        return _sharedPref.getString(pKey, pDefault);
    }


    public static void setSharedPrefString(Context pContext, String pFileName, String pKey, String pValue) {
        SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
        Editor _editor = _sharedPref.edit();
        _editor.putString(pKey, pValue);
        _editor.apply();
    }

    /*public static void setRegisteredUserPref(Context pContext, String pFileName, String pKey,
                                             RegisteredUserDetail pObject){
        if (pObject != null){
            SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
            Editor _editor = _sharedPref.edit();
            Gson gson = new Gson();
            String json = gson.toJson(pObject);
            _editor.putString(pKey, json);
            _editor.apply();
        }

    }
    public static RegisteredUserDetail getRegisteredUserPref(Context pContext, String pFileName, String pKey){
        SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = _sharedPref.getString(pKey, "");
        RegisteredUserDetail registeredUserDetail = gson.fromJson(json, RegisteredUserDetail.class);
        return registeredUserDetail;
    }*/

    public static long getSharedPrefLong(Context pContext, String pFileName, String pKey, long pDefault) {
        SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
        return _sharedPref.getLong(pKey, pDefault);
    }

    public static void setSharedPrefLong(Context pContext, String pFileName, String pKey, long pValue) {
        SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
        Editor _editor = _sharedPref.edit();
        _editor.putLong(pKey, pValue);
        _editor.apply();
    }


    public static float getSharedPrefFloat(Context pContext, String pFileName, String pKey, float pDefault) {
        SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
        return _sharedPref.getFloat(pKey, pDefault);
    }


    public static void setSharedPrefFloat(Context pContext, String pFileName, String pKey, float pValue) {
        SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
        Editor _editor = _sharedPref.edit();
        _editor.putFloat(pKey, pValue);
        _editor.apply();
    }
    public static void clearSharedPref(Context pContext, String pFileName){
        SharedPreferences _sharedPref = pContext.getSharedPreferences(pFileName, Context.MODE_PRIVATE);
        Editor _editor = _sharedPref.edit();
        _editor.clear();
        _editor.apply();
    }

}
