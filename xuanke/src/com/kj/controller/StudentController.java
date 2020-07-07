package com.kj.controller;

import com.kj.export.CourseExport;
import com.kj.export.StudentExport;
import com.kj.po.Course;
import com.kj.po.ResultInfo;
import com.kj.po.Student;
import com.kj.po.Teacher;
import com.kj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 根据课程id查询，选择此课程的学生
     *
     * @param id     课程id
     * @param pageNo 当前页
     * @param size   每页 显示的数据条数
     * @return
     */
    @RequestMapping("/findStudentByCourseId.do")
    @ResponseBody
    public ResultInfo findStudentByCourseId(int id, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5") int size) {
        if (pageNo <= 0) {
            pageNo = 1;
        }
        //查询出这些学生的总数
        int count = studentService.getCountByid(id);
        //最大页数
        int maxPage = (count % size == 0 ? count / size : count / size + 1);
        //起始位设置
        if (pageNo > maxPage) {
            pageNo = maxPage;
        }
        int start = (pageNo - 1) * size;
        //查询学生
        List<Student> studentList = studentService.findStudentByCourseId(id, start, size);
        return new ResultInfo(true, "", studentList, count, maxPage);
    }


    /*根据id查询学生信息*/
    @RequestMapping("/getStudentById.do")
    @ResponseBody
    public ResultInfo getStudentById(Integer sid) {
        List<Student> list = studentService.getStudentById(sid);
        return new ResultInfo(true, list);
    }


    /*修改个人信息*/
    @RequestMapping("/updateMsg.do")
    @ResponseBody
    public ResultInfo updateMsg(Student student) {
        int rows = studentService.updateMsg(student);
        if (rows > 0) {
            return new ResultInfo(true, "修改成功！");
        } else {
            return new ResultInfo(false, "修改失败！");
        }

    }

    //获取全部分类
    @RequestMapping("/getKindAll.do")
    @ResponseBody
    public ResultInfo getKindAll() {
        List<String> classList = studentService.getClassAll();
        List<String> departmentList = studentService.getDepartmentAll();
        Map kindAll = new HashMap();
        kindAll.put("classAll", classList);
        kindAll.put("departmentAll", departmentList);
        return new ResultInfo(true, kindAll);
    }

    //查询全部学生（有条件时，按条件查询）
    @RequestMapping("/getStudentAll.do")
    @ResponseBody
    public ResultInfo getStudentAll(String name, String classname, String department,@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5") int size) {
        //1、查询出总数量（有条件时，查询条件查询的总数）
        int count = studentService.getCount(name, classname, department);
        //2、计算出最大页码
        int maxPage=(count%size==0?count/size:count/size+1);
        //3、分页查询出学生数据（有条件时按条件分页查询）
        List<Student> studentList=studentService.findStudentAll(name,classname,department,pageNo,size,maxPage);
        return new ResultInfo(true,"",studentList,count,maxPage);

    }

    /*根据id删除学生*/
    @RequestMapping("/deleteStudent.do")
    @ResponseBody
    public ResultInfo deleteStudent(Integer id) {
        int rows = studentService.deleteStudent(id);
        if (rows > 0) {
            return new ResultInfo(true, "删除成功！");
        } else {
            return new ResultInfo(false, "删除失败！");
        }
    }

    /*管理员添加学生*/
    @RequestMapping("/addStudentAdmin.do")
    @ResponseBody
    public ResultInfo addStudentAdmin(String name,String account,String password,String department,String sex,@RequestParam(defaultValue ="0") int age,String phone,String email,String classname) {
        //1、根据账号、密码、真实姓名,进行判断是否已经存在
        int flag=studentService.findStudent(name,account,password);
        if (flag>0){
            return new ResultInfo(false, "该用户已存在！",-1,0);
        }
        //2、如果用户不存在则进行添加
        int rows=studentService.addStudentAdmin(name,account,password,department,sex,age,phone,email,classname);
        if (rows>0){
            return new ResultInfo(true, "添加成功！",rows,0);
        }else {
            return new ResultInfo(false, "添加失败！",rows,0);
        }
    }

    /*管理员-修改-学生*/
    @RequestMapping("/updateStudentAdmin.do")
    @ResponseBody
    public ResultInfo updateStudentAdmin(String name,String account,String password,String department,String sex,@RequestParam(defaultValue ="0") int age,String phone,String email,String classname,int id) {
        int rows=studentService.updateStudentAdmin(name,account,password,department,sex,age,phone,email,classname,id);
        if (rows>0){
            return new ResultInfo(true, "修改成功！");
        }else {
            return new ResultInfo(false, "修改失败！");
        }
    }
    //查询全部数据，导出Excel表格
    @RequestMapping("getAllStudent.do")
    @ResponseBody
    public List<StudentExport> getAllStudent(){

        List<StudentExport> list=studentService.getAllStudent();
        return list;
    }
    //查询课程下学生全部数据，导出Excel表格
    @RequestMapping("getAllStudentByCid.do")
    @ResponseBody
    public List<StudentExport> getAllStudentByCid(Integer cid){

        List<StudentExport> list=studentService.getAllStudentByCid(cid);
        return list;
    }
}
