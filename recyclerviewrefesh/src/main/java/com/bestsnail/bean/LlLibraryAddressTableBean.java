package com.bestsnail.bean;

import java.io.Serializable;

public class LlLibraryAddressTableBean implements Serializable {
    private int lc_id;
    private String lc_name;
    private String lc_address;

    public int getLc_id() {
        return lc_id;
    }

    public void setLc_id(int lc_id) {
        this.lc_id = lc_id;
    }

    public String getLc_name() {
        return lc_name;
    }

    public void setLc_name(String lc_name) {
        this.lc_name = lc_name;
    }

    public String getLc_address() {
        return lc_address;
    }

    public void setLc_address(String lc_address) {
        this.lc_address = lc_address;
    }
}