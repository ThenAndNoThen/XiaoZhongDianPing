package com.example.administrator.xiaozhongdianping.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.administrator.xiaozhongdianping.bean.StoreBean;
import com.example.administrator.xiaozhongdianping.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class StoreModel implements HttpUtil.HttpCallbackListener {
    StoreGetListener listener;
    List<StoreBean> storelist;
    public StoreModel(StoreGetListener listener){

        this.listener=listener;
    }
    public StoreBean getStoreInfo(String storeid){
        StoreBean store=new StoreBean(storeid,56,3.5);
        return store;
    }

    public void getStoreInfos(String[] storeIDs){
        Gson gson=new Gson();
        HttpUtil.sendHttpRequest(HttpUtil.serverUrl+"GetStoresServlet",gson.toJson(storeIDs),this);
//        List<StoreBean> storelist=new LinkedList<StoreBean>();
//        for(int i=0;i<storeIDs.length;i++){
//            storelist.add(new StoreBean(storeIDs[i],(int)(Math.random()*200),3.5));
//        }

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    listener.getStoreInfoOnSuccess(storelist);
                    break;
                case 2:
                    listener.getStoreInfoOnError();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onFinish(String response) {
        Gson gson=new Gson();
        storelist=new LinkedList<StoreBean>();
        storelist.addAll((List)gson.fromJson(response, new TypeToken<List<StoreBean>>(){}.getType())) ;
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

    public interface StoreGetListener{
        void getStoreInfoOnSuccess( List<StoreBean> storelist);
        void getStoreInfoOnError();
    }
}
