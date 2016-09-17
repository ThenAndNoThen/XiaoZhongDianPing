package com.example.administrator.xiaozhongdianping.ui.searchResultList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.bean.StoreBean;
import com.example.administrator.xiaozhongdianping.util.MyApplication;

import java.util.List;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class SearchResultRecyclerViewAdapter extends  RecyclerView.Adapter<SearchResultRecyclerViewAdapter.MyViewHolder>{
    private MyApplication myApplication;
    private List<PoiItem> poiItemList;
    private List<StoreBean> storeBeanList;
    private Context context;

    public SearchResultRecyclerViewAdapter(Context context){
        this.myApplication= (MyApplication) ((SearchResultListActivity)context).getApplication();
        this.context=context;
        poiItemList=myApplication.getPoiItemList();
        storeBeanList=myApplication.getStoreBeanList();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                (SearchResultListActivity)context).inflate(R.layout.item_search_result_listview, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PoiItem poiItem=poiItemList.get(position);
        StoreBean storeBean=storeBeanList.get(position);
        holder.mtvStoreName.setText(poiItem.getTitle());
        if(storeBean.getmStoreAvgCost()==0){
            holder.mtvRating.setText("暂未入住");
            holder.mtvAveCost.setText("");
        }
        else{
            holder.mtvAveCost.setText("人均"+String.valueOf(storeBean.getmStoreAvgCost())+"元");
            holder.mtvRating.setText(doubleToString(storeBean.getmStoreRating())+"分");
        }
        holder.mtvStoreType.setText(poiItem.getTypeDes().split(";")[2]);
        holder.mtvDistance.setText(doubleToString(storeBean.getmDistance())+" km");
        holder.mrbStoreRating.setRating((float)storeBean.getmStoreRating());

    }

    public String doubleToString( double num){
        String tmp=String.valueOf(num);
        return tmp.substring(0,tmp.indexOf(".")+2);
    }

    @Override
    public int getItemCount() {
        poiItemList=myApplication.getPoiItemList();
        storeBeanList=myApplication.getStoreBeanList();
        return poiItemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mimgStorePic;
        TextView mtvStoreName,mtvRating,mtvAveCost,mtvStoreType,mtvDistance;
        RatingBar mrbStoreRating;
        LinearLayout mlayoutItem;
        public MyViewHolder(View view)
        {
            super(view);
            mimgStorePic = (ImageView) view.findViewById(R.id.storepicture_resultlist_imageview);
            mtvStoreName = (TextView) view.findViewById(R.id.storename_resultlist__textview);
            mtvRating = (TextView) view.findViewById(R.id.storerating_resultlist_textview);
            mtvAveCost = (TextView) view.findViewById(R.id.avecost_resultlist_textview);
            mtvStoreType = (TextView) view.findViewById(R.id.storetype_resultlist_textview);
            mtvDistance = (TextView) view.findViewById(R.id.distance_resultlist_textview);
            mrbStoreRating = (RatingBar) view.findViewById(R.id.storerating_resultlist_ratingbar);
            mlayoutItem = (LinearLayout) view.findViewById(R.id.item_layout);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            ((SearchResultListActivity)context).gotoStoreDeatilActivity(getPosition());

        }
    }
}
