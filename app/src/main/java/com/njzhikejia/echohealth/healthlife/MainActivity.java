package com.njzhikejia.echohealth.healthlife;

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

import com.njzhikejia.echohealth.healthlife.adapter.MemberListAdapter;
import com.njzhikejia.echohealth.healthlife.entity.Member;
import com.njzhikejia.echohealth.healthlife.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Toolbar mToolbar;
    private NavigationView mNavigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView mRecycleView;
    private MemberListAdapter mAdapter;
    private List<Member> memberList;

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
                        break;

                    case R.id.menu_setting:
                        break;

                    case R.id.menu_feedback:
                        break;

                    case R.id.menu_about:
                        break;
                }
                mDrawerLayout.closeDrawer(mNavigation);
                return false;
            }
        });

        mRecycleView = findViewById(R.id.recycle_view);
        memberList = new ArrayList<>();
        testInitMembers();
        mAdapter = new MemberListAdapter(this, memberList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    //    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);

        mRecycleView.setAdapter(mAdapter);
    }

    private void testInitMembers() {
        Member wukong = new Member(null, "悟空", "12:30");
        Member bajie = new Member(null, "八戒","14:00");
        memberList.add(wukong);
        memberList.add(bajie);
    }
}

