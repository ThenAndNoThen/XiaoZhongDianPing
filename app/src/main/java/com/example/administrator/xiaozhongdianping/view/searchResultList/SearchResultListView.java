package com.example.administrator.xiaozhongdianping.view.searchResultList;

import com.example.administrator.xiaozhongdianping.view.View;

/**
 * Created by Administrator on 2016/4/30 0030.
 */
public interface SearchResultListView extends View{
    void showPoiSearchFailed();
    void updateListView();
    void gotoStoreDeatilActivity(int sel);
    void showPoiSearchSuccess();
    void showNomatchResult();
}
