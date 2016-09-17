package com.example.administrator.xiaozhongdianping.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.bean.OrderBean;
import com.example.administrator.xiaozhongdianping.presenter.Main.OrderHistoryPresenterImpl;
import com.example.administrator.xiaozhongdianping.ui.store.StoreGoodsRecyclerViewAdapter;
import com.example.administrator.xiaozhongdianping.view.main.OrderHistoryView;

import java.util.List;


public class OrderFragment extends Fragment implements OrderHistoryView, OrderHistoryRecyclerViewAdapter.RecyclerViewItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;

    private OrderHistoryRecyclerViewAdapter mAdapter;
    private RecyclerView mRecycler;
    private OrderHistoryPresenterImpl mOrderHitoryPresenter;



    public OrderFragment() {
        // Required empty public constructor
    }

    public void setContext(Context context){
        this.context=context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2,Context context) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        fragment.setContext(context);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        mRecycler = (RecyclerView) view.findViewById(R.id.orderhistory_recyclerview);

        mRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter=new OrderHistoryRecyclerViewAdapter(this.getActivity());
        mRecycler.setAdapter(mAdapter);
        mOrderHitoryPresenter=new OrderHistoryPresenterImpl(context,this);
        mAdapter.setItemClickListener(this);
        return view;
    }


    @Override
    public void showData(List<OrderBean> orderBeanList) {
        mAdapter.setOrderBeanList(orderBeanList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void toOrderDetailActivity() {
        Intent intent=new Intent(context,OrderDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mOrderHitoryPresenter.initData();
    }

    @Override
    public void itemOnclick(int position) {
        mOrderHitoryPresenter.clickRecclerViewItem(mAdapter.getOrderAt(position));

    }
}
