package com.njzhikejia.echohealth.healthlife;

import android.app.Application;

import com.njzhikejia.echohealth.healthlife.service.LoopService;

/**
 * Created by 16222 on 2018/6/9.
 */

public class HealthLifeApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        LoopService.startPollingService(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        LoopService.stopPollingService(getApplicationContext());
    }
}
