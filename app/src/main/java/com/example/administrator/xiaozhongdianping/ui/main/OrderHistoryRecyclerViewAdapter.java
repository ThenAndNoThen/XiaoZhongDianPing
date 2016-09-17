package com.example.administrator.xiaozhongdianping.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.bean.GoodsBean;
import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.ui.store.StoreActivity;
import com.example.administrator.xiaozhongdianping.view.store.StoreView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/8 0008.
 */
public class OrderHistoryRecyclerViewAdapter extends RecyclerView.Adapter<OrderHistoryRecyclerViewAdapter.MyViewHolder>{
    List<OrderBean> orderBeanList;
    Context context;
    RecyclerViewItemClickListener itemClickListener;
    public OrderHistoryRecyclerViewAdapter(Context context){
        this.context=context;
        orderBeanList=new LinkedList<OrderBean>();
    }
    public void setOrderBeanList(List<OrderBean> orderBeanList){
        this.orderBeanList.clear();
        for(int i=orderBeanList.size()-1;i>=0;i--) {
            this.orderBeanList.add(orderBeanList.get(i));
        }
    }
    public void setItemClickListener(RecyclerViewItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    public OrderBean getOrderAt(int i) {
        return orderBeanList.get(i);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_recyclerview_historyorder, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mtvGoodsName.setText(orderBeanList.get(position).getmGoodsName());
        switch(orderBeanList.get(position).getmOrderState()) {
            case 0:
                holder.mtvOrderState.setText("待付款");
                holder.mtvOrderState.setTextColor(context.getResources().getColor(R.color.colorAccent));
                break;
            case 1:
                holder.mtvOrderState.setText("待评价");
                holder.mtvOrderState.setTextColor(context.getResources().getColor(R.color.coloryOrange));
                break;
            case 2:
                holder.mtvOrderState.setText("已完成");
                holder.mtvOrderState.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                break;
            case -1:
                holder.mtvOrderState.setText("已取消");
                holder.mtvOrderState.setTextColor(context.getResources().getColor(R.color.coloryDarkGray));
                break;
        }
        holder.mtvOrderTime.setText(orderBeanList.get(position).getmOrderTime());
        holder.mtvOrderTotalCost.setText("￥"+doubleToString(orderBeanList.get(position).getmTotalCost()));
    }
    public String doubleToString( double num){
        String tmp=String.valueOf(num);
        return tmp.substring(0,tmp.indexOf(".")+2);
    }

    @Override
    public int getItemCount() {
        return orderBeanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView mimgGoodsPic;
    TextView mtvGoodsName,mtvOrderState,mtvOrderTime,mtvOrderTotalCost;
    public MyViewHolder(View view)
    {
        super(view);
        mimgGoodsPic = (ImageView) view.findViewById(R.id.imgOrderIcon);
        mtvGoodsName = (TextView) view.findViewById(R.id.tvOrderTitle);
        mtvOrderState = (TextView) view.findViewById(R.id.tvOrderStatus);
        mtvOrderTime = (TextView) view.findViewById(R.id.tvCreateOrderTime);
        mtvOrderTotalCost = (TextView) view.findViewById(R.id.tvOrderPrice);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.itemOnclick(getPosition());
    }


}
    public interface RecyclerViewItemClickListener{
        void itemOnclick(int position);
    }
}
