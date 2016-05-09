/**
 * ResourceDynamicsTable.java
 * com.bestsnail.bean
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * 2016年5月8日 		liang
 * <p>
 * Copyright (c) 2016, TNT All Rights Reserved.
 */

package com.bestsnail.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:ResourceDynamicsTable Function: TODO ADD FUNCTION Reason: TODO ADD
 * REASON
 *
 * @author liang
 * @version
 * @since Ver 1.1
 * @Date 2016年5月8日 下午12:13:01
 *
 * @see
 *
 */
public class ResourceDynamicsTable implements Serializable {
    private int rdt_id;
    private String rdt_title;
    private String rdt_info;
    private Date rdt_time;
    private String rdt_author;

    public int getRdt_id() {
        return rdt_id;
    }

    public void setRdt_id(int rdt_id) {
        this.rdt_id = rdt_id;
    }

    public String getRdt_title() {
        return rdt_title;
    }

    public void setRdt_title(String rdt_title) {
        this.rdt_title = rdt_title;
    }

    public String getRdt_info() {
        return rdt_info;
    }

    public void setRdt_info(String rdt_info) {
        this.rdt_info = rdt_info;
    }

    public Date getRdt_time() {
        return rdt_time;
    }

    public void setRdt_time(Date rdt_time) {
        this.rdt_time = rdt_time;
    }

    public String getRdt_author() {
        return rdt_author;
    }

    public void setRdt_author(String rdt_author) {
        this.rdt_author = rdt_author;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResourceDynamicsTable) {
            ResourceDynamicsTable name = (ResourceDynamicsTable) obj;
            return (rdt_id == name.rdt_id);
        }
        return false;
    }

    public int hashCode() {
        return rdt_id + "".hashCode();

    }

}
