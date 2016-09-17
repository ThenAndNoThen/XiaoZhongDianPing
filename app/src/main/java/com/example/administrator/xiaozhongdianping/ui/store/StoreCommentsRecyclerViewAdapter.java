package com.example.administrator.xiaozhongdianping.ui.store;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.bean.GoodsBean;
import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.view.store.StoreView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/11 0011.
 */
public class StoreCommentsRecyclerViewAdapter extends  RecyclerView.Adapter<StoreCommentsRecyclerViewAdapter.MyViewHolder>{
    private List<OrderBean> orderBeanList;
    private Context context;
    public StoreCommentsRecyclerViewAdapter( Context context){
        this.context=context;
        this.orderBeanList=new LinkedList<OrderBean>();

    }

    void setOrderBeanList(List<OrderBean> list){
        orderBeanList.clear();
        for(int i=list.size()-1;i>=0;i--){
            orderBeanList.add(list.get(i));
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                (StoreActivity)context).inflate(R.layout.item_store_comments, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OrderBean order=orderBeanList.get(position);
        holder.mtvUsername.setText(order.getmUserID());
        holder.mrbRating.setRating((float)order.getmRating());
        holder.mtvCommentTime.setText(order.getmCommentTime().split(":")[0]);
        holder.mtvCommentText.setText(order.getmCommentText());
        holder.mimgUserPic.setColorFilter(Color.BLUE);
    }

    @Override
    public int getItemCount() {
        return orderBeanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder  {
        ImageView mimgUserPic;
        TextView mtvUsername,mtvCommentTime,mtvCommentText;
        RatingBar mrbRating;
        public MyViewHolder(View view)
        {
            super(view);
            mimgUserPic = (ImageView) view.findViewById(R.id.img_usericon);
            mtvUsername = (TextView) view.findViewById(R.id.tv_username);
            mtvCommentTime = (TextView) view.findViewById(R.id.tv_comment_time);
            mtvCommentText = (TextView) view.findViewById(R.id.tv_comment_text);
            mrbRating = (RatingBar) view.findViewById(R.id.rb_rating_order);
        }


    }
}
