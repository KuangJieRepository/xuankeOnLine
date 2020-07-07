package com.kj.service.impl;

import com.kj.dao.StudentDao;
import com.kj.export.StudentExport;
import com.kj.po.Course;
import com.kj.po.Student;
import com.kj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    //根据课程id查询，选择此课程的学生
    @Override
    public List<Student> findStudentByCourseId(int id, int start, int size) {
        if (start < 0) {
            start = 0;
        }
        return studentDao.findStudentByCourseId(id, start, size);
    }

    //查询出这些学生的总数
    @Override
    public int getCountByid(int id) {

        return studentDao.getCountByid(id);
    }

    /*根据id查询学生信息*/
    @Override
    public List<Student> getStudentById(Integer sid) {
        return studentDao.getStudentById(sid);
    }

    /*修改个人信息*/
    @Override
    public int updateMsg(Student student) {
        return studentDao.updateMsg(student);
    }

    //获取全部班级
    @Override
    public List<String> getClassAll() {
        return studentDao.getClassAll();
    }

    //获取全部院系
    @Override
    public List<String> getDepartmentAll() {
        return studentDao.getDepartmentAll();
    }

    //查询出总数量（有条件时，查询条件查询的总数）
    @Override
    public int getCount(String name, String classname, String department) {
        return studentDao.getCount(name, classname, department);
    }

    //分页查询出学生数据（有条件时按条件分页查询）
    @Override
    public List<Student> findStudentAll(String name, String classname, String department, int pageNo, int size, int maxPage) {
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
        return studentDao.findStudentAll(name, classname, department, start, size);


    }

    //根据id删除学生
    @Override
    public int deleteStudent(Integer id) {
        return studentDao.deleteStudent(id);
    }

    //管理员添加学生
    @Override
    public int addStudentAdmin(String name, String account, String password, String department, String sex, int age, String phone, String email, String classname) {
        Student student = new Student();
        student.setName(name);
        student.setAccount(account);
        student.setPassword(password);
        student.setDepartment(department);
        student.setSex(sex);
        student.setAge(age);
        student.setPhone(phone);
        student.setEmail(email);
        student.setClassname(classname);
        return studentDao.addStudentAdmin(student);
    }

    //管理员-修改-学生
    @Override
    public int updateStudentAdmin(String name, String account, String password, String department, String sex, int age, String phone, String email, String classname, int id) {
        Student student = new Student();
        student.setName(name);
        student.setAccount(account);
        student.setPassword(password);
        student.setDepartment(department);
        student.setSex(sex);
        student.setAge(age);
        student.setPhone(phone);
        student.setEmail(email);
        student.setClassname(classname);
        student.setId(id);
        return studentDao.updateStudentAdmin(student);
    }

    //添加--1根据账号、密码、真实姓名,进行判断是否已经存在
    @Override
    public int findStudent(String name, String account, String password) {

        return studentDao.findStudent(name, account, password);
    }

    //查询全部数据，导出Excel表格
    @Override
    public List<StudentExport> getAllStudent() {
        return studentDao.getAllStudent();
    }

    //查询课程下学生全部数据，导出Excel表格
    @Override
    public List<StudentExport> getAllStudentByCid(Integer cid) {
        return studentDao.getAllStudentByCid(cid);
    }


}
