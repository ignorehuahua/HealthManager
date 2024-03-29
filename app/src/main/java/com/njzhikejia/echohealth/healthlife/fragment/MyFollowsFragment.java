package com.njzhikejia.echohealth.healthlife.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.HealthLifeApplication;
import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.UserApplyActivity;
import com.njzhikejia.echohealth.healthlife.adapter.MyFollowsAdapter;
import com.njzhikejia.echohealth.healthlife.entity.concern.Concerns;
import com.njzhikejia.echohealth.healthlife.entity.concern.MyFollowsData;
import com.njzhikejia.echohealth.healthlife.greendao.ConcernsDao;
import com.njzhikejia.echohealth.healthlife.greendao.DaoSession;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NetWorkUtils;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

import org.greenrobot.greendao.query.QueryBuilder;

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

public class MyFollowsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private MyFollowsAdapter mAdapter;
    private List<Concerns> myFollowsList;
    private static final String TAG = "MyFollowsFragment";
    private MyFollowsHandler mHandler;
    private static final int LOAD_SUCCESS = 30;
    private static final int LOAD_FAILURE = 31;
    private static final int HANDLE_CONCERN_CODE = 40;
    private DaoSession mDaoSession;
    private ConcernsDao concernsDao;
    private TextView tvNoData;
    private LocalBroadcastManager mLocalBroadcastManager;
    private FollowBroadcastReceiver mReceiver;
    private QueryBuilder<Concerns> queryBuilder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_my_follows, null);
        initDaoSession();
        initView(view);
        return view;
    }

    private void initDaoSession() {
        HealthLifeApplication mApplication = (HealthLifeApplication) getActivity().getApplication();
        mDaoSession = mApplication.getDaoSession();
        concernsDao = mDaoSession.getConcernsDao();
        queryBuilder = concernsDao.queryBuilder();
    }

    private void initView(View view) {
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        mReceiver = new FollowBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(ConstantValues.ACTION_CONCERN_REQUEST_ACCEPTED);
        mLocalBroadcastManager.registerReceiver(mReceiver, intentFilter);
        tvNoData = view.findViewById(R.id.tv_no_data);
        tvNoData.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.toolbar);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecycleView = view.findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
        myFollowsList = new ArrayList<>();
        mAdapter = new MyFollowsAdapter(mContext, myFollowsList);
        mRecycleView.setAdapter(mAdapter);
        mHandler = new MyFollowsHandler(this);
        loadMyFollows();
        mAdapter.setOnItemClickListener(new MyFollowsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intentMyFollow = new Intent(mContext, UserApplyActivity.class);
                Concerns concerns = myFollowsList.get(position);
                intentMyFollow.putExtra(ConstantValues.KEY_MY_FOLLOW_USER, concerns);
                startActivityForResult(intentMyFollow, HANDLE_CONCERN_CODE);

            }
        });
    }

    class FollowBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConstantValues.ACTION_CONCERN_REQUEST_ACCEPTED)) {
                loadMyFollows();
            }
        }
    }

    class MyFollowsHandler extends Handler{

        private WeakReference<MyFollowsFragment> weakReference;

        public MyFollowsHandler(MyFollowsFragment fragment) {
            weakReference = new WeakReference<MyFollowsFragment>(fragment);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LOAD_SUCCESS:
                    if (weakReference.get() != null) {
                        weakReference.get().stopRefresh();
                        weakReference.get().checkEmptyData();
                        weakReference.get().mAdapter.setList(myFollowsList);
                    }
                    break;
                case ConstantValues.MSG_REFRESH_TIME_OUT:
                    if (weakReference.get() != null) {
                        weakReference.get().stopRefresh();
                        weakReference.get().checkEmptyData();
                    }
                    break;
                case LOAD_FAILURE:
                    if (weakReference.get() != null) {
                        weakReference.get().stopRefresh();
                        weakReference.get().loadDataFromDb();
                    }
                    break;
            }
        }
    }

    private void checkEmptyData() {
        if (myFollowsList != null && myFollowsList.size() > 0) {
            tvNoData.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.VISIBLE);
        }
    }

    private void loadMyFollows() {
        if (!NetWorkUtils.isNetworkConnected(mContext)) {
            ToastUtil.showShortToast(mContext, R.string.net_work_error);
            stopRefresh();
            loadDataFromDb();
            return;
        }

        OKHttpClientManager.getInstance().getAsync(CommonRequest.getMyFollows(PreferenceUtil.getLoginUserUID(mContext)), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "onFailure");
                mHandler.sendEmptyMessage(LOAD_FAILURE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                Logger.d(TAG, "onResponse code = "+response.code() + "content = "+responseContent);
                if (response.code() == 200 && !TextUtils.isEmpty(responseContent)) {
                    Logger.d(TAG, "onRespone load my follows success");
                    Gson gson = new Gson();
                    MyFollowsData data = gson.fromJson(responseContent, MyFollowsData.class);
                    myFollowsList = data.getData().getConcerns();
                    mHandler.sendEmptyMessage(LOAD_SUCCESS);
                    concernsDao.deleteAll();
                    for (Concerns concerns : myFollowsList) {
                        concernsDao.insert(concerns);
                    }
                }
            }
        });
    }

    private void loadDataFromDb() {
        queryBuilder.where(ConcernsDao.Properties.Uid.notEq(PreferenceUtil.getLoginUserUID(mContext)));
        myFollowsList = queryBuilder.list();
        mAdapter.setList(myFollowsList);
        checkEmptyData();
    }

    private void stopRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            Logger.d(TAG, "stopRefresh!");
        }
    }

    @Override
    public void onRefresh() {
        loadMyFollows();
        mHandler.sendEmptyMessageDelayed(ConstantValues.MSG_REFRESH_TIME_OUT, ConstantValues.REFRESH_TIME_OUT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == HANDLE_CONCERN_CODE && resultCode == Activity.RESULT_OK) {
            loadMyFollows();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mLocalBroadcastManager != null) {
            mLocalBroadcastManager.unregisterReceiver(mReceiver);
        }
    }
}
