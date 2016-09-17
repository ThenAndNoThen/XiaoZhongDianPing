package com.example.administrator.xiaozhongdianping.ui.searchResultList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.presenter.searchResultList.SearchResultListPresenterImpl;
import com.example.administrator.xiaozhongdianping.ui.store.StoreActivity;
import com.example.administrator.xiaozhongdianping.ui.searchResultMap.SearchResultMapActivity;
import com.example.administrator.xiaozhongdianping.view.searchResultList.SearchResultListView;

public class SearchResultListActivity extends AppCompatActivity implements View.OnClickListener,SearchResultListView, AdapterView.OnItemSelectedListener {

    private LinearLayout mLinerLayoutBack,mLinerLayoutResultMap;
    private CardView mcvSearchKey;//搜索关键字
    private RecyclerView mRecyclerView;//结果列表
    private TextView mtvSearchKey,mtvLoadingstate;//搜索关键字
    private SearchResultRecyclerViewAdapter mAdapterResultList;//结果列表适配器
    private SearchResultListPresenterImpl msrlpresent;//p层
    private Spinner mspinnerFoodtype,mspinnerADname,mspinnerResort;//筛选排序下拉菜单

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_list);

        //初始化控件
        mLinerLayoutBack = (LinearLayout) findViewById(R.id.layoutBack);
        mLinerLayoutResultMap = (LinearLayout) findViewById(R.id.layoutResultMap);
        mRecyclerView = (RecyclerView) findViewById(R.id.resultlist_recyclerview);
        mspinnerFoodtype = (Spinner) findViewById(R.id.foodtype_spinner);
        mspinnerADname = (Spinner) findViewById(R.id.adname_spinner);
        mspinnerResort = (Spinner) findViewById(R.id.resort_spinner);
        mtvSearchKey = (TextView) findViewById(R.id.tvInput);
        mcvSearchKey = (CardView) findViewById(R.id.cvSearch);
        mtvLoadingstate = (TextView) findViewById(R.id.tv_loadingstate);

        //设置数据
        Bundle bundle=this.getIntent().getExtras();
        msrlpresent=new SearchResultListPresenterImpl(this,this,bundle.getString("search_key"));
        mtvSearchKey.setText(bundle.getString("search_key"));
        mAdapterResultList=new SearchResultRecyclerViewAdapter(this);
        String[] mItems = getResources().getStringArray(R.array.resorttype_stringarry);
        ArrayAdapter<String> resortadapter = new ArrayAdapter<String>(this, R.layout.item_myspinner_dropdown, mItems);
        mspinnerResort.setAdapter(resortadapter);
        mItems = getResources().getStringArray(R.array.foodtype_stringarry);
        ArrayAdapter<String> foodtypeadapter = new ArrayAdapter<String>(this, R.layout.item_myspinner_dropdown, mItems);
        mspinnerFoodtype.setAdapter(foodtypeadapter);
        mItems = msrlpresent.getAdNames();
        ArrayAdapter<String> adnameadapter = new ArrayAdapter<String>(this, R.layout.item_myspinner_dropdown, mItems);
        mspinnerADname.setAdapter(adnameadapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapterResultList);
        //设置监听
        mLinerLayoutBack.setOnClickListener(this);
        mcvSearchKey.setOnClickListener(this);
        mLinerLayoutResultMap.setOnClickListener(this);
        mspinnerResort.setOnItemSelectedListener(this);
        mspinnerFoodtype.setOnItemSelectedListener(this);
        mspinnerADname.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layoutBack:
                finish();
                break;
            case R.id.cvSearch:
                finish();
                break;
            case R.id.layoutResultMap:
                Intent intent=new Intent(this, SearchResultMapActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showPoiSearchFailed() {
        mtvLoadingstate.setText("加载失败！请检查网络或重试");
    }

    @Override
    public void updateListView() {
        this.mAdapterResultList.notifyDataSetChanged();
    }

    @Override
    public void gotoStoreDeatilActivity(int sel) {
        msrlpresent.clickRecyclerViewItem(sel);
        Intent intent=new Intent(this, StoreActivity.class);
        startActivity(intent);
    }

    @Override
    public void showPoiSearchSuccess() {
        mtvLoadingstate.setVisibility(TextView.GONE);
        mRecyclerView.setVisibility(TextView.VISIBLE);
    }

    @Override
    public void showNomatchResult() {
        mtvLoadingstate.setText("没有符合条件的商家！");
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.resort_spinner:
                msrlpresent.resortPoi(mspinnerResort.getSelectedItem().toString());
                msrlpresent.filterPoi(mspinnerFoodtype.getSelectedItem().toString(),mspinnerADname.getSelectedItem().toString());
                break;
            default:
                msrlpresent.filterPoi(mspinnerFoodtype.getSelectedItem().toString(),mspinnerADname.getSelectedItem().toString());
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

