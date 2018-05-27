package com.njzhikejia.echohealth.healthlife;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.njzhikejia.echohealth.healthlife.adapter.ViewPagerAdapter;
import com.njzhikejia.echohealth.healthlife.fragment.BaseFragment;
import com.njzhikejia.echohealth.healthlife.util.BottomNavigationViewHelper;
import com.njzhikejia.echohealth.healthlife.util.Logger;

/**
 * Created by 16222 on 2018/5/27.
 */

public class MeasureDataActivity extends AppCompatActivity {

    private static final String TAG = "MeasureDataActivity";
    private ViewPager mViewPager;
    private MenuItem mMenuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        setContentView(R.layout.activity_measure_data);
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
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(BaseFragment.newInstance(this.getString(R.string.data)));
        adapter.addFragment(BaseFragment.newInstance(this.getString(R.string.guide)));
        adapter.addFragment(BaseFragment.newInstance(this.getString(R.string.warn)));
        adapter.addFragment(BaseFragment.newInstance(this.getString(R.string.location)));
        viewPager.setAdapter(adapter);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_data:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.menu_guide:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.menu_warn:
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.menu_locate:
                    mViewPager.setCurrentItem(3);
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
    protected void onDestroy() {
        super.onDestroy();
        Logger.d(TAG, "onDestroy");
    }
}
