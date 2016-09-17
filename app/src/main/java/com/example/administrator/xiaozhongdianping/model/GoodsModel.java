package com.example.administrator.xiaozhongdianping.model;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.administrator.xiaozhongdianping.bean.GoodsBean;
import com.example.administrator.xiaozhongdianping.bean.StoreBean;
import com.example.administrator.xiaozhongdianping.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/5 0005.
 */
public class GoodsModel implements HttpUtil.HttpCallbackListener {
    private GoodsGetListener listener;
    private List<GoodsBean> goodsBeanList;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    listener.getGoodsInfoOnSuccess(goodsBeanList);
                    break;
                case 2:
                    listener.getGoodsInfoOnFail();
                    break;
                default:
                    break;
            }
        }
    };
    public GoodsModel(GoodsGetListener listener){this.listener=listener;}

    public void getStoreAllGoods(String storeid){
        Gson gson=new Gson();
        HttpUtil.sendHttpRequest(HttpUtil.serverUrl+"GetGoodsListServlet",gson.toJson(storeid),this);
    }

    @Override
    public void onFinish(String response) {
        Gson gson=new Gson();
        goodsBeanList=new LinkedList<GoodsBean>();
        goodsBeanList.addAll((List)gson.fromJson(response, new TypeToken<List<GoodsBean>>(){}.getType())) ;
        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
        Message message = new Message();
        message.what = 2;
        handler.sendMessage(message);
    }

    public interface GoodsGetListener{
        void getGoodsInfoOnSuccess(List<GoodsBean> goodsBeanList);
        void getGoodsInfoOnFail();
    }
}
