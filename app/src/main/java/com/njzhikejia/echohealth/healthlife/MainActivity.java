package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
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

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.adapter.MemberListAdapter;
import com.njzhikejia.echohealth.healthlife.entity.RelativesData;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.ChineseCharComp;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.ImageUtil;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NetWorkUtils;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;
import com.njzhikejia.echohealth.healthlife.widget.banner.CycleViewPager;
import com.njzhikejia.echohealth.healthlife.widget.banner.ViewUtil;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Toolbar mToolbar;
    private NavigationView mNavigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView mRecycleView;
    private MemberListAdapter mAdapter;
    private List<RelativesData.Data.Relatives> memberList;
    private ImageView ivAvatar;
    private static final int REQUEST_CODE_CHOOSE = 200;
    private static final int LOAD_RELATIVES = 28;
    private PopupWindow mPopupWindow;
    private TextView tvGallery;
    private TextView tvCamera;
    private TextView tvCancel;
    private Uri mImageUri;
    private MainHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new MainHandler(this);
        initView();
        loadRealtives();
        initPopupWindow();
    }

    private void initView() {
        Logger.d(TAG, "initView");
        mToolbar = findViewById(R.id.toolbar);
        mNavigation = findViewById(R.id.bottom_navigation);
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
                        Logger.d(TAG, "menu system setting clicked");
                        Intent intentSetting = new Intent(MainActivity.this, SystemSettingActivity.class);
                        startActivity(intentSetting);
                        break;

                    case R.id.menu_feedback:
                        Logger.d(TAG, "menu feedback clicked");
                        Intent intentFeedback = new Intent(MainActivity.this, FeedbackActivity.class);
                        startActivity(intentFeedback);
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
//                if (mPopupWindow == null) {
//                    return;
//                }
//                if (mPopupWindow.isShowing()) {
//                    closePopupWindow();
//                } else {
//                    showPopupWindow();
//                }
                Intent intentDetails = new Intent(MainActivity.this, UserDetailsActivity.class);
                startActivity(intentDetails);
            }
        });


        mRecycleView = findViewById(R.id.recycle_view);
        memberList = new ArrayList<>();
        initBanner();
        mAdapter = new MemberListAdapter(this, memberList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MemberListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                RelativesData.Data.Relatives member = memberList.get(position);
                Logger.d(TAG, "onItemClick member name is "+member.getName());
                Intent intentMember = new Intent(view.getContext(), MeasureDataActivity.class);
                intentMember.putExtra(ConstantValues.KEY_MEMBER_INFO, member);
                startActivity(intentMember);
            }
        });
    }

    static class MainHandler extends Handler{

        private WeakReference<MainActivity> weakReference;

        public MainHandler(MainActivity mainActivity) {
            weakReference = new WeakReference<MainActivity>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValues.MSG_REFRESH_TIME_OUT:
                    break;
                case LOAD_RELATIVES:
                    if (weakReference.get() != null) {
                        weakReference.get().mAdapter.setList(weakReference.get().memberList);
                    }
                    break;
            }
        }
    }

    private void loadRealtives() {
        if (!NetWorkUtils.isNetworkConnected(this)) {
            ToastUtil.showShortToast(this, R.string.net_work_error);
            return;
        }

        OKHttpClientManager.getInstance().getAsync(CommonRequest.getRealtivesList(PreferenceUtil.getLoginUserUID(this)), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "loadRelatives failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                Logger.d(TAG, "onResponse code = "+response.code() + "responseContent = "+responseContent);
                Gson gson = new Gson();
                RelativesData relativesData = gson.fromJson(responseContent, RelativesData.class);
                memberList = relativesData.getData().getRelatives();
                RelativesData.Data.Relatives me = new RelativesData.Data.Relatives();
                me.setName(getString(R.string.me));
                me.setPhone(PreferenceUtil.getLoginUserPhone(MainActivity.this));
                me.setUid(PreferenceUtil.getLoginUserUID(MainActivity.this));
                memberList.add(me);
                Comparator cmp = new ChineseCharComp();
                Collections.sort(memberList, cmp);
                mHandler.sendEmptyMessage(LOAD_RELATIVES);
            }
        });
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

