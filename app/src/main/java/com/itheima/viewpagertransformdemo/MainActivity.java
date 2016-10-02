package com.itheima.viewpagertransformdemo;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ViewPager mViewPager;

    private int[] mImages = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setAdapter(mPagerAdapter);
//        mViewPager.setPageTransformer(false, new ZoomOutPageTransformer());
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                Log.d(TAG, "onScrollChange: ViewPager's mScrollX " + scrollX );
            }
        });
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView page  = new ImageView(MainActivity.this);
            page.setImageResource(mImages[position]);
            page.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(page);
            return page;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    };
}
