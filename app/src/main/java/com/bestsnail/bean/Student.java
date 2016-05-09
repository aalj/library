/**
 * Student.java
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

import java.util.Date;

/**
 * ClassName:Student Function: TODO ADD FUNCTION Reason: TODO ADD REASON
 *
 * @author liang
 * @version
 * @since Ver 1.1
 * @Date 2016年5月2日 下午4:44:40
 *
 * @see
 *
 */
public class Student {

    private int stu_id;
    private String stu_name;
    private String stu_number;
    private String stu_pwd;
    private String stu_sex;
    private int stu_age;
    private String stu_college;
    private String stu_subject;
    private String stu_grade;
    private Date stu_create_time;

    public Student() {

    }

    public Student(int stu_id, String stu_name, String stu_number, String stu_pwd, String stu_sex, int stu_age,
                   String stu_college, String stu_subject, String stu_grade, Date stu_create_time) {
        super();
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.stu_number = stu_number;
        this.stu_pwd = stu_pwd;
        this.stu_sex = stu_sex;
        this.stu_age = stu_age;
        this.stu_college = stu_college;
        this.stu_subject = stu_subject;
        this.stu_grade = stu_grade;
        this.stu_create_time = stu_create_time;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_number() {
        return stu_number;
    }

    public void setStu_number(String stu_number) {
        this.stu_number = stu_number;
    }

    public String getStu_pwd() {
        return stu_pwd;
    }

    public void setStu_pwd(String stu_pwd) {
        this.stu_pwd = stu_pwd;
    }

    public String getStu_sex() {
        return stu_sex;
    }

    public void setStu_sex(String stu_sex) {
        this.stu_sex = stu_sex;
    }

    public int getStu_age() {
        return stu_age;
    }

    public void setStu_age(int stu_age) {
        this.stu_age = stu_age;
    }

    public String getStu_college() {
        return stu_college;
    }

    public void setStu_college(String stu_college) {
        this.stu_college = stu_college;
    }

    public String getStu_subject() {
        return stu_subject;
    }

    public void setStu_subject(String stu_subject) {
        this.stu_subject = stu_subject;
    }

    public String getStu_grade() {
        return stu_grade;
    }

    public void setStu_grade(String stu_grade) {
        this.stu_grade = stu_grade;
    }

    public Date getStu_create_time() {
        return stu_create_time;
    }

    public void setStu_create_time(Date stu_create_time) {
        this.stu_create_time = stu_create_time;
    }

}
