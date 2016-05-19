/**
 * BorrowTable.java
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
 * ClassName:BorrowTable Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 *
 * @author liang
 * @version
 * @since Ver 1.1
 * @Date 2016年5月2日 下午4:40:08
 *
 * @see
 * 
 */
public class BorrowTable implements Serializable{
	private int borrow_id;
	
	private Student student;
	private BookTable book;
	private Date borrow_time;
	@Override
	public String toString() {
		return "BorrowTable [borrow_id=" + borrow_id + ", student=" + student + ", book=" + book + ", borrow_time="
				+ borrow_time + ", remand_time=" + remand_time + ", borrow_is=" + borrow_is + "]";
	}

	private Date remand_time;
	/**
	 * 是否还书
	 */
	private int borrow_is;

	

	public BorrowTable(int borrow_id, Student student, BookTable book, Date borrow_time, Date remand_time,
			int borrow_is) {
		super();
		this.borrow_id = borrow_id;
		this.student = student;
		this.book = book;
		this.borrow_time = borrow_time;
		this.remand_time = remand_time;
		this.borrow_is = borrow_is;
	}

	public Date getRemand_time() {
		return remand_time;
	}

	public void setRemand_time(Date remand_time) {
		this.remand_time = remand_time;
	}

	public BorrowTable() {
		super();
	}

	public int getBorrow_id() {
		return borrow_id;
	}

	public void setBorrow_id(int borrow_id) {
		this.borrow_id = borrow_id;
	}


	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public BookTable getBook() {
		return book;
	}

	public void setBook(BookTable book) {
		this.book = book;
	}

	public Date getBorrow_time() {
		return borrow_time;
	}

	public void setBorrow_time(Date borrow_time) {
		this.borrow_time = borrow_time;
	}

	public int getBorrow_is() {
		return borrow_is;
	}

	public void setBorrow_is(int borrow_is) {
		this.borrow_is = borrow_is;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BorrowTable) {
			BorrowTable name = (BorrowTable) obj;
			return (borrow_id == name.borrow_id);
		}
		return false;
	}

	public int hashCode() {
		return borrow_id + "".hashCode();

	}




}
