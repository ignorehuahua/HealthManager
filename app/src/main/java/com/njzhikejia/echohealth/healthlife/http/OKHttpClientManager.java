package com.njzhikejia.echohealth.healthlife.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 16222 on 2018/6/9.
 */

public class OKHttpClientManager {

    private static OKHttpClientManager mInstance;
    private OkHttpClient mClient;
    private Gson mGson;
    private Handler mHandler;
    private static final String TAG = "OKHttpClientManager";


    public OKHttpClientManager() {
        mClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
        mGson = new Gson();
    }

    public static OKHttpClientManager getInstance() {
        if (mInstance == null) {
            synchronized (OKHttpClientManager.class) {
                if (mInstance == null) {
                    mInstance = new OKHttpClientManager();
                }
            }
        }
        return mInstance;
    }

    public void getAsync(Request request, Callback callback) {
        mClient.newCall(request).enqueue(callback);
    }

    public void postAsync(Request request, Callback callback) {
        mClient.newCall(request).enqueue(callback);
    }
}

