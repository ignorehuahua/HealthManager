package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import java.io.File;
import java.util.ArrayList;
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
    private PopupWindow mPopupWindow;
    private TextView tvGallery;
    private TextView tvCamera;
    private TextView tvCancel;
    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPopupWindow();
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
                if (mPopupWindow == null) {
                    return;
                }
                if (mPopupWindow.isShowing()) {
                    closePopupWindow();
                } else {
                    showPopupWindow();
                }
            }
        });


        mRecycleView = findViewById(R.id.recycle_view);
        memberList = new ArrayList<>();
        testInitMembers();
        initBanner();
        mAdapter = new MemberListAdapter(this, memberList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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

    private void initPopupWindow() {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.popwindow_avatar, null);
        tvGallery = view.findViewById(R.id.pop_pic);
        tvCamera = view.findViewById(R.id.pop_camera);
        tvCancel = view.findViewById(R.id.pop_cancel);
        tvGallery.setOnClickListener(this);
        tvCamera.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        mPopupWindow = new PopupWindow(view, 660,  ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
    }

    private void setBackgroundAlpha(float alphaLevel) {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = alphaLevel;
        window.setAttributes(params);
    }

    private void showPopupWindow() {
        if (mPopupWindow != null) {
            setBackgroundAlpha(0.6f);
            mPopupWindow.showAsDropDown(ivAvatar, 0, 20);
        }
    }

    private void closePopupWindow() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
            Logger.d(TAG, "close popupWindow");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        closePopupWindow();
    }


    /**
     * 从相册选取
     */
    private void choosePhoto() {
        Intent intentPhoto = new Intent(Intent.ACTION_PICK);
        intentPhoto.setType("image/*");
        startActivityForResult(intentPhoto, ConstantValues.REQUEST_CODE_OF_GRLLERY);
    }


    /**
     * 拍照
     */
    private void takePhoto() {
        //创建一个file，用来存储拍照后的照片
        File imageFile = new File(this.getExternalCacheDir(),"output.png");
        try {
            if (imageFile.exists()){
                imageFile.delete();//删除
            }
            imageFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24){
            mImageUri = FileProvider.getUriForFile(MainActivity.this,
                    "com.njzhikejia.echohealth.healthlife.fileprovider",
                    imageFile);
        }else{
            mImageUri = Uri.fromFile(imageFile);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,mImageUri);
        startActivityForResult(intent,ConstantValues.REQUEST_CODE_OF_CAMERA);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.d(TAG, "onActivityResult resultCode = "+resultCode+" requestCode = "+requestCode);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ConstantValues.REQUEST_CODE_OF_GRLLERY:
                    if (data == null) {
                        Logger.e(TAG, "no photo selected");
                        return;
                    }
                    setAvatar(data.getData());
                    break;
                case ConstantValues.REQUEST_CODE_OF_CAMERA:
                    if (mImageUri == null) {
                        Logger.e(TAG, "take photo imageuri is null");
                        return;
                    }
                    setAvatar(mImageUri);
                    break;

            }
        }
    }

    private void setAvatar(Uri uri) {
        Bitmap selectedPhoto = ImageUtil.decodeUri(MainActivity.this,uri,800, 480);
        ivAvatar.setImageBitmap(selectedPhoto);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_pic:
                Logger.d(TAG, "choose photo from gallery");
                choosePhoto();
                closePopupWindow();
                break;
            case R.id.pop_camera:
                Logger.d(TAG, "take photo");
                takePhoto();
                closePopupWindow();
                break;
            case R.id.pop_cancel:
                Logger.d(TAG, "cancel select photo");
                closePopupWindow();
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}

