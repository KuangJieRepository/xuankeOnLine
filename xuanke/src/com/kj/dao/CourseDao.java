package com.kj.dao;

import com.kj.export.CourseExport;
import com.kj.po.Course;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseDao {
    /**
     * 查询全部课程
     *
     * @return
     */
    List<Course> findCourseByCondition(Course course);

    /**
     * 查询全部科目
     *
     * @return
     */
    @Select("select category from course group by category")
    List<String> findCategoryAll();

    /**
     * 查询全部院系
     *
     * @return
     */
    @Select("select department from course group by department")
    List<String> findDepartmentAll();

    //查询总记录数
    int getCount(@Param("coursename") String coursename, @Param("t_name") String t_name, @Param("category") String category, @Param("department") String department);

    //根据教师的id查询课程
    List<Course> getCourse(int t_id);

    //教师根据删除自己的课程
    int deleteCourse(int id);

    //根据课程id查询课程信息
    Course getCourseById(int id);

    //根据课程id和教师id修改课程信息
    int updateCourseById(@Param("coursename") String coursename, @Param("time") String time, @Param("category") String category, @Param("department") String department, @Param("credit") String credit, @Param("course_id") int course_id, @Param("teacher_id") int teacher_id);

    //对课程查询，判断该课程是否已经存在
    Course findCourseOnly(@Param("coursename") String coursename, @Param("time") String time, @Param("category") String category, @Param("department") String department, @Param("credit") int credit, @Param("teacher_id") int teacher_id);

    //    //教师添加课程
    int addCourseT(@Param("coursename") String coursename, @Param("time") String time, @Param("category") String category, @Param("department") String department, @Param("credit") int credit, @Param("teacher_id") int teacher_id);

    //学生查询自己的课程
    List<Course> getCourseS(int id);

    //学生删除自己的课程
    int deleteCourseStudent(@Param("id") int id, @Param("ownid") int ownid);

    //学生添加课程
    int addMyCourse(@Param("c_id") int c_id, @Param("s_id") int s_id);

    //管理员添加课程，判断课程是否唯一
    Course findCourseOnlyAdmin(@Param("coursename") String coursename, @Param("category") String category, @Param("department") String department, @Param("credit") int credit, @Param("teacher_account") String teacher_account);

    //管理员添加课程,根据teacher_account，查询出教师id，设置添加的课程中
    Integer findId(String teacher_account);


    //管理员添加课程
    int addCourseAdmin(@Param("coursename") String coursename, @Param("time") String time, @Param("category") String category, @Param("department") String department, @Param("credit") int credit, @Param("tid") int tid);

    //判断该课程是否已经加入过
    int check(@Param("c_id") int c_id, @Param("s_id") int s_id);

    //查询全部数据，导出Excel表格
    List<CourseExport> getAllCourse();
}
