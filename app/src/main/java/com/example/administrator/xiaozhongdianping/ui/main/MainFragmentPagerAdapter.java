package com.example.administrator.xiaozhongdianping.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/28 0028.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> mFragmentList;
    public MainFragmentPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList=fragmentList;

    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


}
