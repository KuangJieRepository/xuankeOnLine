package com.kj.service;

import com.kj.export.TeacherExport;
import com.kj.po.Student;
import com.kj.po.Teacher;

import java.util.List;

public interface TeacherService {
    /*根据id查询教师信息，不包含密码*/
    List<Teacher> getTeacherById(Integer tid);

    /*修改个人教师信息*/
    int updateMsg(Teacher teacher);

    //查询出总数量（有条件时，查询条件查询的总数）
    int getCount(String name, String department);

    //分页查询出学生数据（有条件时按条件分页查询）
    List<Teacher> findTeacherAll(String name, String department, int pageNo, int size, int maxPage);

    //获取全部院系
    List<String> getDepartmentAll();

    //添加--1根据账号、密码、真实姓名,进行判断是否已经存在
    int findTeacher(String name, String account, String password);

    //管理员添加教师
    int addTeacherAdmin(String name, String account, String password, String department, String sex, int age, String phone, String email);

    /*根据id删除教师*/
    int deleteTeacher(Integer id);

    //管理员-修改-教师
    int updateTeacherAdmin(String name, String account, String password, String department, String sex, int age, String phone, String email, int id);

    //查询全部数据，导出Excel表格
    List<TeacherExport> getAllTeacher();
}
