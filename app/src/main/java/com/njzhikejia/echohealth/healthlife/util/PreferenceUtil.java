package com.njzhikejia.echohealth.healthlife.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 16222 on 2018/7/22.
 */

public class PreferenceUtil {

    private static final String PREFERENCE_NAME = "healthlife_pref";
    private static final String KEY_LOGIN_UID = "key_login_uid";
    private static final String KEY_SELECTED_USER_UID = "key_selected_uid";
    private static final String KEY_SEC_KEY = "key_sec_key";
    private static final String KEY_LOGIN_USER_PHONE = "key_login_phone";
    private static final String KEY_LOGIN_USER_NAME = "key_login_name";
    private static final String KEY_LOGIN_USER_PWD = "key_user_pwd";
    private static final String KEY_DEVICE_TOKEN = "key_device_token";
    private static final String KEY_NEW_CONCERN = "key_new_concern";

    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static void putLoginUserUID(Context context, int value) {
        putInt(context, KEY_LOGIN_UID, value);
    }

    public static int getLoginUserUID(Context context) {
        return getInt(context, KEY_LOGIN_UID,0);
    }

    public static void putSelectedUserUID(Context context, int value) {
        putInt(context, KEY_SELECTED_USER_UID, value);
    }

    public static int getSelectedUserUID(Context context) {
        return getInt(context, KEY_SELECTED_USER_UID,0);
    }

    public static void putSecKey(Context context, String value) {
        putString(context, KEY_SEC_KEY, value);
    }

    public static String getSecKey(Context context, String defaultKey) {
        return getString(context, KEY_SEC_KEY, defaultKey);
    }

    public static void putLoginUserPhone(Context context, String value) {
        putString(context, KEY_LOGIN_USER_PHONE, value);
    }

    public static String getLoginUserPhone(Context context) {
        return getString(context, KEY_LOGIN_USER_PHONE, "");
    }

    public static void putLoginUserName(Context context, String value) {
        putString(context, KEY_LOGIN_USER_NAME, value);
    }

    public static String getLoginUserName(Context context) {
        return getString(context, KEY_LOGIN_USER_NAME, "");
    }

    public static void putLoginUserPwd(Context context, String value) {
        putString(context, KEY_LOGIN_USER_PWD, value);
    }

    public static String getLoginUserPwd(Context context) {
        return getString(context, KEY_LOGIN_USER_PWD, "");
    }

    public static void putDeviceToken(Context context, String value) {
        putString(context, KEY_DEVICE_TOKEN, value);
    }

    public static String getDeviceToken(Context context) {
        return getString(context, KEY_DEVICE_TOKEN, "");

    }

    public static void setNewConcern(Context concext, boolean value) {
        putBoolean(concext, KEY_NEW_CONCERN, value);
    }

    public static Boolean getNewConcern(Context context) {
        return getBoolean(context, KEY_NEW_CONCERN, false);
    }

    // 清除缓存数据
    public static void clear(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
