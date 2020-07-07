package com.kj.controller;

import com.kj.export.CourseExport;
import com.kj.po.Course;
import com.kj.po.ResultInfo;
import com.kj.service.CourseService;
import com.sun.deploy.net.HttpResponse;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程course控制层
 *
 * @author 匡杰
 * @date
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 查询全部数据按条件
     */
    @RequestMapping("/findCourseByCondition.do")
    @ResponseBody
    public ResultInfo findCourseByCondition(@RequestParam(defaultValue = "0") Integer s_id, String coursename, String t_name, String department, String category, @RequestParam(defaultValue = "0") int start, @RequestParam(defaultValue = "6") int size, @RequestParam(defaultValue = "1") int pageNo) {
        /**查询条件查询的记录总数*/
        int count = getCount(coursename, t_name, category, department);
        //获取最大页数
        int maxPage = (count % size == 0 ? count / size : count / size + 1);
        /**查询全部数据按条件*/
        List<Course> list = courseService.findCourseByCondition(pageNo, coursename, t_name, department, category, start, size);
        /**学生自己课程的数据,有数据才进行判断*/
        List<Course> myCourse= courseService.getCourseS(s_id);
            if (myCourse!=null){
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < myCourse.size(); j++) {
                        if (list.get(i).getId() == myCourse.get(j).getId()){
                            list.get(i).setCheck("已选");
                            break;
                        }else {
                            list.get(i).setCheck("加入我的课程");
                        }
                    }
                }
            }

        return new ResultInfo(true, "", list, count, maxPage);
    }

    //查询全部类别
    @RequestMapping("/getKindAll.do")
    @ResponseBody
    public ResultInfo getKindAll() {
        //查询出全部department
        List<String> departmentAll = courseService.findDepartmentAll();
        //查询出全部category
        List<String> categoryAll = courseService.findCategoryAll();
        Map kindAll = new HashMap();
        kindAll.put("departmentAll", departmentAll);
        kindAll.put("categoryAll", categoryAll);
        return new ResultInfo(true, kindAll);
    }

    //查询总记录数
    @RequestMapping("/getCount.do")
    @ResponseBody
    public int getCount(String coursename, String t_name, String category, String department) {
        int getCount = courseService.getCount(coursename, t_name, category, department);
        return getCount;
    }

    //Teacher
    //根据教师的id查询课程
    @RequestMapping("/getCourse.do")
    @ResponseBody
    public ResultInfo getCourse(@RequestParam("id") int t_id) {
        List<Course> courseList = courseService.getCourse(t_id);
        return new ResultInfo(true, courseList);
    }

    //教师根据删除自己的课程
    @RequestMapping("/deleteCourse.do")
    @ResponseBody
    public ResultInfo deleteCourse(@RequestParam(value = "id", defaultValue = "0") int id) {
        int rows = courseService.deleteCourse(id);
        if (rows > 0) {
            return new ResultInfo(true);
        } else {
            return null;
        }
    }

    //根据课程id查询课程信息
    @RequestMapping("/getCourseById.do")
    @ResponseBody
    public ResultInfo getCourseById(int id) {
        Course course = courseService.getCourseById(id);
        return new ResultInfo(true, course);
    }

    //根据课程id和教师id修改课程信息
    @GetMapping("/updateCourseById.do")
    @ResponseBody
    public ResultInfo updateCourseById(String coursename, String time, String category, String department, String credit, int course_id, int teacher_id) {
        int rows = courseService.updateCourseById(coursename, time, category, department, credit, course_id, teacher_id);
        if (rows > 0) {
            return new ResultInfo(true, "修改成功！");
        } else {
            return null;
        }
    }

    //教师添加课程
    @RequestMapping("/addCourseT.do")
    @ResponseBody
    public ResultInfo addCourseT(String coursename, String time, String category, String department, int credit, int teacher_id) {

        int rows = courseService.addCourseT(coursename, time, category, department, credit, teacher_id);
        if (rows > 0) {
            return new ResultInfo(true, "添加成功！");
        } else {
            return new ResultInfo(false, "添加失败，该课程已存在！");
        }
    }

    //Student
    //学生查询自己的课程
    @RequestMapping("/getCourseS.do")
    @ResponseBody
    public ResultInfo getCourseS(int id) {
        List<Course> courseList = courseService.getCourseS(id);
        return new ResultInfo(true, courseList);

    }

    //学生删除自己的课程
    @RequestMapping("/deleteCourseStudent.do")
    @ResponseBody
    public ResultInfo deleteCourseStudent(@RequestParam(value = "id", defaultValue = "0") int id, int ownid) {
        int rows = courseService.deleteCourseStudent(id, ownid);
        if (rows > 0) {
            return new ResultInfo(true);
        } else {
            return null;
        }
    }
    //学生选择课程
    @RequestMapping("/addMyCourse.do")
    @ResponseBody
    public ResultInfo addMyCourse(int c_id,int s_id){
        int rows=courseService.addMyCourse(c_id,s_id);
        if (rows > 0) {
            return new ResultInfo(true);
        } else {
            return new ResultInfo(false,"该课程已选择过！");
        }
    }

    //admin添加课程
    @RequestMapping("/addCourseAdmin.do")
    @ResponseBody
    public ResultInfo addCourseAdmin(String coursename, String time, String category, String department, int credit,String teacher_account){

        int flag=courseService.addCourseAdmin(coursename, time, category, department, credit,teacher_account);
        if (flag==-1){
            return new ResultInfo(false,"该课程信息已存在！",flag,0,0);
        }
        if (flag==-2){
            return new ResultInfo(false,"该工号不存在！",flag,0,0);
        }
        if (flag==0){
            return new ResultInfo(false,"出现异常，添加失败！",flag,0,0);
        }
        if (flag>0){
            return new ResultInfo(true,"添加成功！",flag,0,0);
        }
        return null;
    }

    //查询全部数据，导出Excel表格
    @RequestMapping("getAllCourse.do")
    @ResponseBody
    public List<CourseExport> getAllCourse(){

        List<CourseExport> list=courseService.getAllCourse();
        return list;
    }

}
