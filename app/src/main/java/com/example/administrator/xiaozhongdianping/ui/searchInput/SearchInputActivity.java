package com.example.administrator.xiaozhongdianping.ui.searchInput;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.presenter.searchInput.SearchInputPresenterImpl;
import com.example.administrator.xiaozhongdianping.ui.searchResultList.SearchResultListActivity;
import com.example.administrator.xiaozhongdianping.view.searchInput.SearchInputView;

public class SearchInputActivity extends AppCompatActivity implements View.OnClickListener, SearchInputView {
    private LinearLayout mlayoutBack,mlayoutSearch;//后退和搜索键
    private AutoCompleteTextView mactvSearchKey;//输入框
    private SearchInputPresenterImpl mSearchInputPresenterImpl;//输入界面的presenter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_input);
        //初始化控件
        mlayoutBack = (LinearLayout) findViewById(R.id.layoutBack);
        mlayoutSearch = (LinearLayout) findViewById(R.id.layoutSearch);
        mactvSearchKey = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        //设置数据
        mSearchInputPresenterImpl=new SearchInputPresenterImpl(this);
        //设置获得焦点时自动弹出键盘
        mactvSearchKey.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        //设置监听
        mlayoutBack.setOnClickListener(this);
        mlayoutSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layoutBack:
                finish();
                break;
            case R.id.layoutSearch:
                mSearchInputPresenterImpl.clickSearch(mactvSearchKey.getText().toString());
                break;
        }
    }

    @Override
    public void toSearchResultActivity() {
        Intent intnet=new Intent(this, SearchResultListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("search_key", mactvSearchKey.getText().toString());
        intnet.putExtras(bundle);
        startActivity(intnet);
    }
}
