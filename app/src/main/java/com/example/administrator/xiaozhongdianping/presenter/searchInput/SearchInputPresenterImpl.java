package com.example.administrator.xiaozhongdianping.presenter.searchInput;

import com.example.administrator.xiaozhongdianping.view.searchInput.SearchInputView;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class SearchInputPresenterImpl implements SearchInputPresenter{
    private SearchInputView mSearchInputView;
    public SearchInputPresenterImpl(SearchInputView searchInputView){
        this.mSearchInputView=searchInputView;

    }
    @Override
    public void clickSearch(String searchKey) {
        //如果输入框不为空
        if(!searchKey.equals("")){
            mSearchInputView.toSearchResultActivity();
        }
    }
}
