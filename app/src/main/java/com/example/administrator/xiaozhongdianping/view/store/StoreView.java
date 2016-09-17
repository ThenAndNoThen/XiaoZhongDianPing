package com.example.administrator.xiaozhongdianping.view.store;

import com.amap.api.services.core.PoiItem;
import com.example.administrator.xiaozhongdianping.bean.GoodsBean;
import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.bean.StoreBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/5 0005.
 */
public interface StoreView {
    void showStoreInfo(PoiItem poiItem, StoreBean storeBean);
    void updateGoodsList(List<GoodsBean> goodsBeanList);
    void updateOrderList(List<OrderBean> orderBeanList);
    void setNestedScrollViewToTop();
    void toOrderConfirmActivity(GoodsBean goodsBean);
    void setCommentTypeNum(int all,int good,int nor,int bad);
}
