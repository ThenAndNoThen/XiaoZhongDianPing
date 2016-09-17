package com.example.administrator.xiaozhongdianping.ui.store;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.bean.GoodsBean;
import com.example.administrator.xiaozhongdianping.ui.searchResultList.SearchResultListActivity;
import com.example.administrator.xiaozhongdianping.view.store.StoreView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/5 0005.
 */
public class StoreGoodsRecyclerViewAdapter extends  RecyclerView.Adapter<StoreGoodsRecyclerViewAdapter.MyViewHolder>{

    private List<GoodsBean> goodsBeanList;
    private Context context;
    public StoreGoodsRecyclerViewAdapter( Context context){
        this.context=context;
        this.goodsBeanList=new LinkedList<GoodsBean>();
    }

    public void setGoodsBeanList(List<GoodsBean> goodsBeanList){
        this.goodsBeanList.clear();
        this.goodsBeanList.addAll(goodsBeanList);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                (StoreActivity)context).inflate(R.layout.item_store_goods, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mtvGoodsName.setText(goodsBeanList.get(position).getmGoodsName());
        holder.mtvGoodsPrice.setText("￥"+doubleToString(goodsBeanList.get(position).getmGoodsPrice()));
        holder.mtvGoodsSaledNum.setText("已售" + goodsBeanList.get(position).getmGoodsSaledNum());

    }

    public String doubleToString( double num){
        String tmp=String.valueOf(num);
        return tmp.substring(0,tmp.indexOf(".")+2);
    }
    @Override
    public int getItemCount() {
        return goodsBeanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mimgGoodsPic;
        TextView mtvGoodsPrice,mtvGoodsSaledNum,mtvGoodsName;
        public MyViewHolder(View view)
        {
            super(view);
            mimgGoodsPic = (ImageView) view.findViewById(R.id.img_goodspic);
            mtvGoodsPrice = (TextView) view.findViewById(R.id.tv_goodsprice);
            mtvGoodsSaledNum = (TextView) view.findViewById(R.id.tv_goods_salednum);
            mtvGoodsName = (TextView) view.findViewById(R.id.tv_goodsname);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            GoodsBean goodsBean=goodsBeanList.get(getPosition());
            ((StoreView)context).toOrderConfirmActivity(goodsBean);
        }
    }
}
