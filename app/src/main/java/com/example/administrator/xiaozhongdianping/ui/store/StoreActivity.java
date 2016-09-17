package com.example.administrator.xiaozhongdianping.ui.store;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.bean.GoodsBean;
import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.bean.StoreBean;
import com.example.administrator.xiaozhongdianping.presenter.store.StorePresenterImpl;
import com.example.administrator.xiaozhongdianping.util.MyApplication;
import com.example.administrator.xiaozhongdianping.view.store.StoreView;

import java.util.List;

public class StoreActivity extends AppCompatActivity implements StoreView, View.OnClickListener {
    private Toolbar mToolbar;//头部标题栏
    private TextView mtvStoreName,mtvStoreRating,mtvStoreAvgCost,mtvStoreAddress,mtvStoreTel;//商家信息控件
    private RatingBar mRbRating;//星级bar
    private RecyclerView mrvGoods;//商品recyclerview
    private RecyclerView mrvComments;//评论recyclerview
    private StorePresenterImpl mStorePresenter;//本页面的presenter
    public  StoreGoodsRecyclerViewAdapter mgoodsAdapter;//商品recyclerview的适配器
    private StoreCommentsRecyclerViewAdapter mcommentAdapter;//评论recyclerview的适配器
    private Button btnAll,btnGood,btnNor,btnBad;
    private LinearLayout layoutBack;
    private NestedScrollView mnsv;//滑动布局
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        //初始化控件
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRbRating = (RatingBar) findViewById(R.id.ratingbar);
        mtvStoreName = (TextView) findViewById(R.id.tv_storename);
        mtvStoreRating = (TextView) findViewById(R.id.tv_rating);
        mtvStoreAvgCost = (TextView) findViewById(R.id.tv_avecost);
        mtvStoreAddress = (TextView) findViewById(R.id.tv_storeaddress);
        mtvStoreTel = (TextView) findViewById(R.id.tv_storetel);
        mrvGoods = (RecyclerView) findViewById(R.id.rv_goods);
        mnsv = (NestedScrollView) findViewById(R.id.nsv);
        layoutBack = (LinearLayout) findViewById(R.id.layoutBack);
        mrvComments = (RecyclerView) findViewById(R.id.rv_comments);
        btnAll = (Button) findViewById(R.id.all_evalute_button);
        btnGood = (Button) findViewById(R.id.good_evalute_button);
        btnNor = (Button) findViewById(R.id.middle_evalute_button);
        btnBad = (Button) findViewById(R.id.bad_evalute_button);

        //设置数据
        mStorePresenter=new StorePresenterImpl(this);
        mToolbar.setTitle("商店详情");
        mStorePresenter.getInitData();
        mgoodsAdapter=new StoreGoodsRecyclerViewAdapter(this);
        mcommentAdapter=new StoreCommentsRecyclerViewAdapter(this);
        mrvGoods.setLayoutManager(new LinearLayoutManager(this));
        mrvComments.setLayoutManager(new LinearLayoutManager(this));
        mrvGoods.setAdapter(mgoodsAdapter);
        mrvComments.setAdapter(mcommentAdapter);


        //设置监听
        layoutBack.setOnClickListener(this);
        btnAll.setOnClickListener(this);
        btnGood.setOnClickListener(this);
        btnNor.setOnClickListener(this);
        btnBad.setOnClickListener(this);


    }


    @Override
    public void showStoreInfo(PoiItem poiItem, StoreBean storeBean) {
        mRbRating.setRating((float)storeBean.getmStoreRating());
        mtvStoreName.setText(poiItem.getTitle());
        if(storeBean.getmStoreAvgCost()==0){
            mtvStoreRating.setText("暂未入住");
            mtvStoreAvgCost.setText("");
        }
        else {
            mtvStoreRating.setText(doubleToString(storeBean.getmStoreRating()) + "分");
            mtvStoreAvgCost.setText("人均￥" + storeBean.getmStoreAvgCost());
        }
        mtvStoreAddress.setText(poiItem.getSnippet());
        mtvStoreTel.setText(poiItem.getTel());
    }

    @Override
    public void updateGoodsList(List<GoodsBean> goodsBeanList) {
        mgoodsAdapter.setGoodsBeanList(goodsBeanList);
        mgoodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateOrderList(List<OrderBean> orderBeanList) {
        mcommentAdapter.setOrderBeanList(orderBeanList);
        mcommentAdapter.notifyDataSetChanged();

    }

    public String doubleToString( double num){
        String tmp=String.valueOf(num);
        return tmp.substring(0,tmp.indexOf(".")+2);
    }
    @Override
    public void setNestedScrollViewToTop(){
        mrvGoods.setFocusable(false);
        mnsv.smoothScrollTo(0,20);
    }

    @Override
    public void toOrderConfirmActivity(GoodsBean goodsBean) {
        ((MyApplication)this.getApplication()).setGoodsInOrder(goodsBean);
        Intent intent=new Intent(this,OrderConfirmActivity.class);
        startActivity(intent);
    }

    @Override
    public void setCommentTypeNum(int all, int good, int nor, int bad) {
        btnAll.setText("全部("+all+")");
        btnGood.setText("好评("+good+")");
        btnNor.setText("中评("+nor+")");
        btnBad.setText("差评("+bad+")");
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.layoutBack:
                finish();
                break;
            case R.id.all_evalute_button:
                mStorePresenter.clickCommentType(0);
                btnAll.setBackgroundColor(getResources().getColor(R.color.coloryOrange));
                btnAll.setTextColor(getResources().getColor(R.color.colorWhite));

                btnGood.setBackground(getResources().getDrawable(R.drawable.button_bg));
                btnGood.setTextColor(getResources().getColor(R.color.coloryOrange));

                btnNor.setBackground(getResources().getDrawable(R.drawable.button_bg));
                btnNor.setTextColor(getResources().getColor(R.color.coloryOrange));

                btnBad.setBackground(getResources().getDrawable(R.drawable.button_bg));
                btnBad.setTextColor(getResources().getColor(R.color.coloryOrange));
                break;
            case R.id.good_evalute_button:
                mStorePresenter.clickCommentType(1);
                btnGood.setBackgroundColor(getResources().getColor(R.color.coloryOrange));
                btnGood.setTextColor(getResources().getColor(R.color.colorWhite));

                btnAll.setBackground(getResources().getDrawable(R.drawable.button_bg));
                btnAll.setTextColor(getResources().getColor(R.color.coloryOrange));

                btnNor.setBackground(getResources().getDrawable(R.drawable.button_bg));
                btnNor.setTextColor(getResources().getColor(R.color.coloryOrange));

                btnBad.setBackground(getResources().getDrawable(R.drawable.button_bg));
                btnBad.setTextColor(getResources().getColor(R.color.coloryOrange));
                break;
            case R.id.middle_evalute_button:
                mStorePresenter.clickCommentType(2);
                btnNor.setBackgroundColor(getResources().getColor(R.color.coloryOrange));
                btnNor.setTextColor(getResources().getColor(R.color.colorWhite));

                btnGood.setBackground(getResources().getDrawable(R.drawable.button_bg));
                btnGood.setTextColor(getResources().getColor(R.color.coloryOrange));

                btnAll.setBackground(getResources().getDrawable(R.drawable.button_bg));
                btnAll.setTextColor(getResources().getColor(R.color.coloryOrange));

                btnBad.setBackground(getResources().getDrawable(R.drawable.button_bg));
                btnBad.setTextColor(getResources().getColor(R.color.coloryOrange));
                break;
            case R.id.bad_evalute_button:
                mStorePresenter.clickCommentType(3);
                btnBad.setBackgroundColor(getResources().getColor(R.color.coloryOrange));
                btnBad.setTextColor(getResources().getColor(R.color.colorWhite));

                btnGood.setBackground(getResources().getDrawable(R.drawable.button_bg));
                btnGood.setTextColor(getResources().getColor(R.color.coloryOrange));

                btnNor.setBackground(getResources().getDrawable(R.drawable.button_bg));
                btnNor.setTextColor(getResources().getColor(R.color.coloryOrange));

                btnAll.setBackground(getResources().getDrawable(R.drawable.button_bg));
                btnAll.setTextColor(getResources().getColor(R.color.coloryOrange));
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        mStorePresenter.getGoodsList();
        mStorePresenter.getOrderList();
    }

}
