package com.example.administrator.xiaozhongdianping.model;

import android.content.Context;

import com.amap.api.services.poisearch.PoiSearch;
import com.example.administrator.xiaozhongdianping.bean.StoreBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class PoiSearchModel {
    PoiSearch.Query query;
    Context context;
    PoiSearch.OnPoiSearchListener onPoiSearchListener;
    public void startPoiSearch(String searchKey, String targetCity, Context context, PoiSearch.OnPoiSearchListener onPoiSearchListener){
        this.context=context;
        this.onPoiSearchListener=onPoiSearchListener;
        query = new PoiSearch.Query(searchKey, "餐饮服务", targetCity); //设置搜索条件
        query.setPageSize(30);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);//设置查询页码
        PoiSearch poiSearch = new PoiSearch(context, query);//初始化poiSearch对象
        poiSearch.setOnPoiSearchListener(onPoiSearchListener);//设置回调数据的监听器
        poiSearch.searchPOIAsyn();//开始搜索
    }

    public void goToNextPoiResultPage(int pageNum){
        query.setPageNum(pageNum+1);//设置查询页码
        PoiSearch poiSearch = new PoiSearch(context, query);//初始化poiSearch对象
        poiSearch.setOnPoiSearchListener(onPoiSearchListener);//设置回调数据的监听器
        poiSearch.searchPOIAsyn();//开始搜索
    }

}
