package com.kj.controller;

import com.kj.export.StudentExport;
import com.kj.export.TeacherExport;
import com.kj.po.ResultInfo;
import com.kj.po.Student;
import com.kj.po.Teacher;
import com.kj.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * teacher 控制层
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /*根据id查询教师信息*/
    @RequestMapping("/getTeacherById.do")
    @ResponseBody
    public ResultInfo getTeacherById(Integer tid){
        List<Teacher> list = teacherService.getTeacherById(tid);
        return new ResultInfo(true, list);
    }
    /*修改个人教师信息*/
    @RequestMapping("/updateMsg.do")
    @ResponseBody
    public ResultInfo updateMsg(Teacher teacher){
        int rows = teacherService.updateMsg(teacher);
        if (rows>0){
            return new ResultInfo(true, "修改成功！");
        }else {
            return new ResultInfo(false, "修改失败！");
        }

    }
    //获取全部分类
    @RequestMapping("/getKindAll.do")
    @ResponseBody
    public ResultInfo getKindAll() {
        List<String> departmentList = teacherService.getDepartmentAll();
        return new ResultInfo(true, departmentList);
    }
    //查询全部教师（有条件时，按条件查询）
    @RequestMapping("/getTeacherAll.do")
    @ResponseBody
    public ResultInfo getTeacherAll(String name, String department,@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5") int size) {
        //1、查询出总数量（有条件时，查询条件查询的总数）
        int count = teacherService.getCount(name, department);
        //2、计算出最大页码
        int maxPage=(count%size==0?count/size:count/size+1);
        //3、分页查询出教师数据（有条件时按条件分页查询）
        List<Teacher> TeacherList=teacherService.findTeacherAll(name,department,pageNo,size,maxPage);
        return new ResultInfo(true,"",TeacherList,count,maxPage);

    }

    /*管理员添加教师*/
    @RequestMapping("/addTeacherAdmin.do")
    @ResponseBody
    public ResultInfo addTeacherAdmin(String name,String account,String password,String department,String sex,@RequestParam(defaultValue ="0") int age,String phone,String email) {
        //1、根据账号、密码、真实姓名,进行判断是否已经存在
        int flag=teacherService.findTeacher(name,account,password);
        if (flag>0){
            return new ResultInfo(false, "该用户已存在！",-1,0);
        }
        //2、如果用户不存在则进行添加
        int rows=teacherService.addTeacherAdmin(name,account,password,department,sex,age,phone,email);
        if (rows>0){
            return new ResultInfo(true, "添加成功！",rows,0);
        }else {
            return new ResultInfo(false, "添加失败！",rows,0);
        }
    }
    /*根据id删除教师*/
    @RequestMapping("/deleteTeacher.do")
    @ResponseBody
    public ResultInfo deleteTeacher(Integer id) {
        int rows = teacherService.deleteTeacher(id);
        if (rows > 0) {
            return new ResultInfo(true, "删除成功！");
        } else {
            return new ResultInfo(false, "删除失败！");
        }
    }
    /*管理员-修改-教师*/
    @RequestMapping("/updateTeacherAdmin.do")
    @ResponseBody
    public ResultInfo updateTeacherAdmin(String name,String account,String password,String department,String sex,@RequestParam(defaultValue ="0") int age,String phone,String email,int id) {
        int rows=teacherService.updateTeacherAdmin(name,account,password,department,sex,age,phone,email,id);
        if (rows>0){
            return new ResultInfo(true, "修改成功！");
        }else {
            return new ResultInfo(false, "修改失败！");
        }
    }

 //查询全部数据，导出Excel表格
    @RequestMapping("getAllTeacher.do")
    @ResponseBody
    public List<TeacherExport> getAllTeacher(){

        List<TeacherExport> list=teacherService.getAllTeacher();
        return list;
    }



}
