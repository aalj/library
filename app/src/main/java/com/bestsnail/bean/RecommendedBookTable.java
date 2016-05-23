/**
 * RecommendedBookTable.java
 * com.bestsnail.bean
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2016年5月2日 		liang
 *
 * Copyright (c) 2016, TNT All Rights Reserved.
*/

package com.bestsnail.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:RecommendedBookTable 图书推荐购买
 *  Function: TODO ADD FUNCTION Reason: TODO
 * ADD REASON
 *
 * @author liang
 * @version
 * @since Ver 1.1
 * @Date 2016年5月2日 下午4:44:11
 *
 * @see
 * 
 */
public class RecommendedBookTable implements Serializable {
	public RecommendedBookTable() {
		super();
	}

	public String getRebo_isbn() {
		return rebo_isbn;
	}

	public void setRebo_isbn(String rebo_isbn) {
		this.rebo_isbn = rebo_isbn;
	}

	public int getRebo_id() {
		return rebo_id;
	}

	public void setRebo_id(int rebo_id) {
		this.rebo_id = rebo_id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getRebo_book_name() {
		return rebo_book_name;
	}

	public void setRebo_book_name(String rebo_book_name) {
		this.rebo_book_name = rebo_book_name;
	}

	public String getRebo_anthor() {
		return rebo_anthor;
	}

	public void setRebo_anthor(String rebo_anthor) {
		this.rebo_anthor = rebo_anthor;
	}


	public String getRebo_press() {
		return rebo_press;
	}

	public void setRebo_press(String rebo_press) {
		this.rebo_press = rebo_press;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	private int rebo_id;
	private Student student;
	private String rebo_book_name;
	private String rebo_anthor;
	private String rebo_isbn;
	private String rebo_press;
	private Date create_time;

}
