package com.application.maje.motionheaderviewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AdapterDetailFragment extends FragmentStatePagerAdapter {

    private ArrayList<TabInfo> mTabs = new ArrayList<>();
    private Context mContext = null;
    private SparseArray<Fragment> mActive = null;

    public AdapterDetailFragment(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        this.mActive = new SparseArray<>();
    }

    @Override
    public Fragment getItem(int i) {
        if (mActive.get(i) != null) {
            return mActive.get(i);
        }
        TabInfo tab = mTabs.get(i);
        Bundle args = tab.getParameters();
        if (args == null) {
            args = new Bundle();
        }
        args.putInt("position", i);
        Fragment fragment = Fragment.instantiate(mContext, tab.getClss().getName(), args);
        mActive.put(i, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        mActive.remove(position);
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).getTitle();
    }

    public void addTab(Class<?> _class, String title, Bundle arguments) {
        TabInfo tab = new TabInfo(_class, title, arguments);
        mTabs.add(tab);
    }

    static final class TabInfo {

        private Class<?> clss;
        private String title;
        private Bundle parameters;

        TabInfo(Class<?> clss, String title, Bundle parameters) {
            this.clss = clss;
            this.title = title;
            this.parameters = parameters;
        }

        public Class<?> getClss() {
            return clss;
        }

        public String getTitle() {
            return title;
        }

        public Bundle getParameters() {
            return parameters;
        }
    }

    public Fragment getCurrentFragment(int position) {
        if (mActive != null) {
            return mActive.get(position);
        } else {
            return null;
        }
    }
}
