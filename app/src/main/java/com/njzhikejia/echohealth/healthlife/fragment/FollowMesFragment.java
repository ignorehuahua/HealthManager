package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.HealthLifeApplication;
import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.UserApplyActivity;
import com.njzhikejia.echohealth.healthlife.adapter.FollowMeAdapter;
import com.njzhikejia.echohealth.healthlife.entity.concern.Concerneds;
import com.njzhikejia.echohealth.healthlife.entity.concern.FollowMeData;
import com.njzhikejia.echohealth.healthlife.greendao.ConcernedsDao;
import com.njzhikejia.echohealth.healthlife.greendao.DaoSession;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NetWorkUtils;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 16222 on 2018/9/16.
 */

public class FollowMesFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "FollowMesFragment";
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private FollowMeAdapter mAdapter;
    private List<Concerneds> followMesList;
    private FollowMeHandler mHandler;
    private static final int LOAD_SUCCESS = 30;
    private static final int RESULT_SUCCESS = 31;
    private static final int RESULT_FAILURE = 32;
    private DaoSession mDaoSession;
    private ConcernedsDao concernedsDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        Logger.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_follow_me, null);
        initDaoSession();
        initView(view);
        return view;
    }

    private void initDaoSession() {
        HealthLifeApplication mApplication = (HealthLifeApplication) getActivity().getApplication();
        mDaoSession = mApplication.getDaoSession();
        concernedsDao = mDaoSession.getConcernedsDao();
    }

    private void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.toolbar);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecycleView = view.findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
        followMesList = new ArrayList<>();
        mAdapter = new FollowMeAdapter(mContext, followMesList);
        mRecycleView.setAdapter(mAdapter);
        mHandler = new FollowMeHandler(this);
        loadFollowMes();
        mAdapter.setOnItemClickListener(new FollowMeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Logger.d(TAG, "onItemClick position = "+position);
                Intent intentFollowMe = new Intent(mContext, UserApplyActivity.class);
                Concerneds concerneds = followMesList.get(position);
                intentFollowMe.putExtra(ConstantValues.KEY_FOLLOW_ME_USER, (Parcelable) concerneds);
                startActivity(intentFollowMe);
            }

            @Override
            public void onBtnAccpetOnClick(int position) {
                Logger.d(TAG, "onBtnAcceptOnClick position = "+position);
                Concerneds concerneds = followMesList.get(position);
                handleConcern(PreferenceUtil.getLoginUserUID(mContext), concerneds.getConcern_id(), ConstantValues.STATUS_DONE);

            }
        });
    }

    private void handleConcern(int userId, int concernId, int status) {

        if (!NetWorkUtils.isNetworkConnected(mContext)) {
            ToastUtil.showShortToast(mContext, R.string.net_work_error);
            return;
        }

        OKHttpClientManager.getInstance().postAsync(CommonRequest.postHandleConcernRequest(userId, concernId, status), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "handle concern failure !!!");
                mHandler.sendEmptyMessage(RESULT_FAILURE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                Logger.d(TAG, "onResponse code = "+response.code() + "content = "+responseContent);
                if (response.code() == 200) {
                    mHandler.sendEmptyMessage(RESULT_SUCCESS);
                }

            }
        });
    }

    private void loadFollowMes() {
        if (!NetWorkUtils.isNetworkConnected(mContext)) {
            ToastUtil.showShortToast(mContext, R.string.net_work_error);
            stopRefresh();
            loadDataFromDb();
            return;
        }

        OKHttpClientManager.getInstance().getAsync(CommonRequest.getFollowMes(PreferenceUtil.getLoginUserUID(mContext)), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "load follow me onFailure");
                stopRefresh();
                loadDataFromDb();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                Logger.d(TAG, "onResponse code = "+response.code() + "content = "+responseContent);
                if (response.code() == 200 && !TextUtils.isEmpty(responseContent)) {
                    Logger.d(TAG, "onRespone load my follows success");
                    Gson gson = new Gson();
                    FollowMeData data = gson.fromJson(responseContent, FollowMeData.class);
                    followMesList = data.getData().getConcerns();
                    mHandler.sendEmptyMessage(LOAD_SUCCESS);
                    concernedsDao.deleteAll();
                    for (Concerneds concerneds : followMesList) {
                        concernedsDao.insert(concerneds);
                    }
                }
            }
        });
    }

    private void loadDataFromDb() {
        followMesList = concernedsDao.loadAll();
        mAdapter.setList(followMesList);
    }

    @Override
    public void onRefresh() {
        loadFollowMes();
        mHandler.sendEmptyMessageDelayed(ConstantValues.MSG_REFRESH_TIME_OUT, ConstantValues.REFRESH_TIME_OUT);
    }

    class FollowMeHandler extends Handler {

        private WeakReference<FollowMesFragment> weakReference;

        public FollowMeHandler(FollowMesFragment fragment) {
            weakReference = new WeakReference<FollowMesFragment>(fragment);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LOAD_SUCCESS:
                    if (weakReference.get() != null) {
                        weakReference.get().stopRefresh();
                        weakReference.get().mAdapter.setList(followMesList);
                    }
                    break;
                case ConstantValues.MSG_REFRESH_TIME_OUT:
                    if (weakReference.get() != null) {
                        weakReference.get().stopRefresh();
                    }
                    break;
                case RESULT_SUCCESS:
                    if (weakReference.get() != null) {
                        weakReference.get().loadFollowMes();
                    }
                    break;
                case RESULT_FAILURE:
                    ToastUtil.showShortToast(mContext, R.string.request_failure);
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d(TAG, "onResume");
        PreferenceUtil.setNewConcern(mContext, false);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(ConstantValues.ACTION_CONCERN_REQUEST_FINISHED));
    }

    private void stopRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            Logger.d(TAG, "stopRefresh!");
        }
    }
}
