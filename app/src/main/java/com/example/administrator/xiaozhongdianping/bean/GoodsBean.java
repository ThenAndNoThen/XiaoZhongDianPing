package com.example.administrator.xiaozhongdianping.bean;

/**
 * Created by Administrator on 2016/5/5 0005.
 */
public class GoodsBean {
    String mGoodsName,mStoreID,mGoodsID;
    double mGoodsPrice;
    int mGoodsSaledNum;

    public GoodsBean(String mStoreID,String mGoodsID,String mGoodsName,double mGoodsPrice,int mGoodsSaledNum){
        this.mGoodsName=mGoodsName;
        this.mStoreID=mStoreID;
        this.mGoodsID=mGoodsID;
        this.mGoodsPrice=mGoodsPrice;
        this.mGoodsSaledNum=mGoodsSaledNum;
    }

    public String getmGoodsName(){return mGoodsName;}
    public String getmStoreID(){return mStoreID;}
    public String getmGoodsID(){return mGoodsID;}
    public double getmGoodsPrice(){return mGoodsPrice;}
    public int getmGoodsSaledNum(){return mGoodsSaledNum;}
}
