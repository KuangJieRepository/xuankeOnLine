package com.kj.dao;

import com.kj.export.StudentExport;
import com.kj.po.Course;
import com.kj.po.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentDao {
    //根据课程id查询，选择此课程的学生
    List<Student> findStudentByCourseId(@Param("id") int id, @Param("start") int start, @Param("size") int size);

    //查询出这些学生的总数
    int getCountByid(int id);

    /*根据id查询学生信息*/
    List<Student> getStudentById(Integer sid);

    /*修改个人信息*/
    int updateMsg(Student student);

    //获取全部班级
    @Select("select classname from student group by classname;")
    List<String> getClassAll();

    //获取全部院系
    @Select("select department from student group by department;")
    List<String> getDepartmentAll();

    //查询出总数量（有条件时，查询条件查询的总数）
    int getCount(@Param("name") String name, @Param("classname") String classname, @Param("department") String department);

    //分页查询出学生数据（有条件时按条件分页查询）
    List<Student> findStudentAll(@Param("name") String name, @Param("classname") String classname, @Param("department") String department, @Param("start") int start, @Param("size") int size);

    //根据id删除学生
    int deleteStudent(Integer id);

    //管理员添加学生
    int addStudentAdmin(Student student);

    //管理员-修改-学生
    int updateStudentAdmin(Student student);

    //添加--1根据账号、密码、真实姓名,进行判断是否已经存在
    int findStudent(@Param("name") String name, @Param("account") String account, @Param("password") String password);

    //查询全部数据，导出Excel表格
    @Select("select name,sex,age,phone,email,classname,department from student")
    List<StudentExport> getAllStudent();

    //查询课程下学生全部数据，导出Excel表格
    List<StudentExport> getAllStudentByCid(Integer cid);
}
