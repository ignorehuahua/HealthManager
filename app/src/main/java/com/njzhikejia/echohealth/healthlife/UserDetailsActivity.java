package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.adapter.ViewPagerAdapter;
import com.njzhikejia.echohealth.healthlife.entity.concern.Concerns;
import com.njzhikejia.echohealth.healthlife.entity.user.Extend;
import com.njzhikejia.echohealth.healthlife.entity.user.User;
import com.njzhikejia.echohealth.healthlife.entity.user.UserDetailsResponse;
import com.njzhikejia.echohealth.healthlife.fragment.UserBaseInfoFragment;
import com.njzhikejia.echohealth.healthlife.fragment.UserHealthInfoFragment;
import com.njzhikejia.echohealth.healthlife.greendao.DaoSession;
import com.njzhikejia.echohealth.healthlife.greendao.ExtendDao;
import com.njzhikejia.echohealth.healthlife.greendao.UserDao;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NetWorkUtils;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

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
    private UserBaseInfoFragment userBaseInfoFragment;
    private UserHealthInfoFragment userHealthInfoFragment;
    private UserDetailsHandler mHandler;
    public static final String KEY_USER_DETAILS = "key_user_details";
    private ProgressBar mProgressBar;
    private static final int NET_ERROR = 26;
    private static final int LOAD_SUCCESS = 27;
    private TextView tvQRCard;
    private DaoSession mDaoSession;
    private UserDao userDao;
    private ExtendDao extendDao;
    private UserDetailsResponse userDetailsResponse;
    public static final String USER_QR_CODE = "user_qr_code";
    private Concerns user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Logger.d(TAG, "onCreate");
        initDaoSession();
        initView();
        mProgressBar.setVisibility(View.VISIBLE);
        mHandler = new UserDetailsHandler(this);
        queryUserInfo();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mProgressBar = findViewById(R.id.progress_bar);
        tvQRCard = findViewById(R.id.tv_qr_card);
        Intent getIntent = getIntent();
        if (getIntent.hasExtra(ConstantValues.KEY_USER_DETAILS)) {
            if (getIntent.getParcelableExtra(ConstantValues.KEY_USER_DETAILS) != null) {
                user = getIntent.getParcelableExtra(ConstantValues.KEY_USER_DETAILS);
            }
            tvQRCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentQRCode = new Intent(UserDetailsActivity.this, QRCodeActivity.class);
                    intentQRCode.putExtra(USER_QR_CODE, user);
                    startActivity(intentQRCode);
                }
            });
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mTabLayout = findViewById(R.id.tab_layout);
            mViewPager = findViewById(R.id.viewpager);
            setupViewPager(mViewPager);
        }
    }

    public UserDetailsResponse getUserDetailsResponse() {
        return userDetailsResponse;
    }

    public void setUserDetailsResponse(UserDetailsResponse userDetailsResponse) {
        this.userDetailsResponse = userDetailsResponse;
    }

    private void initDaoSession() {
        HealthLifeApplication mApplication = (HealthLifeApplication) getApplication();
        mDaoSession = mApplication.getDaoSession();
        userDao = mDaoSession.getUserDao();
        extendDao = mDaoSession.getExtendDao();
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
            loadDataFromDb();
            return;
        }
        int userId = user.getUid();
        OKHttpClientManager.getInstance().getAsync(CommonRequest.getUserDetailsRequest(userId), new Callback() {
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
                setUserDetailsResponse(userDetailsResponse);
                if (userDetailsResponse != null) {
                    userDao.deleteAll();
                    userDao.insert(userDetailsResponse.getData().getUser());
                    extendDao.deleteAll();
                    extendDao.insert(userDetailsResponse.getData().getUser().getExtend());
                    Intent intent = new Intent(ConstantValues.ACTION_LOAD_USER_DETAILS);
                    intent.putExtra(KEY_USER_DETAILS, userDetailsResponse);
                    LocalBroadcastManager.getInstance(UserDetailsActivity.this).sendBroadcast(intent);
                    mHandler.sendEmptyMessage(LOAD_SUCCESS);
                }

            }
        });
    }

    private void loadDataFromDb() {
        List<User> userList = userDao.loadAll();
        List<Extend> extendList = extendDao.loadAll();
        Logger.d(TAG, "userList.size = "+userList.size()+ "extendList.size = "+extendList.size());
        if (userList.size() == 0 || extendList.size() == 0) {
            return;
        }
        User user = new User();
        Extend extend = new Extend();
        if (userList != null && userList.size() > 0) {
            user = userList.get(0);
        }
        if (extendList != null && extendList.size() > 0) {
            extend = extendList.get(0);
        }
        user.setExtend(extend);
        UserDetailsResponse response = new UserDetailsResponse();
        UserDetailsResponse.ResponseData responseData = new UserDetailsResponse.ResponseData();
        responseData.setUser(user);
        response.setData(responseData);
        setUserDetailsResponse(response);

//        userBaseInfoFragment.initData(response);
//        userHealthInfoFragment.initData(response);
        Intent intent = new Intent(ConstantValues.ACTION_LOAD_USER_DETAILS);
        intent.putExtra(KEY_USER_DETAILS, response);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
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
                loadDataFromDb();
            } else if (msg.what == LOAD_SUCCESS) {
                mProgressBar.setVisibility(View.GONE);
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG,"onResume");
    }

}
