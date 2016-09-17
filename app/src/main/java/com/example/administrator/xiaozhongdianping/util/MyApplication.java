package com.example.administrator.xiaozhongdianping.util;

import android.app.Application;

import com.amap.api.services.core.PoiItem;
import com.example.administrator.xiaozhongdianping.bean.GoodsBean;
import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.bean.StoreBean;
import com.example.administrator.xiaozhongdianping.bean.UserBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class MyApplication extends Application{
    String appstrTargetCity;
    List<PoiItem> poiItemList;
    List<StoreBean> storeBeanList;
    PoiItem showStorePoi;
    StoreBean showStoreBean;
    GoodsBean goodsInOrder;
    UserBean presentUser;
    OrderBean showOrderBean;



    @Override
    public void onCreate()
    {
        super.onCreate();
        appstrTargetCity="北京";
        poiItemList=new LinkedList<PoiItem>();
        storeBeanList=new LinkedList<StoreBean>();
        presentUser=new UserBean("wxj");
    }

    public void setPresentUser(UserBean presentUser) {this.presentUser = presentUser;}
    public void setAppstrTargetCity(String city){appstrTargetCity=city;}
    public void setPoiItemList(List poiItemList){
        this.poiItemList=poiItemList;
    }
    public void setStoreBeanList(List storeBeanList) { this.storeBeanList=storeBeanList;}
    public void setShowStorePoi(PoiItem poi){this.showStorePoi=poi;}
    public void setShowStoreBean(StoreBean storeBean){this.showStoreBean=storeBean;}
    public void setGoodsInOrder(GoodsBean goodsBean){this.goodsInOrder=goodsBean;}
    public void setShowOrderBean(OrderBean showOrderBean) {this.showOrderBean = showOrderBean;}

    public String getAppTargetCity(){
        return appstrTargetCity;
    }
    public List<PoiItem> getPoiItemList(){
        return this.poiItemList;
    }
    public List<StoreBean> getStoreBeanList(){ return this.storeBeanList;}
    public PoiItem getShowStorePoi(){return showStorePoi;}
    public StoreBean getShowStoreBean(){return showStoreBean;}
    public GoodsBean getGoodsInOrder(){return goodsInOrder;}
    public UserBean getPresentUser() {return presentUser;}
    public OrderBean getShowOrderBean() {return showOrderBean;}
}
