package com.example.administrator.xiaozhongdianping.presenter.searchResultList;

import android.content.Context;
import android.content.res.AssetManager;
import android.location.LocationListener;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.administrator.xiaozhongdianping.bean.City;
import com.example.administrator.xiaozhongdianping.bean.District;
import com.example.administrator.xiaozhongdianping.bean.Province;
import com.example.administrator.xiaozhongdianping.bean.StoreBean;
import com.example.administrator.xiaozhongdianping.model.PoiSearchModel;
import com.example.administrator.xiaozhongdianping.model.StoreModel;
import com.example.administrator.xiaozhongdianping.ui.searchResultList.SearchResultListActivity;
import com.example.administrator.xiaozhongdianping.util.LocationHelper;
import com.example.administrator.xiaozhongdianping.util.MyApplication;
import com.example.administrator.xiaozhongdianping.util.MyXmlParserHandler;
import com.example.administrator.xiaozhongdianping.view.searchResultList.SearchResultListView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class SearchResultListPresenterImpl implements SearchResultListPresenter,PoiSearch.OnPoiSearchListener,AMapLocationListener, StoreModel.StoreGetListener {
    private SearchResultListView mSearchResultListView;//结果列表view
    private PoiSearchModel mPoiSearchModel;//高德地图服务器交互model
    private StoreModel mStoreModel;//我的商店信息服务器交互model
    private LocationHelper mLocationHelper;
    private LatLng loactionLatLng = null;
    private String locationCityName = null;
    private String mSearchKey;
    private Context context;
    private List<PoiItem> allPoiItem;
    private List<StoreBean> allStoreBean;
    private MyApplication myApplication;
    private List<Province> mListData;//总数据
    private Map<String, List<City>> mMapProvinceCity;//省，市Map
    private Map<String, List<District>> mMapCityDistrict;//市，区Map
    private List<String> mListProvinceName, mListCityName, mListDistrictName;//省，市，区字符串数据

    public SearchResultListPresenterImpl(SearchResultListView searchResultListView,Context context, String searchKey){

        this.mSearchResultListView=searchResultListView;
        this.allPoiItem=new LinkedList<PoiItem>();
        this.allStoreBean=new LinkedList<StoreBean>();
        this.mPoiSearchModel=new PoiSearchModel();
        this.mStoreModel=new StoreModel(this);
        this.myApplication=(MyApplication) ((SearchResultListActivity)context).getApplication();
        this.mLocationHelper=new LocationHelper(context);
        this.mSearchKey=searchKey;
        this.context=context;
        mMapProvinceCity = new HashMap<>();
        mMapCityDistrict = new HashMap<>();
        mListProvinceName = new ArrayList<String>();
        mListCityName = new ArrayList<String>();
        mListDistrictName = new ArrayList<String>();

        myApplication.getPoiItemList().clear();
        myApplication.getStoreBeanList().clear();
        mLocationHelper.setLocationListener(this);
        mLocationHelper.startLocation();

    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int rCode) {
        //可以在回调中解析result，获取POI信息
        //result.getPois()可以获取到PoiItem列表，Poi详细信息可参考PoiItem类
        //若当前城市查询不到所需Poi信息，可以通过result.getSearchSuggestionCitys()获取当前Poi搜索的建议城市
        //如果搜索关键字明显为误输入，则可通过result.getSearchSuggestionKeywords()方法得到搜索关键词建议
        //返回结果成功或者失败的响应码。0为成功，其他为失败（详细信息参见网站开发指南-错误码对照表）

        //请求查找poi失败
        if (rCode != 1000){
            mSearchResultListView.showPoiSearchFailed();
            return;
        }
        List<PoiItem> poiItemList = poiResult.getPois();
        allPoiItem.addAll(poiItemList);
        for(int i=0;i<poiItemList.size();i++){
            PoiItem poiItem=poiItemList.get(i);
            Log.i(i+"----"+poiResult.getQuery().getPageNum(),poiItem.getTitle());
        }


        //如果返回的结果页不是最后一页
        if (poiResult.getPageCount() != poiResult.getQuery().getPageNum()){
            mPoiSearchModel.goToNextPoiResultPage(poiResult.getQuery().getPageNum());
        }
        //如果是最后一页
        else{
            if(allPoiItem.size()==0){
                mSearchResultListView.showNomatchResult();
            }
            else{
                getPoiInfoFromMySer();
            }


        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public String[] getAdNames() {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("province_data.xml");
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();//创建一个解析xml的工厂对象
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyXmlParserHandler xmlParserHandler = new MyXmlParserHandler();
            saxParser.parse(inputStream, xmlParserHandler);
            inputStream.close();
            // 获取解析出来的数据
            mListData = xmlParserHandler.getmProvinceList();

            /**
             * 将解析出来的地址数据从放在Map中
             */
            for (int i = 0; i < mListData.size(); i++) {
                String provinceName = mListData.get(i).getProvinceName();//获得省名
                List<City> cityList = mListData.get(i).getCityList();//获得城市List
                mMapProvinceCity.put(provinceName, cityList);//对应关系放入map中
                mListProvinceName.add(provinceName);//获得省字符串数据
                for (int j = 0; j < cityList.size(); j++) {
                    String cityName = cityList.get(j).getCityName();//获得市名
                    List<District> districtList = cityList.get(j).getDistrictList();//获得地区List
                    mMapCityDistrict.put(cityName, districtList);//对应关系放入map中
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        List<District> districtList = mMapCityDistrict.get(myApplication.getAppTargetCity()+"市");
        String []str=new String[districtList.size()];
        str[0]="全城";
        for (int i = 0; i < districtList.size()-1; i++) {
            str[i+1] = districtList.get(i).getDistrictName();
        }
        return str;
    }

    @Override
    public void resortPoi(String resorttype) {
        switch (resorttype){
            case "离我最近":
                for(int i=0;i<allStoreBean.size()-1;i++){
                    for(int j=i+1;j<allStoreBean.size();j++){
                        if(allStoreBean.get(i).getmDistance()>allStoreBean.get(j).getmDistance()){
                            StoreBean tmpstorebean=allStoreBean.get(j);
                            allStoreBean.set(j,allStoreBean.get(i));
                            allStoreBean.set(i,tmpstorebean);

                            PoiItem tmppoiitem=allPoiItem.get(j);
                            allPoiItem.set(j,allPoiItem.get(i));
                            allPoiItem.set(i,tmppoiitem);
                        }
                    }
                }
                break;
            case "好评优先":
                for(int i=0;i<allStoreBean.size()-1;i++){
                    for(int j=i+1;j<allStoreBean.size();j++){
                        if(allStoreBean.get(i).getmStoreRating()<allStoreBean.get(j).getmStoreRating()){
                            StoreBean tmpstorebean=allStoreBean.get(j);
                            allStoreBean.set(j,allStoreBean.get(i));
                            allStoreBean.set(i,tmpstorebean);

                            PoiItem tmppoiitem=allPoiItem.get(j);
                            allPoiItem.set(j,allPoiItem.get(i));
                            allPoiItem.set(i,tmppoiitem);
                        }
                    }
                }
                break;
            case "人均最低":
                for(int i=0;i<allStoreBean.size()-1;i++){
                    for(int j=i+1;j<allStoreBean.size();j++){
                        if(    allStoreBean.get(j).getmStoreAvgCost()!=0 &&
                                ( allStoreBean.get(i).getmStoreAvgCost()>allStoreBean.get(j).getmStoreAvgCost()
                                || (allStoreBean.get(i).getmStoreAvgCost()==0 )   )     ){
                            StoreBean tmpstorebean=allStoreBean.get(j);
                            allStoreBean.set(j,allStoreBean.get(i));
                            allStoreBean.set(i,tmpstorebean);

                            PoiItem tmppoiitem=allPoiItem.get(j);
                            allPoiItem.set(j,allPoiItem.get(i));
                            allPoiItem.set(i,tmppoiitem);
                        }
                    }
                }
                break;
            case "人均最高":
                for(int i=0;i<allStoreBean.size()-1;i++){
                    for(int j=i+1;j<allStoreBean.size();j++){
                        if(allStoreBean.get(i).getmStoreAvgCost()<allStoreBean.get(j).getmStoreAvgCost()){
                            StoreBean tmpstorebean=allStoreBean.get(j);
                            allStoreBean.set(j,allStoreBean.get(i));
                            allStoreBean.set(i,tmpstorebean);

                            PoiItem tmppoiitem=allPoiItem.get(j);
                            allPoiItem.set(j,allPoiItem.get(i));
                            allPoiItem.set(i,tmppoiitem);
                        }
                    }
                }
                break;
        }

    }

    @Override
    public void filterPoi(String foodtype, String adname) {
        List<PoiItem> poiItem=myApplication.getPoiItemList();
        List<StoreBean> storeBeen=myApplication.getStoreBeanList();
        poiItem.clear();
        storeBeen.clear();
        for(int i=0;i<allPoiItem.size();i++){
            if( (foodtype.equals("全部美食") || allPoiItem.get(i).getTypeDes().split(";")[2].equals(foodtype))
                    &&(adname.equals("全城") || allPoiItem.get(i).getAdName().equals(adname))){
                poiItem.add(allPoiItem.get(i));
                storeBeen.add(allStoreBean.get(i));
            }
        }
        mSearchResultListView.updateListView();
    }

    @Override
    public void getPoiInfoFromMySer() {
        if(allPoiItem.size()==0){

        }
        String[] poiIDs=new String[allPoiItem.size()];
        for(int i=0;i<allPoiItem.size();i++){
            poiIDs[i]=allPoiItem.get(i).getPoiId();
        }
        mStoreModel.getStoreInfos(poiIDs);

    }

    @Override
    public void calDistance() {
        for(int i=0;i<allPoiItem.size();i++){
            LatLng tmplatlng=new LatLng(allPoiItem.get(i).getLatLonPoint().getLatitude(),allPoiItem.get(i).getLatLonPoint().getLongitude());
            allStoreBean.get(i).setmDistance(mLocationHelper.calDiatanceKM(loactionLatLng,tmplatlng));
        }
    }

    @Override
    public void clickRecyclerViewItem(int sel) {
        myApplication.setShowStorePoi(myApplication.getPoiItemList().get(sel));
        myApplication.setShowStoreBean(myApplication.getStoreBeanList().get(sel));
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                loactionLatLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());//获取经度
                locationCityName = aMapLocation.getCity();
                mPoiSearchModel.startPoiSearch(mSearchKey,myApplication.getAppTargetCity(),context,this);
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError",
                        "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());

            }
        }
    }

    @Override
    public void getStoreInfoOnSuccess(List<StoreBean> storelist) {

        for(int i=0;i<allPoiItem.size();i++){
            allStoreBean.add(new StoreBean(allPoiItem.get(i).getPoiId(),0,0));
            for(int j=0;j<storelist.size();j++){
                if(allPoiItem.get(i).getPoiId().equals(storelist.get(j).getmStoreID())){
                    allStoreBean.get(i).setmStoreAvgCost(storelist.get(j).getmStoreAvgCost());
                    allStoreBean.get(i).setmStoreRating(storelist.get(j).getmStoreRating());
                    storelist.remove(j);
                    break;
                }
            }

        }
        calDistance();
        resortPoi("离我最近");
        myApplication.getPoiItemList().addAll(this.allPoiItem);
        myApplication.getStoreBeanList().addAll(this.allStoreBean);
        mSearchResultListView.showPoiSearchSuccess();
        mSearchResultListView.updateListView();
    }

    @Override
    public void getStoreInfoOnError() {

    }
}

