package com.yamankod.murat.map;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class Tab_PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public Tab_PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab_SendSmsAndLocation tab1 = new Tab_SendSmsAndLocation();
                return tab1;
            case 1:
                Tab_Call tab2 = new Tab_Call();
                return tab2;
            case 2:
               Tab_User tab3 = new Tab_User();
               return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}