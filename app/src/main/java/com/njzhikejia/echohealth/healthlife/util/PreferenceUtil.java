package com.njzhikejia.echohealth.healthlife.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

/**
 * Created by 16222 on 2018/7/22.
 */

public class PreferenceUtil {

    private static final String PREFERENCE_NAME = "healthlife_pref";
    private static final String KEY_UID = "key_uid";
    private static final String KEY_SEC_KEY = "key_sec_key";

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

    public static void putUID(Context context, int value) {
        putInt(context, KEY_UID, value);
    }

    public static int getUID(Context context) {
        return getInt(context, KEY_UID,0);
    }

    public static void putSecKey(Context context, String value) {
        putString(context, KEY_SEC_KEY, value);
    }

    public static String getSecKey(Context context, String defaultKey) {
        return getString(context, KEY_SEC_KEY, defaultKey);
    }
}
