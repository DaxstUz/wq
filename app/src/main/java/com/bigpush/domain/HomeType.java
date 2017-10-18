package com.bigpush.domain;

import java.io.Serializable;

public class HomeType implements Serializable {

    private String text;
    private String  commodityType;

    public HomeType() {
    }

    public HomeType(String text, String commodityType) {
        this.text = text;
        this.commodityType = commodityType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }
}
