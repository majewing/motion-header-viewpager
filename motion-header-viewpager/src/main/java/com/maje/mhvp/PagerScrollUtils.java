package com.maje.mhvp;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;

public class PagerScrollUtils {

    public static boolean isFirstChildOnTop(View view) {
        if (view == null) {
            return true;
        }

        if (view instanceof AbsListView) {
            return isFirstChildOnTopInAbsListView((AbsListView) view);
        } else if (view instanceof ScrollView) {
            return isFirstChildOnTopInScrollView((ScrollView) view);
        } else {
            return true;
        }
    }

    private static boolean isFirstChildOnTopInAbsListView(AbsListView listView) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        if (firstVisiblePosition == 0) {
            final View child = listView.getChildAt(0);
            int top = child.getTop();
            return top >= 0;
        } else {
            return false;
        }
    }

    private static boolean isFirstChildOnTopInScrollView(ScrollView scrollView) {
        int scrollY = scrollView.getScrollY();
        return scrollY <= 0;
    }
}
