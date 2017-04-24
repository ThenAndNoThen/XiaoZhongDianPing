package com.example.administrator.xiaozhongdianping.presenter.store;

import android.content.Context;
import android.widget.Toast;

import com.example.administrator.xiaozhongdianping.bean.GoodsBean;
import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.model.GoodsModel;
import com.example.administrator.xiaozhongdianping.model.OrderModel;
import com.example.administrator.xiaozhongdianping.ui.store.StoreActivity;
import com.example.administrator.xiaozhongdianping.util.MyApplication;
import com.example.administrator.xiaozhongdianping.view.store.StoreView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/5 0005.
 */
public class StorePresenterImpl implements StorePresenter, GoodsModel.GoodsGetListener, OrderModel.OrderModelListener {
    private Context context;
    private MyApplication myApplication;
    private StoreView storeView;
    private List<GoodsBean> goodsBeanList;
    private List<OrderBean> orderBeanList;
    private GoodsModel mGoodsModel;
    private OrderModel mOrderModel;
    private int flag;
    public StorePresenterImpl(Context context){
        this.context=context;
        this.myApplication=(MyApplication) ((StoreActivity)context).getApplication();
        this.storeView=((StoreActivity)context);
        this.goodsBeanList=new LinkedList<GoodsBean>();
        this.orderBeanList=new LinkedList<OrderBean>();
        this.mGoodsModel=new GoodsModel(this);
        mOrderModel=new OrderModel(this);
    }

    @Override
    public void getInitData() {
        flag=0;
        storeView.showStoreInfo(myApplication.getShowStorePoi(),myApplication.getShowStoreBean());
    }

    @Override
    public void clickCommentType(int type) {
        List<OrderBean> tmplist=new LinkedList<OrderBean>();
        for(int i=0;i<orderBeanList.size();i++){
            switch (type){
                case 0:
                    tmplist.add(orderBeanList.get(i));
                    break;
                case 3:
                    if(orderBeanList.get(i).getmRating()<=2){
                        tmplist.add(orderBeanList.get(i));
                    }
                    break;
                case 2:
                    if(orderBeanList.get(i).getmRating()<=4 && orderBeanList.get(i).getmRating()>2){
                        tmplist.add(orderBeanList.get(i));
                    }
                    break;
                case 1:
                    if(orderBeanList.get(i).getmRating()>4){
                        tmplist.add(orderBeanList.get(i));
                    }
                    break;
            }
        }
        storeView.updateOrderList(tmplist);

    }

    public void getGoodsList(){
        mGoodsModel.getStoreAllGoods(myApplication.getShowStorePoi().getPoiId());
    }

    public void getOrderList(){
        mOrderModel.getOrdersByStore(myApplication.getShowStorePoi().getPoiId());
    }

    @Override
    public void getGoodsInfoOnSuccess(List<GoodsBean> goodsBeanList) {
        storeView.updateGoodsList(goodsBeanList);
        flag++;
        if(flag==2) {
            storeView.setNestedScrollViewToTop();
        }
    }

    @Override
    public void getGoodsInfoOnFail() {
        Toast.makeText(context,"加载商品出错",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void writeToDatabaseOnSuccess() {

    }

    @Override
    public void updataOrderOnSuccess(OrderBean order) {

    }


    @Override
    public void getOrdersOnSuccess(List<OrderBean> orderlist) {

        orderBeanList.addAll(orderlist);

        int all=orderlist.size(),good=0,nor=0,bad=0;
        for(int i=0;i<orderlist.size();i++)
        {
            if(orderlist.get(i).getmRating()<=2){
                bad++;
            }
            else if(orderlist.get(i).getmRating()<=4){
                nor++;
            }
            else{
                good++;
            }
        }
        //����RecyclerView
        storeView.updateOrderList(orderBeanList);
        //ˢ�±�ʶ
        flag++;
        //��ʾ�����͵Ķ�������
        storeView.setCommentTypeNum(all, good, nor, bad);
        if(flag==2) {
            storeView.setNestedScrollViewToTop();
        }

    }

    @Override
    public void NetworkCommunicationsOnError() {
        Toast.makeText(context,"加载评论出错",Toast.LENGTH_SHORT).show();
    }
}
