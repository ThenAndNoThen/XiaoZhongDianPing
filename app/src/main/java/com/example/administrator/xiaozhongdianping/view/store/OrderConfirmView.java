package com.example.administrator.xiaozhongdianping.view.store;

import com.example.administrator.xiaozhongdianping.bean.OrderBean;

/**
 * Created by Administrator on 2016/5/8 0008.
 */
public interface OrderConfirmView {
    void initData(OrderBean orderBean);
    void goodsNumChanged(OrderBean orderBean);
    void finishActivity();
}
