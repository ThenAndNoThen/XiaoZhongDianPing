package com.example.administrator.xiaozhongdianping.model;

import android.os.Handler;
import android.os.Message;

import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.bean.StoreBean;
import com.example.administrator.xiaozhongdianping.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/8 0008.
 */
public class OrderModel implements HttpUtil.HttpCallbackListener {
    OrderModelListener listener;
    List<OrderBean> orderBeanList=new LinkedList<OrderBean>();
    OrderBean order;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    listener.writeToDatabaseOnSuccess();
                    break;
                case 2:
                    listener.NetworkCommunicationsOnError();
                    break;
                case 3:
                    listener.getOrdersOnSuccess(orderBeanList);
                    break;
                case 4:
                    listener.updataOrderOnSuccess(order);
                    break;
                default:

                    break;
            }
        }
    };
    public OrderModel(OrderModelListener listener){
        this.listener=listener;
    }

    public void writeToDatabase(OrderBean orderBean){
        Gson gson=new Gson();
        HttpUtil.sendHttpRequest(HttpUtil.serverUrl+"AddOrderServlet",gson.toJson(orderBean),this);
    }

    public void getOrdersByUser(String userid){
        Gson gson=new Gson();
        HttpUtil.sendHttpRequest(HttpUtil.serverUrl+"GetOrderListServlet",gson.toJson("USER "+userid),this);
    }

    public void getOrdersByStore(String storeid){
        Gson gson=new Gson();
        HttpUtil.sendHttpRequest(HttpUtil.serverUrl+"GetOrderListServlet",gson.toJson("STORE_COMMENTED "+storeid),this);
    }

    public void updataOrderInDatabase(OrderBean orderBean){
        Gson gson=new Gson();
        HttpUtil.sendHttpRequest(HttpUtil.serverUrl+"UpdataOrderServlet",gson.toJson(orderBean),this);
    }

    @Override
    public void onFinish(String response) {
        if(response.startsWith("提交订单成功"))
        {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
        else if(response.startsWith("评价订单成功")){
            Gson gson=new Gson();
            order=gson.fromJson(response.substring(response.indexOf(" ")),new TypeToken<OrderBean>(){}.getType());
            Message message = new Message();
            message.what = 4;
            handler.sendMessage(message);
        }
        else{
            Gson gson=new Gson();
            orderBeanList=gson.fromJson(response,new TypeToken<List<OrderBean>>(){}.getType());
            Message message = new Message();
            message.what = 3;
            handler.sendMessage(message);
        }
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
        Message message = new Message();
        message.what = 2;
        handler.sendMessage(message);
    }

    public interface OrderModelListener{
        void writeToDatabaseOnSuccess();
        void updataOrderOnSuccess(OrderBean order);
        void getOrdersOnSuccess(List<OrderBean> orderlist);
        void NetworkCommunicationsOnError();
    }
}
