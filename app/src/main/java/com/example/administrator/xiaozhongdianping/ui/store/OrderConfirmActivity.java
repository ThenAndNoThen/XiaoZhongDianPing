package com.example.administrator.xiaozhongdianping.ui.store;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.presenter.store.OrderConfirmPresenterImpl;
import com.example.administrator.xiaozhongdianping.util.MyApplication;
import com.example.administrator.xiaozhongdianping.view.store.OrderConfirmView;

public class OrderConfirmActivity extends AppCompatActivity implements OrderConfirmView, View.OnClickListener {
    private TextView mtvGoodsName,mtvGoodsNum,mtvGoodsSubtotal,mtvTotal,getMtvGoodsPrice;
    private ImageView mimgSub,mimgAdd;
    private Button mbtnSubmit;

    private OrderConfirmPresenterImpl orderConfirmPresenter;
    private MyApplication myApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        //初始化控件
        mtvGoodsName = (TextView) findViewById(R.id.goods_name_textview);
        mtvGoodsNum = (TextView) findViewById(R.id.goodsnum_textview);
        mtvGoodsSubtotal = (TextView) findViewById(R.id.subtotal_textview);
        mtvTotal = (TextView) findViewById(R.id.total_textview);
        mimgSub = (ImageView) findViewById(R.id.sub_imageView);
        mimgAdd = (ImageView) findViewById(R.id.add_submit_imageView);
        mbtnSubmit = (Button) findViewById(R.id.submit_button);
        getMtvGoodsPrice = (TextView) findViewById(R.id.goods_price_textview);

        //设置数据
        myApplication=(MyApplication) getApplication();
        orderConfirmPresenter=new OrderConfirmPresenterImpl(this);
        orderConfirmPresenter.initData();

        //设置监听
        mimgSub.setOnClickListener(this);
        mimgAdd.setOnClickListener(this);
        mbtnSubmit.setOnClickListener(this);
    }


    @Override
    public void initData(OrderBean orderBean) {
        mtvGoodsName.setText(orderBean.getmGoodsName());
        mtvGoodsNum.setText(String.valueOf(orderBean.getmGoodsNum()));
        getMtvGoodsPrice.setText("￥"+doubleToString(orderBean.getmTotalCost()));
        mtvGoodsSubtotal.setText("￥"+doubleToString(orderBean.getmTotalCost()));
        mtvTotal.setText("￥"+doubleToString(orderBean.getmTotalCost()));
    }

    public String doubleToString( double num){
        String tmp=String.valueOf(num);
        return tmp.substring(0,tmp.indexOf(".")+2);
    }
    @Override
    public void goodsNumChanged(OrderBean orderBean) {
        mtvGoodsNum.setText(String.valueOf(orderBean.getmGoodsNum()));
        mtvGoodsSubtotal.setText("￥"+doubleToString(orderBean.getmTotalCost()));
        mtvTotal.setText("￥"+doubleToString(orderBean.getmTotalCost()));
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sub_imageView:
                orderConfirmPresenter.clickSubButton();
                break;
            case R.id.add_submit_imageView:
                orderConfirmPresenter.clickAddButton();
                break;
            case R.id.submit_button:
                orderConfirmPresenter.clickSubmitButton();
                break;
        }
    }
}
