package com.hb.fatsecret.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.hb.fatsecret.fragment.Welcome1Fragment;
import com.hb.fatsecret.fragment.Welcome2Fragment;
import com.hb.fatsecret.fragment.Welcome3Fragment;
import com.hb.fatsecret.fragment.Welcome4Fragment;
import com.hb.fatsecret.fragment.Welcome5Fragment;
import com.hb.fatsecret.fragment.Welcome6Fragment;
import com.hb.fatsecret.fragment.Welcome7Fragment;
import com.hb.fatsecret.fragment.Welcome8Fragment;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        init();
    }

    private void init() {
        fragments = new ArrayList<>();
        fragments.add(new Welcome1Fragment());
        fragments.add(new Welcome2Fragment());
        fragments.add(new Welcome3Fragment());
        fragments.add(new Welcome4Fragment());
        fragments.add(new Welcome5Fragment());
        fragments.add(new Welcome6Fragment());
        fragments.add(new Welcome7Fragment());
        fragments.add(new Welcome8Fragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    public void setFragment2(boolean isShowFragment2) {
        if (isShowFragment2) {
            if (fragments.size() < 8) {
                fragments.add(1, new Welcome2Fragment());
                notifyDataSetChanged();
            }
        } else {
            if (fragments.size() == 8) {
                fragments.remove(1);
                notifyDataSetChanged();
            }
        }
    }

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        return POSITION_NONE;
//    }
}
