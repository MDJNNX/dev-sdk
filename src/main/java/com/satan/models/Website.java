/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2021/1/8
 */

package com.satan.models;

/**
 * @Description: 站点
 * @Authore: 马定健
 * @Date: 2021/1/8
 */
public class Website {

    /**
     * 图片路径
     */
    private String icon;

    /**
     * 网站名
     */
    private String name;

    /**
     * 网站地址
     */
    private String url;

    /**
     * 关键词
     */
    private String keywords;

    public Website() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "Website{" +
                "icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
