package com.example.administrator.xiaozhongdianping.bean;

/**
 * 地区
 * Created by waka on 2016/1/4.
 */
public class District {
    private String districtName;
    private String zipcode;//邮政编码

    /**
     * 构造方法
     */
    public District() {

    }

    public District(String districtName, String zipcode) {
        this.districtName = districtName;
        this.zipcode = zipcode;
    }

    /**
     * get
     */
    public String getDistrictName() {
        return districtName;
    }

    public String getZipcode() {
        return zipcode;
    }

    /**
     * set
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
