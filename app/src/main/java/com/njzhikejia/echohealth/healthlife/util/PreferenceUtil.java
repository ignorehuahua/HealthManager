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
        editor.apply();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
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
}
