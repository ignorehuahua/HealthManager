package com.njzhikejia.echohealth.healthlife.util;

import android.util.Log;

/**
 * Created by 16222 on 2018/5/27.
 */

public class Logger {

    private static final String TAG = "healthlife";

    public static void v (String tag, String msg) {
        Log.v(TAG+tag, msg);
    }

    public static void d (String tag, String msg) {
        Log.d(TAG+tag, msg);
    }

    public static void i (String tag, String msg) {
        Log.d(TAG+tag, msg);
    }

    public static void w (String tag, String msg) {
        Log.d(TAG+tag, msg);
    }

    public static void e (String tag, String msg) {
        Log.d(TAG+tag, msg);
    }
}
