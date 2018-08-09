package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.adapter.MeasureDataAdapter;
import com.njzhikejia.echohealth.healthlife.adapter.WarnAdapter;
import com.njzhikejia.echohealth.healthlife.entity.MeasureData;
import com.njzhikejia.echohealth.healthlife.entity.WarnInfo;
import com.njzhikejia.echohealth.healthlife.entity.WarnNoticesData;
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
 * Created by 16222 on 2018/6/30.
 */

public class WarningFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "WarningFragment";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private WarnAdapter mAdapter;
    private List<WarnNoticesData.Data.Notices> warnInfoList;
    private Context mContext;
    private WarnHandler mHandler;
    private static final int KEY_WARN = 22;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCrateView");
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_warning, null);
        initView(view);
        mHandler = new WarnHandler(this);
        return view;
    }

    private void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.toolbar);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecycleView = view.findViewById(R.id.recycle_view_data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
        warnInfoList = new ArrayList<>();
        mAdapter = new WarnAdapter(mContext, warnInfoList);
        mRecycleView.setAdapter(mAdapter);
        loadWarnNotices();
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(TAG, "onPause");
        stopRefresh();
    }

    @Override
    public void onRefresh() {
        Logger.d(TAG, "onRefresh");
        loadWarnNotices();
        mHandler.sendEmptyMessageDelayed(ConstantValues.MSG_REFRESH_TIME_OUT, ConstantValues.REFRESH_TIME_OUT);
    }

    static class WarnHandler extends Handler {

        private WeakReference<WarningFragment> warningFragmentWeakReference;

        public WarnHandler(WarningFragment warningFragment) {
            warningFragmentWeakReference = new WeakReference<WarningFragment>(warningFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValues.MSG_REFRESH_TIME_OUT:
                    if (warningFragmentWeakReference.get() != null)
                        warningFragmentWeakReference.get().stopRefresh();
                    break;
                case KEY_WARN:
                    if (warningFragmentWeakReference.get() != null) {
                        warningFragmentWeakReference.get().stopRefresh();
                        warningFragmentWeakReference.get().mAdapter.setlist(warningFragmentWeakReference.get().warnInfoList);
                    }
                    break;
            }
        }
    }

    private void stopRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            Logger.d(TAG, "stopRefresh!");
        }
    }

    private void loadWarnNotices() {
        if (!NetWorkUtils.isNetworkConnected(mContext)) {
            ToastUtil.showShortToast(mContext, R.string.net_work_error);
            return;
        }
        OKHttpClientManager.getInstance().getAsync(CommonRequest.getUserWarnInfo(PreferenceUtil.getUID(mContext)), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "loadWarnNotices onFailure");
                stopRefresh();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                Logger.d(TAG, "code = "+response.code() + "responseContent = "+responseContent);
                Gson gson = new Gson();
                WarnNoticesData warnNoticesData = gson.fromJson(responseContent, WarnNoticesData.class);
                warnInfoList = warnNoticesData.getData().getNotices();
                mHandler.sendEmptyMessage(KEY_WARN);
            }
        });
    }
}
