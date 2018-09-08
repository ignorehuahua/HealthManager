package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.adapter.ViewPagerAdapter;
import com.njzhikejia.echohealth.healthlife.entity.UserDetailsResponse;
import com.njzhikejia.echohealth.healthlife.fragment.UserBaseInfoFragment;
import com.njzhikejia.echohealth.healthlife.fragment.UserHealthInfoFragment;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NetWorkUtils;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 16222 on 2018/7/22.
 */

public class UserDetailsActivity extends BaseActivity {

    private static final String TAG = "UserDetailsActivity";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] titles;
    private Toolbar mToolbar;
    private UserDetailsResponse userDetailsResponse;
    private UserBaseInfoFragment userBaseInfoFragment;
    private UserHealthInfoFragment userHealthInfoFragment;
    private UserDetailsHandler mHandler;
    private static final String KEY_USER_DETAILS = "key_user_details";
    private ProgressBar mProgressBar;
    private static final int NET_ERROR = 26;
    private TextView tvQRCard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Logger.d(TAG, "onCreate");
        initView();
        mProgressBar.setVisibility(View.VISIBLE);
        queryUserInfo();
        mHandler = new UserDetailsHandler(this);
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mProgressBar = findViewById(R.id.progress_bar);
        tvQRCard = findViewById(R.id.tv_qr_card);
        tvQRCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentQRCode = new Intent(UserDetailsActivity.this, QRCodeActivity.class);
                startActivity(intentQRCode);
            }
        });
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        titles = new String[]{getString(R.string.user_base_info), getString(R.string.user_health_info)};
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles);
        userBaseInfoFragment = new UserBaseInfoFragment();
        userHealthInfoFragment = new UserHealthInfoFragment();
        adapter.addFragment(userBaseInfoFragment);
        adapter.addFragment(userHealthInfoFragment);
        viewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void queryUserInfo() {
        if (!NetWorkUtils.isNetworkConnected(this)) {
            ToastUtil.showShortToast(this, R.string.net_work_error);
            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.GONE);
            }
            return;
        }
        OKHttpClientManager.getInstance().getAsync(CommonRequest.getUserDetailsRequest(PreferenceUtil.getLoginUserUID(UserDetailsActivity.this)), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "onFailure call = "+call.toString());
                mHandler.sendEmptyMessage(NET_ERROR);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Logger.d(TAG, "getUserDetails onResponse code = "+response.code());
                String resonseContent = response.body().string();
                Logger.d(TAG, "responseContent = "+resonseContent);
                Gson gson = new Gson();
                userDetailsResponse = gson.fromJson(resonseContent, UserDetailsResponse.class);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        userBaseInfoFragment.initData();
//                        userHealthInfoFragment.initData();
//                    }
//                });
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putParcelable(KEY_USER_DETAILS, userDetailsResponse);
                message.setData(bundle);
                mHandler.sendMessage(message);
            }
        });
    }

    class UserDetailsHandler extends Handler{

        private WeakReference<UserDetailsActivity> weakReference;

        public UserDetailsHandler(UserDetailsActivity activity) {
            weakReference = new WeakReference<UserDetailsActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == NET_ERROR) {
                mProgressBar.setVisibility(View.GONE);
                ToastUtil.showShortToast(UserDetailsActivity.this, R.string.net_work_error);
            }
           Bundle bundle = msg.getData();
            if (bundle != null) {
                if (weakReference.get() != null) {
                    UserDetailsResponse userDetailsResponse = bundle.getParcelable(KEY_USER_DETAILS);
                    mProgressBar.setVisibility(View.GONE);
                    userBaseInfoFragment.initData(userDetailsResponse);
                    userHealthInfoFragment.initData(userDetailsResponse);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG,"onResume");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
