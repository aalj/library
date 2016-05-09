/**
 * LecturesTable.java
 * com.bestsnail.bean
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * 2016年5月2日 		liang
 * <p>
 * Copyright (c) 2016, TNT All Rights Reserved.
 */

package com.bestsnail.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:LecturesTable 讲座表 Function: TODO ADD FUNCTION Reason: TODO ADD
 * REASON
 *
 * @author liang
 * @version
 * @since Ver 1.1
 * @Date 2016年5月2日 下午4:42:41
 *
 * @see
 *
 */
public class LecturesTable implements Serializable {
    private int lec_id;
    private String lec_title;
    private String lec_info;
    private Date lec_time;
    private String lec_address;
    private String lec_person;
    private int lec_per_num;
    private Date create_time;
    private int lec_longtime;

    public LecturesTable(int lec_id, String lec_title, String lec_info, Date lec_time, String lec_address,
                         String lec_person, int lec_per_num, Date create_time) {
        super();
        this.lec_id = lec_id;
        this.lec_title = lec_title;
        this.lec_info = lec_info;
        this.lec_time = lec_time;
        this.lec_address = lec_address;
        this.lec_person = lec_person;
        this.lec_per_num = lec_per_num;
        this.create_time = create_time;
    }

    public LecturesTable() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LecturesTable) {
            LecturesTable name = (LecturesTable) obj;
            return (lec_id == name.lec_id);
        }
        return false;
    }

    public int hashCode() {
        return lec_id + "".hashCode();

    }

    public int getLec_id() {
        return lec_id;
    }

    public void setLec_id(int lec_id) {
        this.lec_id = lec_id;
    }

    public String getLec_title() {
        return lec_title;
    }

    public void setLec_title(String lec_title) {
        this.lec_title = lec_title;
    }

    public String getLec_info() {
        return lec_info;
    }

    public void setLec_info(String lec_info) {
        this.lec_info = lec_info;
    }

    public Date getLec_time() {
        return lec_time;
    }

    public void setLec_time(Date lec_time) {
        this.lec_time = lec_time;
    }

    public String getLec_address() {
        return lec_address;
    }

    public void setLec_address(String lec_address) {
        this.lec_address = lec_address;
    }

    public String getLec_person() {
        return lec_person;
    }

    public void setLec_person(String lec_person) {
        this.lec_person = lec_person;
    }

    public int getLec_per_num() {
        return lec_per_num;
    }

    public void setLec_per_num(int lec_per_num) {
        this.lec_per_num = lec_per_num;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getLec_longtime() {

        return lec_longtime;
    }

    public void setLec_longtime(int lec_longtime) {
        this.lec_longtime = lec_longtime;
    }
}
