package com.njzhikejia.echohealth.healthlife;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.njzhikejia.echohealth.healthlife.adapter.ViewPagerAdapter;
import com.njzhikejia.echohealth.healthlife.entity.Member;
import com.njzhikejia.echohealth.healthlife.fragment.HealthGuidanceFragment;
import com.njzhikejia.echohealth.healthlife.fragment.LocationFragment;
import com.njzhikejia.echohealth.healthlife.fragment.MeasureDataFragment;
import com.njzhikejia.echohealth.healthlife.fragment.WarningFragment;
import com.njzhikejia.echohealth.healthlife.util.BottomNavigationViewHelper;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NoScrollViewPager;

/**
 * Created by 16222 on 2018/5/27.
 */

public class MeasureDataActivity extends BaseActivity {

    private static final String TAG = "MeasureDataActivity";
    private NoScrollViewPager mViewPager;
    private MenuItem mMenuItem;
    private Toolbar mToolbar;
    private String name;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigation;
    private ImageView ivAvatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        setContentView(R.layout.activity_measure_data);
        initView();
        setupViewPager(mViewPager);
        initPage();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mNavigation = findViewById(R.id.navigation);
        setSupportActionBar(mToolbar);
        if (HealthLifeApplication.isMultiUser) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mNavigation.setVisibility(View.GONE);
        } else {
            mNavigation.setVisibility(View.VISIBLE);
            mToolbar.setNavigationIcon(R.drawable.ic_menu);
            mDrawerToggle = new ActionBarDrawerToggle(
                    this,
                    mDrawerLayout,
                    mToolbar,0,0
            );
            mDrawerLayout.addDrawerListener(mDrawerToggle);
            mDrawerToggle.syncState();
        }


        final BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mViewPager = findViewById(R.id.viewpager);
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (mMenuItem != null) {
//                    mMenuItem.setChecked(false);
//                } else {
//                    navigation.getMenu().getItem(0).setChecked(false);
//                }
//                mMenuItem = navigation.getMenu().getItem(position);
//                mMenuItem.setChecked(true);
//                if (!HealthLifeApplication.isMultiUser) {
//                    return;
//                }
//                switch (position) {
//                    case 0:
//                        mToolbar.setTitle(name+"-"+getString(R.string.measure_data));
//                        break;
//                    case 1:
//                        mToolbar.setTitle(name+"-"+getString(R.string.evaluation_guide));
//                        break;
//                    case 2:
//                        mToolbar.setTitle(name+"-"+getString(R.string.warn_notice));
//                        break;
//                    case 3:
//                        mToolbar.setTitle(name+"-"+getString(R.string.location));
//                        break;
//                }
//            }
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_meember_manager:
                        Logger.d(TAG, "menu member manager clicked");
                        Intent intentMember = new Intent(MeasureDataActivity.this, MemberManageActivity.class);
                        startActivity(intentMember);
                        break;

                    case R.id.menu_setting:
                        break;

                    case R.id.menu_feedback:
                        break;

                    case R.id.menu_about:
                        Intent intentAbout = new Intent(MeasureDataActivity.this, LoginActivity.class);
                        startActivity(intentAbout);
                        break;
                }
                mDrawerLayout.closeDrawer(mNavigation);
                return false;
            }
        });

        View headerLayout = mNavigation.getHeaderView(0);
        ivAvatar= headerLayout.findViewById(R.id.iv_avatar);
        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mPopupWindow == null) {
//                    return;
//                }
//                if (mPopupWindow.isShowing()) {
//                    closePopupWindow();
//                } else {
//                    showPopupWindow();
//                }
                Intent intentDetails = new Intent(MeasureDataActivity.this, UserDetailsActivity.class);
                startActivity(intentDetails);
            }
        });
    }

    private void setupViewPager(NoScrollViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MeasureDataFragment());
        adapter.addFragment(new HealthGuidanceFragment());
        adapter.addFragment(new WarningFragment());
        adapter.addFragment(new LocationFragment());
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
            if (HealthLifeApplication.isMultiUser) {
                mToolbar.setTitle(name+"-"+getString(R.string.measure_data));
            }
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
                    if (HealthLifeApplication.isMultiUser)
                    mToolbar.setTitle(name+"-"+getString(R.string.measure_data));
                    return true;
                case R.id.menu_guide:
                    mViewPager.setCurrentItem(1);
                    if (HealthLifeApplication.isMultiUser)
                    mToolbar.setTitle(name+"-"+getString(R.string.evaluation_guide));
                    return true;
                case R.id.menu_warn:
                    mViewPager.setCurrentItem(2);
                    if (HealthLifeApplication.isMultiUser)
                    mToolbar.setTitle(name+"-"+getString(R.string.warn_notice));
                    return true;
                case R.id.menu_locate:
                    mViewPager.setCurrentItem(3);
                    if (HealthLifeApplication.isMultiUser)
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
                if (HealthLifeApplication.isMultiUser) {
                    finish();
                }
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
