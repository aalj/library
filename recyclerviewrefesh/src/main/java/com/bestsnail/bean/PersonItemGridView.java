package com.bestsnail.bean;

import java.io.Serializable;

/**
 * 作者：liang on 2016/4/29 19:39
 */
public class PersonItemGridView implements Serializable {
    private int imaRes = 0;
    private String ItemName = null;

    public PersonItemGridView(int imaRes, String itemName) {
        this.imaRes = imaRes;
        ItemName = itemName;
    }

    public int getImaRes() {

        return imaRes;
    }

    public void setImaRes(int imaRes) {
        this.imaRes = imaRes;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }
}
