package com.abed.rebtel.view.misc;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class VerticalEqualSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpaceHeight;

    public VerticalEqualSpaceItemDecoration(int mSpaceHeight) {
        this.mSpaceHeight = mSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mSpaceHeight;
        outRect.top = mSpaceHeight;
        outRect.left = 0;
        outRect.right = 0;
    }

}
