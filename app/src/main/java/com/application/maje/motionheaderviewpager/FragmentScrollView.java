package com.application.maje.motionheaderviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maje.mhvp.IPagerScroll;
import com.maje.mhvp.PagerScrollUtils;

public class FragmentScrollView extends Fragment implements IPagerScroll {

    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_scrollview, container, false);
        return mRootView;
    }

    @Override
    public boolean isFirstChildOnTop() {
        return PagerScrollUtils.isFirstChildOnTop(mRootView);
    }
}
