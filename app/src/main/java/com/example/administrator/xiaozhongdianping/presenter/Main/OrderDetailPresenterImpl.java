package com.example.administrator.xiaozhongdianping.presenter.Main;

import android.content.Context;

import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.model.OrderModel;
import com.example.administrator.xiaozhongdianping.ui.main.OrderDetailActivity;
import com.example.administrator.xiaozhongdianping.util.MyApplication;
import com.example.administrator.xiaozhongdianping.view.main.OrderDetailView;

import java.util.List;

/**
 * Created by Administrator on 2016/5/12 0012.
 */
public class OrderDetailPresenterImpl implements OrderDetailPresenter {
    Context context;
    OrderDetailView morderDetailView;
    OrderBean orderBean;
    MyApplication myApplication;
    public OrderDetailPresenterImpl(Context context){
        this.context=context;
        this.morderDetailView=(OrderDetailView)context;
        myApplication=(MyApplication) ((OrderDetailActivity)context).getApplication();
    }
    @Override
    public void initData() {
        orderBean=myApplication.getShowOrderBean();
        morderDetailView.showData(orderBean);
    }


}
