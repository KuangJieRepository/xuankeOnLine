package com.kj.dao;

import com.kj.export.TeacherExport;
import com.kj.po.Student;
import com.kj.po.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherDao {
    /*根据id查询教师信息，不包含密码*/
    List<Teacher> getTeacherById(Integer tid);

    /*修改个人教师信息*/
    int updateMsg(Teacher teacher);

    //查询出总数量（有条件时，查询条件查询的总数）
    int getCount(@Param("name") String name, @Param("department") String department);

    //分页查询出学生数据（有条件时按条件分页查询）
    List<Teacher> findTeacherAll(@Param("name") String name, @Param("department") String department, @Param("start") int start, @Param("size") int size);

    //获取全部院系
    @Select("select department from teacher group by department;")
    List<String> getDepartmentAll();

    //添加1根据账号、密码、真实姓名,进行判断是否已经存在
    int findTeacher(@Param("name") String name, @Param("account") String account, @Param("password") String password);

    //管理员添加教师
    int addTeacherAdmin(Teacher teacher);

    //根据id删除教师
    int deleteTeacher(Integer id);

    //管理员-修改-教师
    int updateTeacherAdmin(Teacher teacher);

    //查询全部数据，导出Excel表格
    @Select("select name,sex,age,phone,email,department from teacher")
    List<TeacherExport> getAllTeacher();
}
