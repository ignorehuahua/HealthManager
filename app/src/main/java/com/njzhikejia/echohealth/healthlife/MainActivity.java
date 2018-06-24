package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
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
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.ImageUtil;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import java.util.ArrayList;
import java.util.List;

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
    private TextView tvVisit;
    private TextView tvGuidance;
    private TextView tvMsgCenter;
    private TextView tvWarning;
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
        tvVisit = findViewById(R.id.tv_apply_visit);
        tvGuidance = findViewById(R.id.tv_evaluation_guide);
        tvMsgCenter = findViewById(R.id.tv_msg_center);
        tvWarning = findViewById(R.id.tv_warn);
        tvVisit.setOnClickListener(this);
        tvGuidance.setOnClickListener(this);
        tvMsgCenter.setOnClickListener(this);
        tvWarning.setOnClickListener(this);

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
            case R.id.tv_apply_visit:

                break;
            case R.id.tv_evaluation_guide:
                Intent intentGuidance = new Intent(MainActivity.this, MeasureDataActivity.class);
                intentGuidance.putExtra(ConstantValues.KEY_TO_PAGE, ConstantValues.PAGE_OF_GUIDANCE);
                startActivity(intentGuidance);
                break;
            case R.id.tv_msg_center:
                break;
            case R.id.tv_warn:
                Intent intentWarn = new Intent(MainActivity.this, MeasureDataActivity.class);
                intentWarn.putExtra( ConstantValues.KEY_TO_PAGE, ConstantValues.PAGE_OF_WARN);
                startActivity(intentWarn);
                break;
        }
    }
}

