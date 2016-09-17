package com.example.administrator.xiaozhongdianping.view.searchResultMap;

import com.amap.api.services.core.PoiItem;
import com.example.administrator.xiaozhongdianping.bean.StoreBean;

/**
 * Created by Administrator on 2016/5/2 0002.
 */
public interface SearchResultMapView {
    void showStoreInfo(PoiItem poiItem, StoreBean storeBean);
    void toStoreActivity();
}
