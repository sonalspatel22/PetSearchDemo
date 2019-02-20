package com.example.petsearchdemo.util;

import android.support.v4.widget.SwipeRefreshLayout;

public final class ViewUtils {

    public static void setSwipeRefreshing(final SwipeRefreshLayout swipeRefreshLayout, final boolean refreshing) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(refreshing);
            }
        });
    }
}
