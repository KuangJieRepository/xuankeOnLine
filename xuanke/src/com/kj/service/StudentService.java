package com.kj.service;

import com.kj.export.CourseExport;
import com.kj.export.StudentExport;
import com.kj.po.Course;
import com.kj.po.Student;

import java.util.List;

public interface StudentService {
    //根据课程id查询，选择此课程的学生
    List<Student> findStudentByCourseId(int id, int start, int size);

    //查询出这些学生的总数
    int getCountByid(int id);

    /*根据id查询学生信息*/
    List<Student> getStudentById(Integer sid);

    /*修改个人信息*/
    int updateMsg(Student student);

    //获取全部班级
    List<String> getClassAll();

    //获取全部院系
    List<String> getDepartmentAll();

    //查询出总数量（有条件时，查询条件查询的总数）
    int getCount(String name, String classname, String department);

    //分页查询出学生数据（有条件时按条件分页查询）
    List<Student> findStudentAll(String name, String classname, String department, int pageNo, int size, int maxPage);

    //根据id删除学生
    int deleteStudent(Integer id);

    //管理员添加学生
    int addStudentAdmin(String name, String account, String password, String department, String sex, int age, String phone, String email, String classname);

    //管理员-修改-学生
    int updateStudentAdmin(String name, String account, String password, String department, String sex, int age, String phone, String email, String classname, int id);

    //添加--1根据账号、密码、真实姓名,进行判断是否已经存在
    int findStudent(String name, String account, String password);

    //查询全部数据，导出Excel表格
    List<StudentExport> getAllStudent();

    //查询课程下学生全部数据，导出Excel表格
    List<StudentExport> getAllStudentByCid(Integer cid);
}
