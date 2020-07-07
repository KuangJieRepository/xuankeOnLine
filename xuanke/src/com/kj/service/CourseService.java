package com.kj.service;

import com.kj.export.CourseExport;
import com.kj.po.Course;

import java.util.List;

public interface CourseService {
    /**
     * 查询全部课程
     *
     * @return
     */
    List<Course> findCourseByCondition(int pageNo, String coursename, String teachername, String department, String category, int start, int size);

    /*查询出科目*/
    List<String> findCategoryAll();

    /*查询出院系*/
    List<String> findDepartmentAll();

    //查询总记录数
    int getCount(String coursename, String t_name, String category, String department);

    //根据教师的id查询课程
    List<Course> getCourse(int t_id);

    //教师根据删除自己的课程
    int deleteCourse(int id);

    //根据课程id查询课程信息
    Course getCourseById(int id);

    //根据课程id和教师id修改课程信息
    int updateCourseById(String coursename, String time, String category, String department, String credit, int course_id, int teacher_id);

    //教师添加课程
    int addCourseT(String coursename, String time, String category, String department, int credit, int teacher_id);

    //学生查询自己的课程
    List<Course> getCourseS(int id);

    //学生删除自己的课程
    int deleteCourseStudent(int id, int ownid);

    //学生添加课程
    int addMyCourse(int c_id, int s_id);

    //admin添加课程
    int addCourseAdmin(String coursename, String time, String category, String department, int credit, String teacher_account);

    //查询全部数据，导出Excel表格
    List<CourseExport> getAllCourse();
}
