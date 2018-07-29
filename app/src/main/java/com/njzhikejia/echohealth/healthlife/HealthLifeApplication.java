package com.njzhikejia.echohealth.healthlife;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.njzhikejia.echohealth.healthlife.service.LoopService;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;

/**
 * Created by 16222 on 2018/6/9.
 */

public class HealthLifeApplication extends Application{

    public static boolean isMultiUser = false;

    @Override
    public void onCreate() {
        super.onCreate();
        if (ConstantValues.MULTI_USER_MODE.equals(BuildConfig.FLAVOR)) {
            isMultiUser = true;
        }
        SDKInitializer.initialize(getApplicationContext());
//        LoopService.startPollingService(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        LoopService.stopPollingService(getApplicationContext());
    }
}
