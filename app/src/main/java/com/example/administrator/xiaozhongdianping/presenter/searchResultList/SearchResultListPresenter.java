package com.example.administrator.xiaozhongdianping.presenter.searchResultList;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public interface SearchResultListPresenter {
    String[] getAdNames();
    void resortPoi(String resorttype);
    void filterPoi(String foodtype,String adname);
    void getPoiInfoFromMySer();
    void calDistance();
    void clickRecyclerViewItem( int sel);

}
