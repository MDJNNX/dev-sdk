/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2021/1/8
 */

package com.satan.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Websites
 * @Authore: 马定健
 * @Date: 2021/1/8
 */
public class OnlineWebsites {

    private List<Website> websites = new ArrayList<>();

    public List<Website> getWebsites() {
        return websites;
    }

    public void setWebsites(List<Website> websites) {
        this.websites = websites;
    }

    @Override
    public String toString() {
        return "OnlineWebsites{" +
                "websites=" + websites +
                '}';
    }
}
