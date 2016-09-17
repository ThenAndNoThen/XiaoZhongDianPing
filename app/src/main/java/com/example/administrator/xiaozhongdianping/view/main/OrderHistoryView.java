package com.example.administrator.xiaozhongdianping.view.main;

import com.example.administrator.xiaozhongdianping.bean.OrderBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/8 0008.
 */
public interface OrderHistoryView {
    void showData(List<OrderBean> orderBeanList);
    void toOrderDetailActivity();
}
