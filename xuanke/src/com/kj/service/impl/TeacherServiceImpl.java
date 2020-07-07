package com.kj.service.impl;

import com.kj.dao.TeacherDao;
import com.kj.export.TeacherExport;
import com.kj.po.Student;
import com.kj.po.Teacher;
import com.kj.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    /*根据id查询教师信息，不包含密码*/
    @Override
    public List<Teacher> getTeacherById(Integer tid) {
        return teacherDao.getTeacherById(tid);
    }

    /*修改个人教师信息*/
    @Override
    public int updateMsg(Teacher teacher) {
        return teacherDao.updateMsg(teacher);
    }

    //查询出总数量（有条件时，查询条件查询的总数）
    @Override
    public int getCount(String name, String department) {
        return teacherDao.getCount(name, department);
    }

    //分页查询出学生数据（有条件时按条件分页查询）
    @Override
    public List<Teacher> findTeacherAll(String name, String department, int pageNo, int size, int maxPage) {
        //对页码进行设置
        //小于0的，设置为0
        if (pageNo < 1) {
            pageNo = 1;
        }
        //大于最大页数的设置为最大页码
        if (pageNo > maxPage) {
            pageNo = maxPage;
        }
        //起始位置start
        int start = (pageNo - 1) * size;
        if (start < 0) {
            start = 0;
        }
        return teacherDao.findTeacherAll(name, department, start, size);


    }

    //获取全部院系
    @Override
    public List<String> getDepartmentAll() {
        return teacherDao.getDepartmentAll();
    }

    //添加--1根据账号、密码、真实姓名,进行判断是否已经存在
    @Override
    public int findTeacher(String name, String account, String password) {

        return teacherDao.findTeacher(name, account, password);
    }

    //管理员添加教师
    @Override
    public int addTeacherAdmin(String name, String account, String password, String department, String sex, int age, String phone, String email) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setAccount(account);
        teacher.setPassword(password);
        teacher.setDepartment(department);
        teacher.setSex(sex);
        teacher.setAge(age);
        teacher.setPhone(phone);
        teacher.setEmail(email);
        return teacherDao.addTeacherAdmin(teacher);
    }

    /*根据id删除教师*/
    @Override
    public int deleteTeacher(Integer id) {
        return teacherDao.deleteTeacher(id);
    }

    //管理员-修改-教师
    @Override
    public int updateTeacherAdmin(String name, String account, String password, String department, String sex, int age, String phone, String email, int id) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setAccount(account);
        teacher.setPassword(password);
        teacher.setDepartment(department);
        teacher.setSex(sex);
        teacher.setAge(age);
        teacher.setPhone(phone);
        teacher.setEmail(email);
        teacher.setId(id);
        return teacherDao.updateTeacherAdmin(teacher);
    }

    //查询全部数据，导出Excel表格
    @Override
    public List<TeacherExport> getAllTeacher() {
        return teacherDao.getAllTeacher();
    }
}
