package com.njzhikejia.echohealth.healthlife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.adapter.ViewPagerAdapter;
import com.njzhikejia.echohealth.healthlife.entity.UserDetailsResponse;
import com.njzhikejia.echohealth.healthlife.fragment.BaseFragment;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 16222 on 2018/7/22.
 */

public class UserDetailsActivity extends AppCompatActivity {

    private static final String TAG = "UserDetailsActivity";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] titles;
    private Toolbar mToolbar;
    private UserDetailsResponse userDetailsResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Logger.d(TAG, "onCreate");
        initView();
        queryUserInfo();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        titles = new String[]{getString(R.string.user_base_info), getString(R.string.user_health_info)};
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles);
        adapter.addFragment(BaseFragment.newInstance(this.getString(R.string.user_base_info)));
        adapter.addFragment(BaseFragment.newInstance(this.getString(R.string.user_health_info)));
        viewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void queryUserInfo() {
        OKHttpClientManager.getInstance().getAsync(CommonRequest.getUserDetailsRequest(PreferenceUtil.getUID(UserDetailsActivity.this)), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "onFailure call = "+call.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Logger.d(TAG, "getUserDetails onResponse code = "+response.code());
                String resonseContent = response.body().string();
                Logger.d(TAG, "responseContent = "+resonseContent);
                Gson gson = new Gson();
                userDetailsResponse = gson.fromJson(resonseContent, UserDetailsResponse.class);
                String name = userDetailsResponse.getData().getUser().getName();
                String interests = userDetailsResponse.getData().getUser().getExtend().getInterests();
                Logger.d(TAG, "name = "+name+ " interests = "+interests);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG,"onResume");
    }

    public UserDetailsResponse getUserDetailsResponse() {
        return userDetailsResponse;
    }

    public void setUserDetailsResponse(UserDetailsResponse userDetailsResponse) {
        this.userDetailsResponse = userDetailsResponse;
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
