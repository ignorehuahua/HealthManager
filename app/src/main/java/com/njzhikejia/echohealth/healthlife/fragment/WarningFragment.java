package com.njzhikejia.echohealth.healthlife.fragment;

import android.content.Context;
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
import com.njzhikejia.echohealth.healthlife.adapter.WarnAdapter;
import com.njzhikejia.echohealth.healthlife.entity.rule.RuleResult;
import com.njzhikejia.echohealth.healthlife.entity.rule.WarnRule;
import com.njzhikejia.echohealth.healthlife.entity.warn.Notices;
import com.njzhikejia.echohealth.healthlife.entity.warn.WarnNoticesData;
import com.njzhikejia.echohealth.healthlife.greendao.DaoSession;
import com.njzhikejia.echohealth.healthlife.greendao.NoticesDao;
import com.njzhikejia.echohealth.healthlife.greendao.ReportsDao;
import com.njzhikejia.echohealth.healthlife.greendao.RuleResultDao;
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
import java.util.concurrent.CopyOnWriteArrayList;

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
    private List<Notices> warnInfoList;
    private Context mContext;
    private WarnHandler mHandler;
    private static final int KEY_WARN = 22;
    private static final int KEY_FAILURE = 23;
    private static final int KEY_GET_RULE_SUCCESS = 24;
    private static final int KEY_GET_RULE_FAILURE = 25;
    private DaoSession mDaoSession;
    private NoticesDao noticesDao;
    private TextView tvNoData;
    private QueryBuilder<Notices> queryBuilder;
    private RuleResultDao ruleResultDao;
    private QueryBuilder<RuleResult> ruleResultQueryBuilder;
    private List<RuleResult> ruleResultList;
    private RuleResult ruleResult;
    private int mPage;
    private int mCount;
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_COUNT = 8;
    private static final String KEY_LOAD_SIZE = "key_load_size";
    private boolean pull_refresh = false;

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
        mHandler = new WarnHandler(this);
        initSession();
        initView(view);
        return view;
    }

    private void initSession() {
        HealthLifeApplication mApplication = (HealthLifeApplication) getActivity().getApplication();
        mDaoSession = mApplication.getDaoSession();
        noticesDao = mDaoSession.getNoticesDao();
        ruleResultDao = mDaoSession.getRuleResultDao();
        queryBuilder = noticesDao.queryBuilder();
        ruleResultQueryBuilder = ruleResultDao.queryBuilder();
    }

    private void initView(View view) {
        tvNoData = view.findViewById(R.id.tv_no_data);
        tvNoData.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.toolbar);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecycleView = view.findViewById(R.id.recycle_view_data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(layoutManager);
        warnInfoList = new CopyOnWriteArrayList<>();
        mAdapter = new WarnAdapter(mContext, warnInfoList);
        mRecycleView.setAdapter(mAdapter);
        getWarnRules();
        loadWarnNotices(DEFAULT_PAGE, DEFAULT_COUNT, false);
        mAdapter.setOnFooterClickListener(new WarnAdapter.OnFooterClickListener() {
            @Override
            public void onFooterClick() {
                Logger.d(TAG, "onFooterClick");
                getWarnRules();
                int currentSize = warnInfoList.size();
                int remainder = currentSize/8;
                Logger.d(TAG, "current size = "+currentSize + "remainder = "+remainder);
                loadWarnNotices(remainder, DEFAULT_COUNT, false);
            }
        });
    }

    private void setFooterView(RecyclerView recyclerView) {
        View footer = LayoutInflater.from(mContext).inflate(R.layout.list_footer, recyclerView, false);
        mAdapter.setFooterView(footer);
    }

    private void removeFooterView() {
        mAdapter.setFooterView(null);
    }

    private void checkEmptyData() {
        if (warnInfoList != null && warnInfoList.size() > 0) {
            setFooterView(mRecycleView);
            tvNoData.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.VISIBLE);
            removeFooterView();
        }
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
        loadWarnNotices(DEFAULT_PAGE, DEFAULT_COUNT, true);
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
                    if (warningFragmentWeakReference.get() != null) {
                        warningFragmentWeakReference.get().stopRefresh();
                        warningFragmentWeakReference.get().checkEmptyData();
                    }
                    break;
                case KEY_WARN:
                    if (warningFragmentWeakReference.get() != null) {
                        warningFragmentWeakReference.get().stopRefresh();
                        warningFragmentWeakReference.get().checkEmptyData();
                        Bundle bundle = msg.getData();
                        int loadSize = bundle.getInt(KEY_LOAD_SIZE, 0);
                        if (loadSize < DEFAULT_COUNT) {
                            warningFragmentWeakReference.get().removeFooterView();
                        }
                        warningFragmentWeakReference.get().mAdapter.setlist(warningFragmentWeakReference.get().warnInfoList);
                    }
                    break;
                case KEY_FAILURE:
                    if (warningFragmentWeakReference.get() != null) {
                        warningFragmentWeakReference.get().stopRefresh();
                        warningFragmentWeakReference.get().loadDataFromDb();
                    }
                    break;
                case KEY_GET_RULE_SUCCESS:
                    break;
                case KEY_GET_RULE_FAILURE:
                    if (warningFragmentWeakReference.get() != null) {
                        warningFragmentWeakReference.get().loadWarnRulesFromDb();
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

    private void loadDataFromDb() {
        loadWarnRulesFromDb();
        queryBuilder.where(NoticesDao.Properties.Uid.eq(PreferenceUtil.getSelectedUserUID(mContext)));
        warnInfoList = queryBuilder.list();
        checkEmptyData();
        mAdapter.setlist(warnInfoList);
    }

    private void loadWarnNotices(int page, int count, final boolean pull_refresh) {
        if (!NetWorkUtils.isNetworkConnected(mContext)) {
            ToastUtil.showShortToast(mContext, R.string.net_work_error);
            stopRefresh();
            loadDataFromDb();
            return;
        }
        OKHttpClientManager.getInstance().getAsync(CommonRequest.getUserWarnInfo(PreferenceUtil.getSelectedUserUID(mContext), page, count), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(TAG, "loadWarnNotices onFailure");
                mHandler.sendEmptyMessage(KEY_FAILURE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseContent = response.body().string();
                Logger.d(TAG, "code = "+response.code() + "responseContent = "+responseContent);
                Gson gson = new Gson();
                WarnNoticesData warnNoticesData = gson.fromJson(responseContent, WarnNoticesData.class);
                List<Notices> tempList = warnNoticesData.getData().getNotices();
                int oldSize = warnInfoList.size();
                if (pull_refresh) {
                    warnInfoList.clear();
                    mAdapter.notifyItemRangeRemoved(0, oldSize);
                }
                warnInfoList.addAll(tempList);
                mAdapter.notifyItemRangeInserted(oldSize, tempList.size());
                DeleteQuery<Notices> deleteQuery = queryBuilder.where(NoticesDao.Properties.Uid.eq(PreferenceUtil.getSelectedUserUID(mContext))).buildDelete();
                deleteQuery.executeDeleteWithoutDetachingEntities();
                for (Notices notices : warnInfoList) {
                    noticesDao.insert(notices);
                }

                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt(KEY_LOAD_SIZE, tempList.size());
                msg.setData(bundle);
                msg.what = KEY_WARN;
                mHandler.sendMessage(msg);
//                mHandler.sendEmptyMessage(KEY_WARN);
            }
        });
    }

    private void getWarnRules() {
        if (!NetWorkUtils.isNetworkConnected(mContext)) {
            ToastUtil.showShortToast(mContext, R.string.net_work_error);
            mHandler.sendEmptyMessage(KEY_GET_RULE_FAILURE);
            return;
        }

        OKHttpClientManager.getInstance().getAsync(CommonRequest.getWarnRuleRequest(PreferenceUtil.getSelectedUserUID(mContext)), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.d(TAG, "get warn rules failure");
                mHandler.sendEmptyMessage(KEY_GET_RULE_FAILURE);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                Logger.d(TAG, "code = "+response.code() + "content = "+content);
                Gson gson = new Gson();
                WarnRule warnRule = gson.fromJson(content, WarnRule.class);
                String data = warnRule.getData().getRules().getData();
                Logger.d(TAG, "data = "+data);
                Gson gsonRule = new Gson();
                ruleResult = gsonRule.fromJson(data, RuleResult.class);
                int uid = warnRule.getData().getRules().getUid();
                ruleResult.setUid(uid);
                mAdapter.setRuleResult(ruleResult);
                int deletedUid = PreferenceUtil.getSelectedUserUID(mContext);
                if (uid == 0) {
                    deletedUid = 0;
                }
                DeleteQuery<RuleResult> deleteQuery = ruleResultQueryBuilder.where(RuleResultDao.Properties.Uid.eq(deletedUid)).buildDelete();
                deleteQuery.executeDeleteWithoutDetachingEntities();
                ruleResultDao.insert(ruleResult);
                mHandler.sendEmptyMessage(KEY_GET_RULE_SUCCESS);
            }
        });
    }

    private void loadWarnRulesFromDb() {
        ruleResultList = new ArrayList<>();
        ruleResultQueryBuilder.where(RuleResultDao.Properties.Uid.eq(PreferenceUtil.getSelectedUserUID(mContext)));
        ruleResultList = ruleResultQueryBuilder.list();
        Logger.d(TAG, "select uid result size = "+ruleResultList.size());
        if (ruleResultList.size() == 0) {
            QueryBuilder<RuleResult> mQueryBuilder = ruleResultDao.queryBuilder();
            mQueryBuilder.where(RuleResultDao.Properties.Uid.eq(0));
            ruleResultList = mQueryBuilder.list();
            Logger.d(TAG, "default result size = "+ruleResultList.size());
        }

        if (ruleResultList.size() > 0) {
            ruleResult = ruleResultList.get(0);
            mAdapter.setRuleResult(ruleResult);
        }
    }
}
