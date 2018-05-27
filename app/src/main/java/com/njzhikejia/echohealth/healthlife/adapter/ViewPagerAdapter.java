package com.njzhikejia.echohealth.healthlife.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 16222 on 2018/5/9.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list ;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
    }

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
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
}
