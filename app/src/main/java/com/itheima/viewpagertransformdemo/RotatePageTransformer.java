package com.itheima.viewpagertransformdemo;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * 创建者: Leon
 * 创建时间: 2016/10/2 16:21
 * 描述： TODO
 */
public class RotatePageTransformer implements ViewPager.PageTransformer {
    private static final String TAG = "RotatePageTransformer";
    private static final float MAX_ROTATION = 25f;

    @Override
    public void transformPage(View view, float position) {
        Log.d(TAG, "transformPage: " + position);
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setRotation(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page

            rotate(view, position);
            // Scale the page down (between MIN_SCALE and 1)
            view.setAlpha(1 + position);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            //1--> 0
            rotate(view, position);
            view.setAlpha(1 - position);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setRotation(0);
        }
    }

    private void rotate(View view, float position) {
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight());
        float rotation = position * MAX_ROTATION;
        view.setRotation(rotation);
    }
}
