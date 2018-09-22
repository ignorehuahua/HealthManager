package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.njzhikejia.echohealth.healthlife.adapter.ViewPagerAdapter;
import com.njzhikejia.echohealth.healthlife.fragment.BaseFragment;
import com.njzhikejia.echohealth.healthlife.fragment.FollowMesFragment;
import com.njzhikejia.echohealth.healthlife.fragment.MyFollowsFragment;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;

/**
 * Created by 16222 on 2018/6/1.
 */

public class MemberManageActivity extends BaseActivity {

    private static final String TAG = "MemberManageActivity";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] titles;
    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private static final int SCAN_REQUEST_CODE = 1;
    private MyFollowsFragment myFollowsFragment;
    private FollowMesFragment followMesFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        setContentView(R.layout.activity_member_manage);
        initView();
        setCurrentPage();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.viewpager);
        mFab = findViewById(R.id.floating_action_btn);
        setupViewPager(mViewPager);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 扫描二维码添加好友
//                Intent intentScan = new Intent(MemberManageActivity.this, CaptureActivity.class);
//                startActivityForResult(intentScan, SCAN_REQUEST_CODE);
                initScanConfig();
            }
        });
    }

    private void setCurrentPage() {
        Intent intent = getIntent();
        if (intent.getIntExtra(HealthLifeApplication.KEY_JUMP_TO_FOLLOW_ME, 0) == 1) {
            mViewPager.setCurrentItem(1);
        } else if (PreferenceUtil.getNewConcern(this)) {

        }
    }

    private void initScanConfig() {
        IntentIntegrator integrator = new IntentIntegrator(MemberManageActivity.this);

        // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setCaptureActivity(ScanActivity.class);
        integrator.setPrompt(getResources().getString(R.string.scan_qrcode_description)); //底部的提示文字，设为""可以置空
        integrator.setCameraId(0); //前置或者后置摄像头
        integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    private void setupViewPager(ViewPager viewPager) {
        titles = new String[]{getString(R.string.my_follow), getString(R.string.follow_me)};
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), titles);
        myFollowsFragment = new MyFollowsFragment();
        followMesFragment = new FollowMesFragment();
        adapter.addFragment(myFollowsFragment);
        adapter.addFragment(followMesFragment);
        viewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.d(TAG, "onActivityResult");
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
           if (result.getContents() != null) {
               Logger.d(TAG, "result.getContents = "+result.getContents());
               Intent intent = new Intent(this, ScanResultActivity.class);
               intent.putExtra(ConstantValues.SCAN_RESULT, result.getContents());
               startActivity(intent);
           }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
