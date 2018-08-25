package com.njzhikejia.echohealth.healthlife.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by 16222 on 2018/5/9.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list ;
    private String[] titles;
    private boolean isScroll;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
    }

    public ViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        list = new ArrayList<>();
        this.titles = titles;
    }

    public boolean isScroll() {
        return isScroll;
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }



    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void addFragment(Fragment fragment) {
        list.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > 0) {
            return titles[position];
        }
        return super.getPageTitle(position);
    }
}
