package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.adapter.MemberListAdapter;
import com.njzhikejia.echohealth.healthlife.entity.Member;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.http.ServerAddrUtils;
import com.njzhikejia.echohealth.healthlife.service.LoopService;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.ImageUtil;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PhoneUtil;
import com.njzhikejia.echohealth.healthlife.widget.banner.CycleViewPager;
import com.njzhikejia.echohealth.healthlife.widget.banner.ViewUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Toolbar mToolbar;
    private NavigationView mNavigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView mRecycleView;
    private MemberListAdapter mAdapter;
    private List<Member> memberList;
    private ImageView ivAvatar;
    private static final int REQUEST_CODE_CHOOSE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Logger.d(TAG, "initView");
        mToolbar = findViewById(R.id.toolbar);
        mNavigation = findViewById(R.id.navigation);
        mDrawerLayout = findViewById(R.id.drawerLayout);


        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,0,0
        );

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_meember_manager:
                        Logger.d(TAG, "menu member manager clicked");
                        Intent intentMember = new Intent(MainActivity.this, MemberManageActivity.class);
                        startActivity(intentMember);
                        break;

                    case R.id.menu_setting:
                        break;

                    case R.id.menu_feedback:
                        break;

                    case R.id.menu_about:
                        Intent intentAbout = new Intent(MainActivity.this, LoginActivity.class);
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
                ImageUtil.choosePhoto(MainActivity.this, REQUEST_CODE_CHOOSE);
            }
        });


        mRecycleView = findViewById(R.id.recycle_view);
        memberList = new ArrayList<>();
        testInitMembers();
        initBanner();
        mAdapter = new MemberListAdapter(this, memberList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    //    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);

        mRecycleView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MemberListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Member member = memberList.get(position);
                Logger.d(TAG, "onItemClick member name is "+member.getName());
                Intent intentMember = new Intent(view.getContext(), MeasureDataActivity.class);
                intentMember.putExtra(ConstantValues.KEY_MEMBER_INFO, member);
                startActivity(intentMember);
            }
        });
    }

    private void testInitMembers() {
        Member wukong = new Member(null, "悟空", "12:30");
        Member bajie = new Member(null, "八戒","14:00");
        memberList.add(wukong);
        memberList.add(bajie);
    }

    private void initBanner() {
        List<View> views = new ArrayList<View>();
        // 将最后一个view添加进来
        views.add(ViewUtil.getView(this, R.drawable.photo_four));
        views.add(ViewUtil.getView(this, R.drawable.photo_one));
        views.add(ViewUtil.getView(this, R.drawable.photo_two));
        views.add(ViewUtil.getView(this, R.drawable.photo_three));
        views.add(ViewUtil.getView(this, R.drawable.photo_four));

        // 将第一个view添加进来
        views.add(ViewUtil.getView(this, R.drawable.photo_one));

        final CycleViewPager cycleViewPager = (CycleViewPager) findViewById(R.id.banner);
        cycleViewPager.setIndicatorCenter();
        cycleViewPager.setIndicatorsSpace(8);
        cycleViewPager.setData(views, true, true, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d(TAG, "onActivityResult resultCode = "+resultCode+" requestCode = "+requestCode);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CHOOSE) {
            if (data == null) {
                Logger.e(TAG, "no selected photo");
                return;
            }
            List<Uri> mSelected = Matisse.obtainResult(data);
            if (mSelected != null && mSelected.size() > 0) {
                Bitmap selectedPhoto = ImageUtil.decodeUri(MainActivity.this, mSelected.get(0),300, 300);
                ivAvatar.setImageBitmap(selectedPhoto);
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}

