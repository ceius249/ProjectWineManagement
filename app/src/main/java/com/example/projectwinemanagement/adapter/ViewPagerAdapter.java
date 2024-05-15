package com.example.projectwinemanagement.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.projectwinemanagement.fragment.OriginalCountryFragment;
import com.example.projectwinemanagement.fragment.WineFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new WineFragment();

            case 1:
                return new OriginalCountryFragment();

            default:
                return new WineFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
