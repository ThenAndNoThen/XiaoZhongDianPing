package com.example.administrator.xiaozhongdianping.presenter.Main;

import android.content.Context;
import android.widget.Toast;

import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.model.OrderModel;
import com.example.administrator.xiaozhongdianping.presenter.store.OrderConfirmPresenter;
import com.example.administrator.xiaozhongdianping.ui.main.MainActivity;
import com.example.administrator.xiaozhongdianping.util.MyApplication;
import com.example.administrator.xiaozhongdianping.view.main.OrderHistoryView;

import java.util.List;

/**
 * Created by Administrator on 2016/5/8 0008.
 */
public class OrderHistoryPresenterImpl implements OrderHistoryPresenter, OrderModel.OrderModelListener {
    Context context;
    MyApplication myApplication;
    OrderHistoryView mOrderHistoryView ;
    OrderModel orderModel;

    public OrderHistoryPresenterImpl(Context context, OrderHistoryView view){
        this.context=context;
        this.mOrderHistoryView=view;
        myApplication=(MyApplication) ((MainActivity)context).getApplication();
        orderModel=new OrderModel(this);
    }

    @Override
    public void initData() {
        orderModel.getOrdersByUser(myApplication.getPresentUser().getmUserID());
    }

    @Override
    public void clickRecclerViewItem(OrderBean order) {
        myApplication.setShowOrderBean(order);
        mOrderHistoryView.toOrderDetailActivity();
    }

    @Override
    public void writeToDatabaseOnSuccess() {

    }

    @Override
    public void updataOrderOnSuccess(OrderBean order) {

    }



    @Override
    public void getOrdersOnSuccess(List<OrderBean> orderlist) {
        mOrderHistoryView.showData(orderlist);
    }

    @Override
    public void NetworkCommunicationsOnError() {
        Toast.makeText(context,"网络错误！",Toast.LENGTH_SHORT).show();
    }
}
