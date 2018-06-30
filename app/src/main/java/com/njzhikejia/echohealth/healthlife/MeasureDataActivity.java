package com.njzhikejia.echohealth.healthlife;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.adapter.ViewPagerAdapter;
import com.njzhikejia.echohealth.healthlife.entity.Member;
import com.njzhikejia.echohealth.healthlife.fragment.BaseFragment;
import com.njzhikejia.echohealth.healthlife.fragment.HealthGuidanceFragment;
import com.njzhikejia.echohealth.healthlife.fragment.MeasureDataFragment;
import com.njzhikejia.echohealth.healthlife.fragment.WarningFragment;
import com.njzhikejia.echohealth.healthlife.util.BottomNavigationViewHelper;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;

/**
 * Created by 16222 on 2018/5/27.
 */

public class MeasureDataActivity extends AppCompatActivity {

    private static final String TAG = "MeasureDataActivity";
    private ViewPager mViewPager;
    private MenuItem mMenuItem;
    private Toolbar mToolbar;
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        setContentView(R.layout.activity_measure_data);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mViewPager = findViewById(R.id.viewpager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mMenuItem != null) {
                    mMenuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                mMenuItem = navigation.getMenu().getItem(position);
                mMenuItem.setChecked(true);
                switch (position) {
                    case 0:
                        mToolbar.setTitle(name+"-"+getString(R.string.measure_data));
                        break;
                    case 1:
                        mToolbar.setTitle(name+"-"+getString(R.string.evaluation_guide));
                        break;
                    case 2:
                        mToolbar.setTitle(name+"-"+getString(R.string.warn_notice));
                        break;
                    case 3:
                        mToolbar.setTitle(name+"-"+getString(R.string.location));
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(mViewPager);
        initPage();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MeasureDataFragment());
        adapter.addFragment(new HealthGuidanceFragment());
        adapter.addFragment(new WarningFragment());
        adapter.addFragment(BaseFragment.newInstance(this.getString(R.string.location)));
        viewPager.setAdapter(adapter);
    }

    private void initPage() {
        Intent intentPage = getIntent();
        if (intentPage == null) {
            return;
        }

        int currentPage = intentPage.getIntExtra(ConstantValues.KEY_TO_PAGE, 0);
        Member member = intentPage.getParcelableExtra(ConstantValues.KEY_MEMBER_INFO);
        if (member != null) {
            name = member.getName();
            Logger.d(TAG, "get name = "+name);
            mToolbar.setTitle(name+"-"+getString(R.string.measure_data));
        }
        mViewPager.setCurrentItem(currentPage);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_data:
                    mViewPager.setCurrentItem(0);
                    mToolbar.setTitle(name+"-"+getString(R.string.measure_data));
                    return true;
                case R.id.menu_guide:
                    mViewPager.setCurrentItem(1);
                    mToolbar.setTitle(name+"-"+getString(R.string.evaluation_guide));
                    return true;
                case R.id.menu_warn:
                    mViewPager.setCurrentItem(2);
                    mToolbar.setTitle(name+"-"+getString(R.string.warn_notice));
                    return true;
                case R.id.menu_locate:
                    mViewPager.setCurrentItem(3);
                    mToolbar.setTitle(name+"-"+getString(R.string.location));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d(TAG, "onDestroy");
    }
}
