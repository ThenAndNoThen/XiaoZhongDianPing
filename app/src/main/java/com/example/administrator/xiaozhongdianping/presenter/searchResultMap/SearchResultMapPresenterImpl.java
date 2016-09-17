package com.example.administrator.xiaozhongdianping.presenter.searchResultMap;

import android.content.Context;

import com.amap.api.maps.model.Marker;
import com.amap.api.services.core.PoiItem;
import com.example.administrator.xiaozhongdianping.bean.StoreBean;
import com.example.administrator.xiaozhongdianping.ui.searchResultMap.SearchResultMapActivity;
import com.example.administrator.xiaozhongdianping.util.MyApplication;
import com.example.administrator.xiaozhongdianping.view.searchResultMap.SearchResultMapView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/2 0002.
 */
public class SearchResultMapPresenterImpl implements SearchResultMapPresenter{
    private SearchResultMapView mSearchResultMapView;
    Context context;
    MyApplication myApplication;
    int selectedPoi;
    public SearchResultMapPresenterImpl(Context context){
        this.context=context;
        this.myApplication= (MyApplication) ((SearchResultMapActivity)context).getApplication();
        this.mSearchResultMapView=(SearchResultMapActivity)context;
        this.selectedPoi=-1;
    }
    @Override
    public void clickMarker(Marker marker) {
        List<PoiItem> poiItemlist=myApplication.getPoiItemList();
        List<StoreBean> storeBeenList=myApplication.getStoreBeanList();
        for(int i=0;i<poiItemlist.size();i++){
            if(marker.getPosition().latitude==poiItemlist.get(i).getLatLonPoint().getLatitude()
                    && marker.getPosition().longitude==poiItemlist.get(i).getLatLonPoint().getLongitude()){
                mSearchResultMapView.showStoreInfo(poiItemlist.get(i),storeBeenList.get(i));
                selectedPoi=i;
                return;
            }
        }
    }

    @Override
    public void clickBottomLayout() {
        myApplication.setShowStorePoi(myApplication.getPoiItemList().get(selectedPoi));
        myApplication.setShowStoreBean(myApplication.getStoreBeanList().get(selectedPoi));
        mSearchResultMapView.toStoreActivity();
    }


}
