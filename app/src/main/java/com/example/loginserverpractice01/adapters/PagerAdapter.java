package com.example.loginserverpractice01.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.loginserverpractice01.fragments.FragmentOne;
import com.example.loginserverpractice01.fragments.FragmentTwo;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTab;

    Fragment frag1, frag2;

    public PagerAdapter(FragmentManager fm, int numOfTab){

        super(fm);
        mNumOfTab = numOfTab;

    }

    @Override
    public Fragment getItem(int i) {

        Fragment fg = null;

            if (i == 0) {
                if (frag1 == null){
                    frag1 = new FragmentOne();
                }
                fg = frag1;
            }else if (i == 1){
                if (frag2 == null){
                    frag2 = new FragmentTwo();
                }
                fg = frag2;
            }

        return fg;
    }

    @Override
    public int getCount() {
        return mNumOfTab;
    }
}
