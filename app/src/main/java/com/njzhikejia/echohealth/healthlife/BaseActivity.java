package com.njzhikejia.echohealth.healthlife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.umeng.message.PushAgent;

import static anet.channel.util.Utils.context;

/**
 * Created by 16222 on 2018/8/26.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(context).onAppStart();
    }
}
