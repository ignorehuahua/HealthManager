package com.njzhikejia.echohealth.healthlife;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.Notification;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.njzhikejia.echohealth.healthlife.entity.Message;
import com.njzhikejia.echohealth.healthlife.greendao.DaoMaster;
import com.njzhikejia.echohealth.healthlife.greendao.DaoSession;
import com.njzhikejia.echohealth.healthlife.greendao.MessageDao;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.service.LoopService;
import com.njzhikejia.echohealth.healthlife.service.UpdateDeviceInfoService;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 16222 on 2018/6/9.
 */

public class HealthLifeApplication extends Application{

    public static boolean isMultiUser = false;
    private static final String TAG = "HealthLifeApplication";
    public static final String UPDATE_STATUS_ACTION = "com.umeng.message.example.action.UPDATE_STATUS";
    private Handler handler;
    private static final String DB_NAME = "healthlife.db";
    private DaoSession daoSession;
    private String deviceToken;
    public static final String KEY_JUMP_TO_FOLLOW_ME = "jump_to_follow_me";

    @Override
    public void onCreate() {
        super.onCreate();
        if (ConstantValues.MULTI_USER_MODE.equals(BuildConfig.FLAVOR)) {
            isMultiUser = true;
        }
        SDKInitializer.initialize(getApplicationContext());
        initUmengConfig();
        initGreendao();
//        LoopService.startPollingService(getApplicationContext());
    }

    /**
     * 初始化GreenDao
     */
    private void initGreendao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    /**
     * 获取DaoSession
     * @return
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 初始化友盟
     */
    private void initUmengConfig() {
        Logger.d(TAG, "initUmengConfig");
        handler = new Handler(getMainLooper());
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数3:Push推送业务的secret
         */
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "47dee1bd3a3f8f2cd4963e79e3092216");
        PushAgent mPushAgent = PushAgent.getInstance(this);
        deviceToken = mPushAgent.getRegistrationId();
        PreferenceUtil.putDeviceToken(getApplicationContext(), deviceToken);
        postDeviceInfo();
        UmengMessageHandler messageHandler = new UmengMessageHandler() {

            /**
             * 通知的回调方法（通知送达时会回调）
             */
            @Override
            public void dealWithNotificationMessage(Context context, UMessage msg) {
                //调用super，会展示通知，不调用super，则不展示通知。
                super.dealWithNotificationMessage(context, msg);
                Logger.d(TAG, "dealWithNotificationMessage title = "+msg.title + "content = "+msg.text);
                if (msg.title.equals(getString(R.string.apply_concern))) {
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent(ConstantValues.ACTION_CONCERN_REQUEST_RECEIVED));
                    PreferenceUtil.setNewConcern(getApplicationContext(), true);
                    return;
                }
                MessageDao messageDao = daoSession.getMessageDao();
                Message message = new Message(msg.title, msg.text);
                messageDao.insert(message);
            }

            /**
             * 自定义消息的回调方法
             */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                Logger.d(TAG, "dealWithCustomMessage");

                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        // 对自定义消息的处理方式，点击或者忽略
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                        }
                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                });
            }

            /**
             * 自定义通知栏样式的回调方法
             */
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                Logger.d(TAG, "getNotification msg.build_id = "+msg.builder_id);
                switch (msg.builder_id) {
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
                                R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
                                getSmallIconId(context, msg));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true);

                        return builder.getNotification();
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };

        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

            @Override
            public void launchApp(Context context, UMessage msg) {
                Logger.d(TAG, "launchApp");
                if (msg.title.equals(getString(R.string.apply_concern))) {
                    Intent intent = new Intent();
                    intent.setClassName("com.njzhikejia.echohealth.healthlife", "com.njzhikejia.echohealth.healthlife.MemberManageActivity");
                    intent.putExtra(KEY_JUMP_TO_FOLLOW_ME, 1);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    super.launchApp(context, msg);
                }


            }

            @Override
            public void openUrl(Context context, UMessage msg) {
                super.openUrl(context, msg);
                Logger.d(TAG, "openUrl");
            }

            @Override
            public void openActivity(Context context, UMessage msg) {
                super.openActivity(context, msg);
                Logger.d(TAG, "openActivity");
            }

            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                Logger.d(TAG, "dealWithCustomAction");
            }
        };
        //使用自定义的NotificationHandler
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Logger.d(TAG, "Umeng registerCallback onSuccess! token = "+deviceToken);
        }

            @Override
            public void onFailure(String s, String s1) {
                Logger.e(TAG, "Umeng registerCallback onFailure! s = "+ s + "s1 = "+ s1);
            }
        });
    }

    /**
     * 更新设备信息到server
     */
    private void postDeviceInfo() {
        OKHttpClientManager.getInstance().postAsync(CommonRequest.postDeviceInfoRequest(PreferenceUtil.getLoginUserUID(getApplicationContext()),
                deviceToken, 1), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "post device info failure!!!!");
                startUdateDeviceInfoService();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();

                Logger.d(TAG, "onResponse code = "+response.code() + "content = "+responseContent);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startUdateDeviceInfoService() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(this, UpdateDeviceInfoService.class));
        builder.setMinimumLatency(10 * 1000);
        builder.setOverrideDeadline(30 * 1000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        jobScheduler.schedule(builder.build());
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        LoopService.stopPollingService(getApplicationContext());
    }
}
