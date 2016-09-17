package com.example.administrator.xiaozhongdianping.ui.searchResultMap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.services.core.PoiItem;
import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.bean.StoreBean;
import com.example.administrator.xiaozhongdianping.presenter.searchResultMap.SearchResultMapPresenterImpl;
import com.example.administrator.xiaozhongdianping.ui.store.StoreActivity;
import com.example.administrator.xiaozhongdianping.util.MyApplication;
import com.example.administrator.xiaozhongdianping.view.searchResultMap.SearchResultMapView;

import static com.example.administrator.xiaozhongdianping.R.id.imagebutton_back;

public class SearchResultMapActivity extends AppCompatActivity implements AMap.OnMapLoadedListener, AMap.OnMarkerClickListener,SearchResultMapView, View.OnClickListener {

    private MapView mapView;//地图控件
    private AMap aMap;
    private PoiOverlay poiOverlay;//poi展示类
    private LinearLayout bottomlayout;//底部布局
    private TextView mtvStoreName,mtvStoreAvgCost,mtvStoreRating,mtvStoreAddress;//底部商家信息控件
    private RatingBar mRbStoreRating;//商家评级
    private ImageButton imageButtonBack;
    private SearchResultMapPresenterImpl mSearchResultMapPresenter;//本页面的presenter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_map);

        //初始化控件
        mapView = (MapView) findViewById(R.id.map);
        bottomlayout = (LinearLayout) findViewById(R.id.layout_bottom);
        mtvStoreName = (TextView) findViewById(R.id.tv_storename);
        mtvStoreAddress = (TextView) findViewById(R.id.tv_storeaddress);
        mtvStoreAvgCost = (TextView) findViewById(R.id.tv_avecost);
        mtvStoreRating = (TextView) findViewById(R.id.tv_rating);
        mRbStoreRating = (RatingBar) findViewById(R.id.ratingbar);
        imageButtonBack = (ImageButton) findViewById(imagebutton_back);

        //设置数据
        mSearchResultMapPresenter=new SearchResultMapPresenterImpl(this);
        mapView.onCreate(savedInstanceState);// 必须要写
        aMap = mapView.getMap();

        //设置监听
        aMap.setOnMarkerClickListener(this);//设置地图标记事件监听
        aMap.setOnMapLoadedListener(this);
        bottomlayout.setOnClickListener(this);
        imageButtonBack.setOnClickListener(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMapLoaded() {
        poiOverlay = new PoiOverlay(aMap, ((MyApplication)this.getApplication()).getPoiItemList());//设置poi点在地图的显示
        poiOverlay.removeFromMap();
        poiOverlay.addToMap();//添加到地图中
        poiOverlay.zoomToSpan();//设置地图视图范围位置到合适值
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mSearchResultMapPresenter.clickMarker(marker);
        return true;
    }

    @Override
    public void showStoreInfo(PoiItem poiItem, StoreBean storeBean) {
        mtvStoreName.setText(poiItem.getTitle());
        mtvStoreAddress.setText(poiItem.getSnippet());
        if(storeBean.getmStoreAvgCost()==0){
            mtvStoreAvgCost.setText("");
            mtvStoreRating.setText("暂未入住");
        }
        else {
            mtvStoreAvgCost.setText("人均￥" + storeBean.getmStoreAvgCost());
            mtvStoreRating.setText(doubleToString(storeBean.getmStoreRating()) + "分");
        }
        mRbStoreRating.setRating((float)storeBean.getmStoreRating());
        bottomlayout.setVisibility(View.VISIBLE);//设置底部布局可见（默认不可见）
    }

    @Override
    public void toStoreActivity() {
        Intent intent=new Intent(this, StoreActivity.class);
        startActivity(intent);
    }

    public String doubleToString( double num){
        String tmp=String.valueOf(num);
        return tmp.substring(0,tmp.indexOf(".")+2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layout_bottom:
                mSearchResultMapPresenter.clickBottomLayout();
                break;
            case R.id.imagebutton_back:
                finish();
                break;
        }
    }
}
