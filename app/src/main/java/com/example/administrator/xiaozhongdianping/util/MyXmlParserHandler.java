package com.example.administrator.xiaozhongdianping.util;

import com.example.administrator.xiaozhongdianping.bean.City;
import com.example.administrator.xiaozhongdianping.bean.District;
import com.example.administrator.xiaozhongdianping.bean.Province;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义SAX解析器
 * Created by waka on 2016/1/4.
 */
public class MyXmlParserHandler extends DefaultHandler {

    //存储所有的解析对象
    private List<Province> mProvinceList;

    private Province mProvince;
    private City mCity;
    private District mDistrict;

    /**
     * 构造方法
     */
    public MyXmlParserHandler() {

    }

    /**
     * get
     */
    public List<Province> getmProvinceList() {
        return mProvinceList;
    }

    /**
     * 当读到第一个开始标签的时候，会触发这个方法
     *
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        mProvinceList = new ArrayList<Province>();//初始化provinceList
    }

    /**
     * 当遇到开始标记的时候，调用这个方法
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("province")) {
            mProvince = new Province();
            mProvince.setProvinceName(attributes.getValue(0));
            mProvince.setCityList(new ArrayList<City>());
        } else if (qName.equals("city")) {
            mCity = new City();
            mCity.setCityName(attributes.getValue(0));
            mCity.setDistrictList(new ArrayList<District>());
        } else if (qName.equals("district")) {
            mDistrict = new District();
            mDistrict.setDistrictName(attributes.getValue(0));
            mDistrict.setZipcode(attributes.getValue(1));
        }
    }

    /**
     * 遇到结束标记的时候，会调用这个方法
     *
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("district")) {
            mCity.getDistrictList().add(mDistrict);
        } else if (qName.equals("city")) {
            mProvince.getCityList().add(mCity);
        } else if (qName.equals("province")) {
            mProvinceList.add(mProvince);
        }
    }

}
