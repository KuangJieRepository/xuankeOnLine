package com.kj.po;

/**
 * 课程POJO
 *
 * @author 匡杰
 * @date
 */
public class Course {
    /**id*/
    private int id;
    /**课程名称*/
    private String coursename;
    /**所属院系*/
    private String department;
    /**上课时间*/
    private String time;
    /**老师id*/
    private int t_id;
    /**老师姓名*/
    private String t_name;
    /**科目*/
    private String category;
    /**学分*/
    private int credit;
    /**起始行*/
    private int start;
    /**一页显示记录数*/
    private  int size;
    /**是否被学生选过*/
    private String check;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", coursename='" + coursename + '\'' +
                ", department='" + department + '\'' +
                ", time='" + time + '\'' +
                ", t_id=" + t_id +
                ", t_name='" + t_name + '\'' +
                ", category='" + category + '\'' +
                ", credit=" + credit +
                ", start=" + start +
                ", size=" + size +
                ", check='" + check + '\'' +
                '}';
    }
}
