package com.example.administrator.xiaozhongdianping.bean;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class StoreBean {
    String mStoreID;
    int mStoreAvgCost;
    double mStoreRating;
    double mDistance;

    public StoreBean(String mStoreID,int mStoreAvgCost,double mStoreRating){
        this.mStoreID=mStoreID;
        this.mStoreAvgCost=mStoreAvgCost;
        this.mStoreRating=mStoreRating;
        this.mDistance=-1;
    }

    public void setmStoreID(String id){
        mStoreID=id;
    }

    public void setmStoreAvgCost(int cost){
        mStoreAvgCost=cost;
    }

    public void setmStoreRating(double rating){
        mStoreRating=rating;
    }

    public void setmDistance(double dis){
        mDistance=dis;
    }

    public String getmStoreID(){
        return mStoreID;
    }

    public double getmStoreRating(){
        return mStoreRating;
    }

    public double getmDistance(){
        return  mDistance;
    }

    public int getmStoreAvgCost() {
        return mStoreAvgCost;
    }
}
