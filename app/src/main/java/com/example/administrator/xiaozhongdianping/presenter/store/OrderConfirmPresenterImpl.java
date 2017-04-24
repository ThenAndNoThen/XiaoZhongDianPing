package com.example.administrator.xiaozhongdianping.presenter.store;

import android.content.Context;

import com.example.administrator.xiaozhongdianping.bean.GoodsBean;
import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.bean.StoreBean;
import com.example.administrator.xiaozhongdianping.model.OrderModel;
import com.example.administrator.xiaozhongdianping.ui.store.OrderConfirmActivity;
import com.example.administrator.xiaozhongdianping.util.MyApplication;
import com.example.administrator.xiaozhongdianping.view.store.OrderConfirmView;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2016/5/8 0008.
 */
public class OrderConfirmPresenterImpl implements OrderConfirmPresenter, OrderModel.OrderModelListener {
    private MyApplication myApplication;
    private OrderBean orderBean;
    private OrderConfirmView orderConfirmView;
    private OrderModel orderModel;
    private Context context;
    StoreBean storeBean;
    GoodsBean goodsBean;
    public OrderConfirmPresenterImpl(Context context){
        this.context=context;
        this.myApplication=(MyApplication) ((OrderConfirmActivity)context).getApplication();
        this.goodsBean=myApplication.getGoodsInOrder();
        this.storeBean=myApplication.getShowStoreBean();
        this.orderConfirmView=(OrderConfirmView)context;
        this.orderModel=new OrderModel(this);

    }


    @Override
    public void initData() {
        this.orderBean=new OrderBean(myApplication.getPresentUser().getmUserID(),"无",goodsBean.getmGoodsID(),storeBean.getmStoreID(),goodsBean.getmGoodsName(),goodsBean.getmGoodsPrice(),1,0);
        orderConfirmView.initData(orderBean);
    }

    @Override
    public void clickSubButton() {
        if(orderBean.getmGoodsNum()==1) {
            return;
        }
        orderBean.setmGoodsNum(orderBean.getmGoodsNum()-1);
        orderBean.setmTotalCost(orderBean.getmGoodsNum()*goodsBean.getmGoodsPrice());
        orderConfirmView.goodsNumChanged(orderBean);
    }

    @Override
    public void clickAddButton() {
        orderBean.setmGoodsNum(orderBean.getmGoodsNum()+1);
        orderBean.setmTotalCost(orderBean.getmGoodsNum()*goodsBean.getmGoodsPrice());
        orderConfirmView.goodsNumChanged(orderBean);
    }

    @Override
    public void clickSubmitButton() {
        Time t=new Time();
        t.setToNow();
        String time=t.year+"-"+(t.month+1)+"-"+t.monthDay+" "+t.hour+":"+t.minute;
        orderBean.setmOrderTime(time);
        orderBean.setmUseKey("这是一个测试使用码");
        orderModel.writeToDatabase(orderBean);
    }

    @Override
    public void writeToDatabaseOnSuccess() {
        Toast.makeText(context,"提交订单成功！",Toast.LENGTH_SHORT).show();
        orderConfirmView.finishActivity();
    }

    @Override
    public void updataOrderOnSuccess(OrderBean orderBean) {

    }


    @Override
    public void getOrdersOnSuccess(List<OrderBean> orderlist) {

    }

    @Override
    public void NetworkCommunicationsOnError() {
        Toast.makeText(context,"网络错误，请重试！",Toast.LENGTH_SHORT).show();
    }
}
