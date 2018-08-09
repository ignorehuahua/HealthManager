package com.njzhikejia.echohealth.healthlife.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ToastUtil {

    private static final String TAG = "ToastUtil";
    private static Toast mToast;

    private ToastUtil() {
        Log.d(TAG, "should not construct outside");
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public static void showShortToast(Context context, int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(resId);
        }
        mToast.show();
    }

    public static void showLongToast(Context context, int resId) {
        if (mToast == null) {
            mToast = Toast.makeText(context, resId, Toast.LENGTH_LONG);
        } else {
            mToast.setText(resId);
        }
        mToast.show();
    }

}