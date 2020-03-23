package com.example.softxpert.utils;

import androidx.recyclerview.widget.LinearLayoutManager;

public class Utils {
    /**
     * method used to check if the recycler linear layout manager has reached the end or not
     *
     * @param layoutManager
     * @return
     */
    public static boolean isReachedEndOfRecycler(LinearLayoutManager layoutManager) {
        // get counts
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

        // check total item count
        if (totalItemCount == 0) {
            return true;
        } else {
            return ((visibleItemCount + pastVisibleItems) >= totalItemCount);
        }
    }
}
