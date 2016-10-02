package com.itheima.viewpagertransformdemo;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * 创建者: Leon
 * 创建时间: 2016/10/2 9:38
 * 描述： TODO
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static final String TAG = "ZoomOutPageTransformer";

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    /**
     * Apply a property transformation to the given page.
     *
     * @param view Apply the transformation to this page
     * @param position Position of page relative to the current front-and-center
     *                 position of the pager. 0 is front and center. 1 is one full
     *                 page position to the right, and -1 is one page position to the left.
     */
    public void transformPage(View view, float position) {
        Log.d(TAG, "transformPage " + position);
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            //取值范围 0.5 + （0.85 - 0.85）/ (1 - 0.85) * (1 - 0.5) = 0.5
            // 0.5 + (1 - 0.85) /  (1 - 0.85) * (1 - 0.5) = 1
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}
