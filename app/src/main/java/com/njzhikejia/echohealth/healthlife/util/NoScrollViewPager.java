package com.njzhikejia.echohealth.healthlife.util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 16222 on 2018/8/25.
 */

public class NoScrollViewPager extends ViewPager {

    private boolean isScroll;
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isScroll() {
        return isScroll;
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isScroll && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isScroll && super.onInterceptTouchEvent(event);
    }
}
