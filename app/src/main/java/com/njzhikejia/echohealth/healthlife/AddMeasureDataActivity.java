package com.njzhikejia.echohealth.healthlife;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.njzhikejia.echohealth.healthlife.adapter.ViewPagerAdapter;
import com.njzhikejia.echohealth.healthlife.fragment.BaseFragment;
import com.njzhikejia.echohealth.healthlife.fragment.BloodPressureFragment;
import com.njzhikejia.echohealth.healthlife.fragment.HeartRateFragment;
import com.njzhikejia.echohealth.healthlife.fragment.HeightAndWeightFragment;
import com.njzhikejia.echohealth.healthlife.util.Logger;

/**
 * Created by 16222 on 2018/6/3.
 */

public class AddMeasureDataActivity extends BaseActivity {

    private static final String TAG = "AddMeasureDataActivity";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] titles;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        setContentView(R.layout.activity_add_measure_data);
        initView();
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
        titles = new String[]{getString(R.string.blood_pressure), getString(R.string.heart_rate), getString(R.string.height_and_weight)};
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles);
        adapter.addFragment(new BloodPressureFragment());
        adapter.addFragment(new HeartRateFragment());
        adapter.addFragment(new HeightAndWeightFragment());
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
        Logger.d(TAG, "onDestroy");
    }
}
