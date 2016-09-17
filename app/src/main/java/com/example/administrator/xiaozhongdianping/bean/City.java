package com.example.administrator.xiaozhongdianping.bean;

import java.util.List;

/**
 * 城市
 * Created by waka on 2016/1/4.
 */
public class City {
    private String cityName;
    private List<District> districtList;

    /**
     * 构造方法
     */
    public City() {

    }

    public City(String cityName, List<District> districtList) {
        this.cityName = cityName;
        this.districtList = districtList;
    }

    /**
     * get
     */
    public String getCityName() {
        return cityName;
    }

    public List<District> getDistrictList() {
        return districtList;
    }

    /**
     * set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }
}
