package com.example.zhangyuanke.istudyandroid.FCThirdPart;

import java.util.List;

/**
 * Created by zhangyuanke on 16/11/12.
 */

public class FFTeacher {

    private int id;
    private String name;

    private List<FFStudent> students;


    /**
     * 默认的构造方法必须不能省，不然不能解析
     */
    public FFTeacher() {

    }
    public FFTeacher(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<FFStudent> getStudents() {
        return students;
    }
    public void setStudents(List<FFStudent> students) {
        this.students = students;
    }
    @Override
    public String toString() {
        return "Teacher [id=" + id + ", name=" + name + ", mStudents="
                + students + "]";
    }


}