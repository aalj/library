/**
 * LecturesRegistrationTable.java
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
 * ClassName:LecturesRegistrationTable 讲座报名表 Function: TODO ADD FUNCTION Reason:
 * TODO ADD REASON
 *
 * @author liang
 * @version
 * @since Ver 1.1
 * @Date 2016年5月2日 下午4:41:51
 *
 * @see
 * 
 */
public class LecturesRegistrationTable implements Serializable{
	public LecturesRegistrationTable() {
		super();
	}

	public LecturesRegistrationTable(int lere_id, LecturesTable lecturesTable, Student student, Date create_time) {
		super();
		this.lere_id = lere_id;
		this.lecturesTable = lecturesTable;
		this.student = student;
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "LecturesRegistrationTable [lere_id=" + lere_id + ", lecturesTable=" + lecturesTable + ", student="
				+ student + ", create_time=" + create_time + "]";
	}

	public int getLere_id() {
		return lere_id;
	}

	public void setLere_id(int lere_id) {
		this.lere_id = lere_id;
	}

	public LecturesTable getLecturesTable() {
		return lecturesTable;
	}

	public void setLecturesTable(LecturesTable lecturesTable) {
		this.lecturesTable = lecturesTable;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	private int lere_id;
	private LecturesTable lecturesTable;
	private Student student;
	private Date create_time;
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LecturesRegistrationTable) {
			LecturesRegistrationTable name = (LecturesRegistrationTable) obj;
			return (lere_id == name.lere_id);
		}
		return false;
	}

	public int hashCode() {
		return lere_id + "".hashCode();

	}

}
