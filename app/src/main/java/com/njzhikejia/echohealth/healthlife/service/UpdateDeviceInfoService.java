package com.njzhikejia.echohealth.healthlife.service;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class UpdateDeviceInfoService extends JobService {

    private static final String TAG = "UpdateDeviceInfoService";

    @Override
    public boolean onStartJob(JobParameters params) {
        postDeviceInfo(params);
       return true;

    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


    /**
     * 更新设备信息到server
     */
    private void postDeviceInfo(final JobParameters params) {
        OKHttpClientManager.getInstance().postAsync(CommonRequest.postDeviceInfoRequest(PreferenceUtil.getLoginUserUID(getApplicationContext()),
                PreferenceUtil.getDeviceToken(this), 1), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "post device info failure!!!!");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                if (response.code() == 200) {
                    jobFinished(params, false);
                }
                Logger.d(TAG, "onResponse code = "+response.code() + "content = "+responseContent);
            }
        });
    }
}