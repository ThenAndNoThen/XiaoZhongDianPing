package com.example.administrator.xiaozhongdianping.presenter.Main;

import android.content.Context;
import android.text.format.Time;
import android.widget.Toast;

import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.model.OrderModel;
import com.example.administrator.xiaozhongdianping.ui.main.OrderCommentActivity;
import com.example.administrator.xiaozhongdianping.util.MyApplication;
import com.example.administrator.xiaozhongdianping.view.main.OrderCommentView;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class OrderCommentPresenterImpl implements OrderCommentPresenter, OrderModel.OrderModelListener {
    Context context;
    MyApplication myApplication;
    OrderCommentView orderCommentView;
    OrderModel orderModel;
    public OrderCommentPresenterImpl(Context context){
        this.context=context;
        myApplication=(MyApplication) ((OrderCommentActivity)context).getApplication();
        orderCommentView=(OrderCommentView)context;
        orderModel=new OrderModel(this);
    }
    @Override
    public void clickCommentButton(String comment, double rating) {
        if(rating==0 || comment.equals("")){
            Toast.makeText(context,"请打分，并且评论不能为空！",Toast.LENGTH_SHORT).show();
        }
        else{
            OrderBean tmporder=myApplication.getShowOrderBean();
            OrderBean order=new OrderBean(tmporder.getmUserID(),tmporder.getmOrderID(),tmporder.getmGoodsID(),tmporder.getmStoreID(),tmporder.getmGoodsName(),tmporder.getmTotalCost(),tmporder.getmGoodsNum(),tmporder.getmOrderState());
            order.setmOrderTime(tmporder.getmOrderTime());
            order.setmUseKey(tmporder.getmUseKey());
            order.setmRating(rating);
            order.setmCommentText(comment);
            Time t=new Time();
            t.setToNow();
            String time=t.year+"-"+(t.month+1)+"-"+t.monthDay+" "+t.hour+":"+t.minute;
            order.setmCommentTime(time);
            order.setmOrderState(2);
            orderModel.updataOrderInDatabase(order);
        }
    }

    @Override
    public void writeToDatabaseOnSuccess() {

    }

    @Override
    public void updataOrderOnSuccess(OrderBean order) {

        myApplication.setShowOrderBean(order);
        orderCommentView.showCommentSuccess();
        orderCommentView.finishActivity();
    }


    @Override
    public void getOrdersOnSuccess(List<OrderBean> orderlist) {

    }

    @Override
    public void NetworkCommunicationsOnError() {
        Toast.makeText(context,"网络错误，请重试！",Toast.LENGTH_SHORT).show();
    }
}
