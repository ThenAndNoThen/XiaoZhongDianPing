package com.example.administrator.xiaozhongdianping.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.presenter.Main.OrderDetailPresenterImpl;
import com.example.administrator.xiaozhongdianping.view.main.OrderDetailView;

public class OrderDetailActivity extends AppCompatActivity implements OrderDetailView, View.OnClickListener {

    private TextView mtvGoodsName,mtvOrderID,mtvOrderNum,mtvOrdertotalCost,mtvOrderTime,mtvUseKey,mtvGotoComment;
    private LinearLayout mlayoutPay,mlayoutComment,layoutBack,layoutQRcode;
    private RatingBar ratingBar;
    private Button btnPay;
    public  OrderDetailPresenterImpl orderDetailPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        //初始化控件
        mtvGoodsName = (TextView) findViewById(R.id.tv_goodsname);
        mtvOrderID  = (TextView) findViewById(R.id.tv_orderid);
        mtvOrderNum  = (TextView) findViewById(R.id.tv_ordernum);
        mtvOrdertotalCost = (TextView) findViewById(R.id.tv_ordertotalcost);
        mtvOrderTime = (TextView) findViewById(R.id.tv_ordertime);
        mtvUseKey = (TextView) findViewById(R.id.tv_order_usekey);
        mtvGotoComment = (TextView) findViewById(R.id.tv_order_gotocomment);
        mlayoutPay = (LinearLayout) findViewById(R.id.layout_pay);
        mlayoutComment = (LinearLayout) findViewById(R.id.layout_comment);
        ratingBar = (RatingBar) findViewById(R.id.rb_rating_order);
        layoutBack = (LinearLayout) findViewById(R.id.layoutBack);
        layoutQRcode = (LinearLayout) findViewById(R.id.layout_QRcode);
        btnPay= (Button) findViewById(R.id.btn_pay);

        //设置数据
        orderDetailPresenter=new OrderDetailPresenterImpl(this);

        //设置监听
        mlayoutPay.setOnClickListener(this);
        mlayoutComment.setOnClickListener(this);
        layoutBack.setOnClickListener(this);


    }

    @Override
    public void showData(OrderBean order) {
        mtvOrderID.setText(order.getmOrderID());
        mtvGoodsName.setText(order.getmGoodsName());
        mtvOrderNum.setText(String.valueOf(order.getmGoodsNum()));
        mtvOrderTime.setText(order.getmOrderTime());
        mtvOrdertotalCost.setText(doubleToString(order.getmTotalCost())+"元");
        mtvUseKey.setText(order.getmUseKey());
        ratingBar.setRating((float)order.getmRating());
        switch (order.getmOrderState()){
            case 0:
                mlayoutPay.setVisibility(View.VISIBLE);
                mlayoutComment.setVisibility(View.INVISIBLE);
                layoutQRcode.setVisibility(View.INVISIBLE);
                break;
            case 1:
                mtvGotoComment.setText("去评价");
                mlayoutPay.setVisibility(View.INVISIBLE);
                mlayoutComment.setVisibility(View.VISIBLE);
                layoutQRcode.setVisibility(View.VISIBLE);
                mlayoutComment.setClickable(true);
                break;
            case 2:
                mtvGotoComment.setText(doubleToString(order.getmRating())+"分");
                mlayoutPay.setVisibility(View.INVISIBLE);
                mlayoutComment.setVisibility(View.VISIBLE);
                layoutQRcode.setVisibility(View.VISIBLE);
                mlayoutComment.setClickable(false);
                break;
            case -1:
                btnPay.setText("已取消");
                btnPay.setBackgroundColor(getResources().getColor(R.color.coloryDarkGray));
                mlayoutPay.setVisibility(View.VISIBLE);
                mlayoutComment.setVisibility(View.INVISIBLE);
                layoutQRcode.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void gotoOrderCommentActivity() {
        Intent intent=new Intent(this,OrderCommentActivity.class);
        startActivity(intent);
    }

    public String doubleToString( double num){
        String tmp=String.valueOf(num);
        return tmp.substring(0,tmp.indexOf(".")+2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layoutBack:
                finish();
                break;
            case R.id.layout_comment:
                gotoOrderCommentActivity();
                break;
            case R.id.layout_pay:
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        orderDetailPresenter.initData();
    }


}
