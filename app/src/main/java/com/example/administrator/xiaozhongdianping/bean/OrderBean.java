package com.example.administrator.xiaozhongdianping.bean;

/**
 * Created by Administrator on 2016/5/8 0008.
 */
public class OrderBean {
    String mUserID,mOrderID,mGoodsID,mStoreID,mGoodsName,mOrderTime="无",mCommentTime="无",mCommentText="无",mUseKey="无";
    double mTotalCost,mRating=0;
    int mGoodsNum,mOrderState;//mOrderState：0  待付款，1  待评价，2  已评价，-1  已取消

    public OrderBean (String mUserID,String mOrderID,String mGoodsID,String mStoreID ,String mGoodsName,double mTotalCost ,int mGoodsNum,int mOrderState){
        this.mUserID=mUserID;
        this.mOrderID=mOrderID;
        this.mGoodsID=mGoodsID;
        this.mGoodsName=mGoodsName;
        this.mTotalCost=mTotalCost;
        this.mGoodsNum=mGoodsNum;
        this.mOrderState=mOrderState;
        this.mStoreID=mStoreID;
    }
    public void setmOrderTime(String time){this.mOrderTime=time;}
    public void setmCommentTime(String time){this.mCommentTime=time;}
    public void setmCommentText(String text){this.mCommentText=text;}
    public void setmRating(double rating){this.mRating=rating;}
    public void setmTotalCost(double cost){this.mTotalCost=cost;}
    public void setmGoodsNum(int num){this.mGoodsNum=num;}
    public void setmOrderState(int state){this.mOrderState=state;}
    public void setmUseKey(String mUseKey) {this.mUseKey = mUseKey;}
    public void setmUserID(String mUserID) {this.mUserID = mUserID;}
    public void setmGoodsID(String mGoodsID) {this.mGoodsID = mGoodsID;}
    public void setmGoodsName(String mGoodsName) {this.mGoodsName = mGoodsName;}
    public void setmOrderID(String mOrderID) {this.mOrderID = mOrderID;}
    public void setmStoreID(String mStoreID) {this.mStoreID = mStoreID;}

    public String getmGoodsID() {
        return mGoodsID;
    }
    public String getmOrderID() {
        return mOrderID;
    }
    public String getmGoodsName() {
        return mGoodsName;
    }
    public String getmCommentTime() {
        return mCommentTime;
    }
    public String getmOrderTime() {
        return mOrderTime;
    }
    public String getmCommentText() {
        return mCommentText;
    }
    public double getmTotalCost() {
        return mTotalCost;
    }
    public double getmRating() {
        return mRating;
    }
    public int getmGoodsNum() {
        return mGoodsNum;
    }
    public int getmOrderState() {
        return mOrderState;
    }
    public String getmUseKey() {
        return mUseKey;
    }
    public String getmUserID() {return mUserID;}
    public String getmStoreID() {return mStoreID;}
}
