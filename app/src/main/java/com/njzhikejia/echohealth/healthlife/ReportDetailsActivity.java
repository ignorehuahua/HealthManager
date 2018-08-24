package com.njzhikejia.echohealth.healthlife;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.njzhikejia.echohealth.healthlife.util.ConstantValues;

/**
 * Created by 16222 on 2018/8/24.
 */

public class ReportDetailsActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
        initView();
    }


    private void initView() {
        mWebView = findViewById(R.id.web_view);
        initWebView(mWebView);
        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra(ConstantValues.KEY_REPORT_DETAIL_ID, 1);
            mWebView.loadUrl("http://120.55.22.115/user/sleepReport.html?id=20&uid=1");
        }
    }

    private void initWebView(WebView webView) {
        //支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
// 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
//扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
//自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
