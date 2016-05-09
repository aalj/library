/**
 * NewsTable.java
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
 * ClassName:NewsTable Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 *
 * @author liang
 * @version
 * @since Ver 1.1
 * @Date 2016年5月8日 下午12:12:33
 *
 * @see
 *
 */
public class NewsTable implements Serializable {
    private int news_id;
    private String news_title;
    private String news_info;
    private String news_author;
    private Date news_time;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NewsTable) {
            NewsTable name = (NewsTable) obj;
            return (news_id == name.news_id);
        }
        return false;
    }

    public int hashCode() {
        return news_id + "".hashCode();

    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_info() {
        return news_info;
    }

    public void setNews_info(String news_info) {
        this.news_info = news_info;
    }

    public String getNews_author() {
        return news_author;
    }

    public void setNews_author(String news_author) {
        this.news_author = news_author;
    }

    public Date getNews_time() {
        return news_time;
    }

    public void setNews_time(Date news_time) {
        this.news_time = news_time;
    }

}
