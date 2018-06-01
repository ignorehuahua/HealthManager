package com.njzhikejia.echohealth.healthlife;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.njzhikejia.echohealth.healthlife.adapter.ViewPagerAdapter;
import com.njzhikejia.echohealth.healthlife.fragment.BaseFragment;
import com.njzhikejia.echohealth.healthlife.util.Logger;

/**
 * Created by 16222 on 2018/6/1.
 */

public class MemberManageActivity extends AppCompatActivity {

    private static final String TAG = "MemberManageActivity";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] titles;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        setContentView(R.layout.activity_member_manage);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        titles = new String[]{getString(R.string.my_follow), getString(R.string.follow_me)};
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles);
        adapter.addFragment(BaseFragment.newInstance(this.getString(R.string.my_follow)));
        adapter.addFragment(BaseFragment.newInstance(this.getString(R.string.follow_me)));
        viewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
