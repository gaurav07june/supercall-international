package com.example.gauravsingh.myapplication.adapter;

import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.gauravsingh.myapplication.fragment.ContactFragment;
import com.example.gauravsingh.myapplication.fragment.InfoFragment;
import com.example.gauravsingh.myapplication.fragment.KeypadFragment;
import com.example.gauravsingh.myapplication.fragment.RecentFragment;
import com.example.gauravsingh.myapplication.fragment.SettingFragment;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    int noOfPage;
    Fragment[] fragment = {new InfoFragment(), new RecentFragment(), new ContactFragment(),
            new KeypadFragment(), new SettingFragment()};
    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);

    }
    @Override
    public Fragment getItem(int position) {
        return fragment[position];
    }

    @Override
    public int getCount() {
        return fragment.length;
    }
}
