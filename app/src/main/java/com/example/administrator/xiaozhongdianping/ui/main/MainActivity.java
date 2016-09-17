package com.example.administrator.xiaozhongdianping.ui.main;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.view.main.MainVIew;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,MainVIew {

    //选项卡有关控件
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragmentList;
    private TabLayout mTabLayout;
    private ImageView mImgStore,mImgOrder,mImgMine;
    private TextView mTvStore,mTvOrder,mTvMine;

    //3个页面frament
    private StoreFragment storeFragment;
    private OrderFragment orderFragment;
    private MineFragment mineFragment;
    //底部选项卡
    private LinearLayout mLayoutStore, mLayoutOrder, mLayoutMine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        mViewPager = (ViewPager) findViewById(R.id.viewPager_mainActivity);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_mainActivity);
        mLayoutStore = (LinearLayout) findViewById(R.id.layoutStore_mainActivity);
        mLayoutOrder = (LinearLayout) findViewById(R.id.layoutOrder_mainActivity);
        mLayoutMine = (LinearLayout) findViewById(R.id.layoutMine_mainActivity);
        mImgStore = (ImageView) findViewById(R.id.imgStore_mainActivity);
        mImgOrder = (ImageView) findViewById(R.id.imgOrder_mainActivity);
        mImgMine = (ImageView) findViewById(R.id.imgMine_mainActivity);
        mTvStore = (TextView) findViewById(R.id.tvStore_mainActivity);
        mTvOrder = (TextView) findViewById(R.id.tvOrder_mainActivity);
        mTvMine = (TextView) findViewById(R.id.tvMine_mainActivity);


        //配置数据和设置监听
        storeFragment = StoreFragment.newInstance(null,null);
        orderFragment = OrderFragment.newInstance(null,null,this);
        mineFragment = MineFragment.newInstance(null,null);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(storeFragment);
        mFragmentList.add(orderFragment);
        mFragmentList.add(mineFragment);
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
       // mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setAdapter(adapter);//设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//tablayout与viewpager绑定
        mViewPager.setCurrentItem(0);//设置当前页为第一页
        mLayoutStore.setOnClickListener(this);
        mLayoutOrder.setOnClickListener(this);
        mLayoutMine.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(new MainOnPageChangeListener());//设置翻页监听
        mTvStore.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));//设置文字颜色
        mImgStore.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));//设置图片填充色


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.layoutStore_mainActivity:
                mViewPager.setCurrentItem(0);
                break;

            case R.id.layoutOrder_mainActivity:
                mViewPager.setCurrentItem(1);
                break;

            case R.id.layoutMine_mainActivity:
                mViewPager.setCurrentItem(2);
                break;

        }
    }

    class MainOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setTabColor(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 设置底部标签的颜色和动画
     */
    @Override
    public void setTabColor(int tabPosition) {
        Animator animator = AnimatorInflater.loadAnimator(MainActivity.this, R.animator.anim_rotation_circle);
        switch (tabPosition) {
            case 0:
                //设置旋转动画
                animator.setTarget(mImgStore);
                animator.setInterpolator(new AnticipateOvershootInterpolator());
                animator.start();
                //设置文字颜色
                mTvStore.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                mTvOrder.setTextColor(Color.WHITE);
                mTvMine.setTextColor(Color.WHITE);
                //设置图片颜色
                mImgStore.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                mImgOrder.setColorFilter(Color.WHITE);
                mImgMine.setColorFilter(Color.WHITE);
                break;
            case 1:
                //设置旋转动画
                animator.setTarget(mImgOrder);
                animator.setInterpolator(new AnticipateOvershootInterpolator());
                animator.start();
                //设置文字颜色
                mTvStore.setTextColor(Color.WHITE);
                mTvOrder.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                mTvMine.setTextColor(Color.WHITE);
                //设置图片颜色
                mImgStore.setColorFilter(Color.WHITE);
                mImgOrder.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                mImgMine.setColorFilter(Color.WHITE);
                break;
            case 2:
                //设置旋转动画
                animator.setTarget(mImgMine);
                animator.setInterpolator(new AnticipateOvershootInterpolator());
                animator.start();
                //设置文字颜色
                mTvStore.setTextColor(Color.WHITE);
                mTvOrder.setTextColor(Color.WHITE);
                mTvMine.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                //设置图片颜色
                mImgStore.setColorFilter(Color.WHITE);
                mImgOrder.setColorFilter(Color.WHITE);
                mImgMine.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                break;
            default:
                break;
        }
    }
}

