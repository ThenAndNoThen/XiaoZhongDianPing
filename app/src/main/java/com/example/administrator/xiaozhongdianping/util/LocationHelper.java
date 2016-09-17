package com.example.administrator.xiaozhongdianping.util;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class LocationHelper {
    private AMapLocationClient mAMapLocationClient;
    AMapLocationClientOption mLocationOption;


    public LocationHelper(Context context){
        mAMapLocationClient=new AMapLocationClient(context);
        mLocationOption = new AMapLocationClientOption();
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        ////设置是否返回地址信息（默认返回地址信息）
        //        mLocationOption.setNeedAddress(true);
        ////设置是否强制刷新WIFI，默认为强制刷新
        //        mLocationOption.setWifiActiveScan(true);
        ////设置是否允许模拟位置,默认为false，不允许模拟位置
        //        mLocationOption.setMockEnable(false);
        ////设置定位间隔,单位毫秒,默认为2000ms
        //        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mAMapLocationClient.setLocationOption(mLocationOption);


    }
    public void startLocation(){
        //启动定位
        mAMapLocationClient.startLocation();
    }

    public void setLocationListener(AMapLocationListener listener){
        mAMapLocationClient.setLocationListener(listener);
    }

    public float calDiatanceKM(LatLng l1,LatLng l2){
       return  AMapUtils.calculateLineDistance(l1, l2) / 1000;
    }

}
