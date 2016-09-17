package com.example.administrator.xiaozhongdianping.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.xiaozhongdianping.R;
import com.example.administrator.xiaozhongdianping.ui.searchInput.SearchInputActivity;
import com.example.administrator.xiaozhongdianping.util.MyApplication;
import com.example.administrator.xiaozhongdianping.util.citypicker.Activity01;
import com.example.administrator.xiaozhongdianping.view.main.StoreFragmentView;


public class StoreFragment extends Fragment implements View.OnClickListener,StoreFragmentView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CardView mcvSearch;
    private LinearLayout mlayoutcity;
    private TextView mtvCity;
    private ImageView mimgCity;

    public StoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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

        //初始化控件
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        mlayoutcity = (LinearLayout) view.findViewById(R.id.layoutCity);
        mcvSearch = (CardView) view.findViewById(R.id.cvSearch);
        mtvCity = (TextView) view.findViewById(R.id.tvCity);
        mimgCity = (ImageView) view.findViewById(R.id.imgCityArrow);

        //添加响应
        mcvSearch.setOnClickListener(this);
        mlayoutcity.setOnClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cvSearch:
                Intent intnet=new Intent(getActivity(), SearchInputActivity.class);
                startActivity(intnet);
                break;
            case R.id.layoutCity:
                Intent intnet2=new Intent(getActivity(), Activity01.class);
                startActivity(intnet2);
                break;
        }
    }

    @Override
    public void showData() {
        String city=( (MyApplication)this.getActivity().getApplication()).getAppTargetCity();
        mtvCity.setText(city);
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }
}
