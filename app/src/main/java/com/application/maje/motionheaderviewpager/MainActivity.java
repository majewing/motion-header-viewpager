package com.application.maje.motionheaderviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.maje.mhvp.IPagerScroll;
import com.maje.mhvp.TouchPanelLayout;

public class MainActivity extends AppCompatActivity {

    private TouchPanelLayout mLayoutTouchPanel;
    private ImageView mImageView;
    private ViewPager mContentViewPager;
    private AdapterDetailFragment mAdapterContentDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.image_view);
        mContentViewPager = (ViewPager) findViewById(R.id.view_pager);
        mContentViewPager.setOffscreenPageLimit(1);
        mAdapterContentDetail = new AdapterDetailFragment(getSupportFragmentManager(), getApplicationContext());
        mAdapterContentDetail.addTab(FragmentScrollView.class, "TAB1", null);
        mAdapterContentDetail.addTab(FragmentListView.class, "TAB2", null);

        mContentViewPager.setAdapter(mAdapterContentDetail);

        mLayoutTouchPanel = (TouchPanelLayout) findViewById(R.id.panel_layout);
        mLayoutTouchPanel.setConfigCurrentPagerScroll(new TouchPanelLayout.IConfigCurrentPagerScroll() {
            @Override
            public IPagerScroll getCurrentPagerScroll() {
                if (mAdapterContentDetail != null) {
                    Fragment fragment = mAdapterContentDetail.getCurrentFragment(mContentViewPager.getCurrentItem());
                    if (fragment != null && fragment instanceof IPagerScroll) {
                        return (IPagerScroll) fragment;
                    }
                }
                return null;
            }

            @Override
            public float getActionBarHeight() {
                return 0;
            }
        });
        mLayoutTouchPanel.setOnViewUpdateListener(new TouchPanelLayout.OnViewUpdateListener() {
            @Override
            public void onAlphaChanged(float alpha) {
                mImageView.setAlpha(alpha);
            }

            @Override
            public void onJumpAlphaChanged(float alpha) {
                mImageView.setAlpha(alpha);
            }

            @Override
            public float getViewAlpha() {
                return mImageView.getAlpha();
            }
        });
    }
}
