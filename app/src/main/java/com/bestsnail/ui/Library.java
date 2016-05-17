package com.bestsnail.ui;

import android.app.Application;

import com.bestsnail.bean.Student;

/**
 * 作者：liang on 2016/5/9 21:05
 */
public class Library extends Application {
    private Student student = null;

    public Student getStudent() {

        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public void onCreate() {
        student = new Student();

        super.onCreate();
//        LeakCanary.install(this);
    }
}
