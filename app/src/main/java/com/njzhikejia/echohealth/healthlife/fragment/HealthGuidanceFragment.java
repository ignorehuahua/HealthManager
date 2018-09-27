package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.HealthLifeApplication;
import com.njzhikejia.echohealth.healthlife.R;
import com.njzhikejia.echohealth.healthlife.ReportDetailsActivity;
import com.njzhikejia.echohealth.healthlife.adapter.HealthGuidanceAdapter;
import com.njzhikejia.echohealth.healthlife.entity.report.ReportData;
import com.njzhikejia.echohealth.healthlife.entity.report.Reports;
import com.njzhikejia.echohealth.healthlife.greendao.DaoSession;
import com.njzhikejia.echohealth.healthlife.greendao.ReportsDao;
import com.njzhikejia.echohealth.healthlife.http.CommonRequest;
import com.njzhikejia.echohealth.healthlife.http.OKHttpClientManager;
import com.njzhikejia.echohealth.healthlife.util.ConstantValues;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.NetWorkUtils;
import com.njzhikejia.echohealth.healthlife.util.PreferenceUtil;
import com.njzhikejia.echohealth.healthlife.util.ToastUtil;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 16222 on 2018/5/28.
 */

public class HealthGuidanceFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "HealthGuidanceFragment";
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private HealthGuidanceAdapter mAdapter;
    private List<Reports> healthGuidanceList;
    private HealthGuidanceHandler mHandler;
    private static final int KEY_REPORT = 21;
    private static final int KEY_FAILURE = 23;
    private TextView tvNoData;
    private DaoSession mDaoSession;
    private ReportsDao reportsDao;
    private QueryBuilder<Reports> queryBuilder;
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_COUNT = 8;
    private static final String KEY_LOAD_SIZE = "key_load_size";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateView");
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_health_guidance, null);
        initSession();
        initView(view);
        mHandler = new HealthGuidanceHandler(this);
        return view;
    }

    private void initSession() {
        HealthLifeApplication mApplication = (HealthLifeApplication) getActivity().getApplication();
        mDaoSession = mApplication.getDaoSession();
        reportsDao = mDaoSession.getReportsDao();
        queryBuilder = reportsDao.queryBuilder();
    }

    private void initView(View view) {
        tvNoData = view.findViewById(R.id.tv_no_data);
        tvNoData.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.toolbar);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecycleView = view.findViewById(R.id.recycle_view_health_guidance);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
        healthGuidanceList = new ArrayList<>();
        mAdapter = new HealthGuidanceAdapter(mContext, healthGuidanceList);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new HealthGuidanceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Reports report = healthGuidanceList.get(position);
                int reportType = report.getType();
                Logger.d(TAG, "onItemClick reportType = "+reportType);
                if (reportType == HealthGuidanceAdapter.DEPRESSION_REPORT || reportType == HealthGuidanceAdapter.SLEEP_REPORT) {
                    Intent intentDetail = new Intent(mContext, ReportDetailsActivity.class);
                    intentDetail.putExtra(ConstantValues.KEY_REPORT_DETAIL_ID, report.getId());
                    startActivity(intentDetail);
                }
            }

            @Override
            public void onFooterClick() {
                Logger.d(TAG, "onFooterClick");
                int currentSize = healthGuidanceList.size();
                int remainder = currentSize/DEFAULT_COUNT;
                Logger.d(TAG, "current size = "+currentSize + "remainder = "+remainder);
                loadReports(remainder, DEFAULT_COUNT, false);
            }
        });
        loadReports(DEFAULT_PAGE, DEFAULT_COUNT, false);
    }

    private void setFooterView(RecyclerView recyclerView) {
        View footer = LayoutInflater.from(mContext).inflate(R.layout.list_footer, recyclerView, false);
        mAdapter.setFooterView(footer);
    }

    private void removeFooterView() {
        mAdapter.setFooterView(null);
    }

    static class HealthGuidanceHandler extends Handler {

        private WeakReference<HealthGuidanceFragment> healthGuidanceFragmentWeakReference;

        public HealthGuidanceHandler(HealthGuidanceFragment healthGuidanceFragment) {
            healthGuidanceFragmentWeakReference = new WeakReference<HealthGuidanceFragment>(healthGuidanceFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValues.MSG_REFRESH_TIME_OUT:
                    if (healthGuidanceFragmentWeakReference.get() != null) {
                        healthGuidanceFragmentWeakReference.get().stopRefresh();
                        healthGuidanceFragmentWeakReference.get().checkEmptyData();
                    }
                    break;
                case KEY_REPORT:
                    if (healthGuidanceFragmentWeakReference.get() != null) {
                        Logger.d(TAG, "handleMessage KEY_REPORT");
                        healthGuidanceFragmentWeakReference.get().stopRefresh();
                        healthGuidanceFragmentWeakReference.get().checkEmptyData();
                        Bundle bundle = msg.getData();
                        int loadSize = bundle.getInt(KEY_LOAD_SIZE, 0);
                        Logger.d(TAG, "loadSize = "+loadSize);
                        if (loadSize < DEFAULT_COUNT) {
                            Logger.d(TAG, "removeFooterView");
                            healthGuidanceFragmentWeakReference.get().removeFooterView();
                        }

                        healthGuidanceFragmentWeakReference.get().mAdapter.setList(healthGuidanceFragmentWeakReference.get().healthGuidanceList);
                    }
                    break;
                case KEY_FAILURE:
                    if (healthGuidanceFragmentWeakReference.get() != null) {
                        healthGuidanceFragmentWeakReference.get().stopRefresh();
                        healthGuidanceFragmentWeakReference.get().loadDataFromDb();
                    }
                    break;
            }
        }
    }

    private void checkEmptyData() {
        if (healthGuidanceList != null && healthGuidanceList.size() > 0) {
            setFooterView(mRecycleView);
            tvNoData.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.VISIBLE);
            removeFooterView();
        }
    }

    @Override
    public void onRefresh() {
        Logger.d(TAG, "onRefresh");
        loadReports(DEFAULT_PAGE, DEFAULT_COUNT, true);
        mHandler.sendEmptyMessageDelayed(ConstantValues.MSG_REFRESH_TIME_OUT, ConstantValues.REFRESH_TIME_OUT);
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(TAG, "onPause");
        stopRefresh();
    }

    private void stopRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            Logger.d(TAG, "stopRefresh!");
        }
    }

    private void loadDataFromDb() {
        queryBuilder.where(ReportsDao.Properties.Uid.eq(PreferenceUtil.getSelectedUserUID(mContext)));
        healthGuidanceList = queryBuilder.list();
        checkEmptyData();
        mAdapter.setList(healthGuidanceList);
    }

    private void loadReports(int page, int count, final boolean pull_refresh) {
        if (!NetWorkUtils.isNetworkConnected(mContext)) {
            ToastUtil.showShortToast(mContext, R.string.net_work_error);
            stopRefresh();
            loadDataFromDb();
            return;
        }
        OKHttpClientManager.getInstance().getAsync(CommonRequest.getUserReports(PreferenceUtil.getSelectedUserUID(mContext), page, count), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "onFailure");
                mHandler.sendEmptyMessage(KEY_FAILURE);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                Logger.d(TAG, "onResponse code = "+response.code() + "responseContent = "+responseContent);
                Gson gson = new Gson();
                ReportData reportData = gson.fromJson(responseContent, ReportData.class);
                List<Reports> tempList = reportData.getData().getReports();
                int oldSize = healthGuidanceList.size();
                if (pull_refresh) {
                    healthGuidanceList.clear();
                    mAdapter.notifyItemRangeRemoved(0, oldSize);
                }
                healthGuidanceList.addAll(tempList);
                mAdapter.notifyItemRangeInserted(oldSize, tempList.size());
                DeleteQuery<Reports> deleteQuery = queryBuilder.where(ReportsDao.Properties.Uid.eq(PreferenceUtil.getSelectedUserUID(mContext))).buildDelete();
                deleteQuery.executeDeleteWithoutDetachingEntities();
                for (Reports reports : healthGuidanceList) {
                    reportsDao.insert(reports);
                }
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt(KEY_LOAD_SIZE, tempList.size());
                msg.setData(bundle);
                msg.what = KEY_REPORT;
                mHandler.sendMessage(msg);
//                mHandler.sendEmptyMessage(KEY_REPORT);
            }
        });
    }
}




