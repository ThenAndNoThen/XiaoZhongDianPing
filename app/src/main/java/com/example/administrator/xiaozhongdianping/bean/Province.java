package com.example.administrator.xiaozhongdianping.bean;

import java.util.List;

/**
 * 省
 * Created by waka on 2016/1/4.
 */
public class Province {
    private String provinceName;
    private List<City> cityList;

    /**
     * 构造方法
     */
    public Province() {

    }

    public Province(String provinceName, List<City> cityList) {
        this.provinceName = provinceName;
        this.cityList = cityList;
    }

    /**
     * get
     */
    public String getProvinceName() {
        return provinceName;
    }

    public List<City> getCityList() {
        return cityList;
    }

    /**
     * set
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
