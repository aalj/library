package com.bestsnail.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 作者：liang on 2016/5/3 20:05
 */
public class BookTable implements Serializable {

    /**
     * book_id : 2
     * llLibraryAddressTable : {"lc_id":1,"lc_name":"中区馆藏","lc_address":"中区"}
     * book_name : 感冒灵颗粒说明书
     * book_author : 999
     * book_isbn : 6901339913419
     * book_category_number : 6901339913419
     * book_publishing_house : 三九医药
     * book_price : 0.0
     * book_keywords : android
     * book_time : 2016-05-02 00:00:00
     * book_num : 1
     */

    private int book_id;
    private LlLibraryAddressTableBean llLibraryAddressTable;
    private String book_name;
    private String book_author;
    private String book_isbn;
    private String book_category_number;
    private String book_publishing_house;
    private Date book_publishing_house_time;
    private float book_price;
    private int book_borrow_num;
    private String book_keywords;
    private String book_image;
    private String book_cd_down;
    private String book_introduction;
    private Date book_time;
    private int book_num;

    public int getBook_borrow_num() {
        return book_borrow_num;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getBook_num() {
        return book_num;
    }

    public LlLibraryAddressTableBean getLlLibraryAddressTable() {
        return llLibraryAddressTable;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public String getBook_isbn() {
        return book_isbn;
    }

    public String getBook_category_number() {
        return book_category_number;
    }

    public String getBook_publishing_house() {
        return book_publishing_house;
    }

    public Date getBook_publishing_house_time() {
        return book_publishing_house_time;
    }

    public float getBook_price() {
        return book_price;
    }

    public String getBook_keywords() {
        return book_keywords;
    }

    public String getBook_image() {
        return book_image;
    }

    public String getBook_cd_down() {
        return book_cd_down;
    }

    public String getBook_introduction() {
        return book_introduction;
    }

    public Date getBook_time() {
        return book_time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BookTable) {
            BookTable name = (BookTable) obj;
            return (book_id == name.book_id);
        }
        return false;
    }

    public int hashCode() {
        return book_id + "".hashCode();

    }
}
