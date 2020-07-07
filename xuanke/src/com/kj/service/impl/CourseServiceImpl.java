package com.kj.service.impl;

import com.kj.dao.CourseDao;
import com.kj.export.CourseExport;
import com.kj.po.Course;
import com.kj.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    /**
     * 查询全部课程
     *
     * @return
     */
    @Override
    public List<Course> findCourseByCondition(int pageNo, String coursename, String t_name, String department, String category, int start, int size) {
        //获取总记录数
        int count = courseDao.getCount(coursename, t_name, category, department);
        //获取最大页数
        int maxPage = (count % size == 0 ? count / size : count / size + 1);
        Course course = new Course();
        course.setCoursename(coursename);
        course.setT_name(t_name);
        course.setCategory(category);
        course.setDepartment(department);
        course.setStart((pageNo - 1) * size);
        //设置起始位置
        //如果pageNo小于0，设置起始为0
        if (pageNo <= 0) {
            course.setStart(0);
        }
        if (pageNo > maxPage) {
            course.setStart((maxPage - 1) * size);
        }
        if (course.getStart() < 0) {
            course.setStart(0);
        }
        course.setSize(size);
        return courseDao.findCourseByCondition(course);
    }

    //查询出科目
    @Override
    public List<String> findCategoryAll() {
        return courseDao.findCategoryAll();
    }

    //查询出院系
    @Override
    public List<String> findDepartmentAll() {
        return courseDao.findDepartmentAll();
    }

    //查询总记录数
    @Override
    public int getCount(String coursename, String t_name, String category, String department) {
        return courseDao.getCount(coursename, t_name, category, department);
    }

    //根据教师的id查询课程
    @Override
    public List<Course> getCourse(int t_id) {
        return courseDao.getCourse(t_id);
    }

    //教师根据删除自己的课程
    @Override
    public int deleteCourse(int id) {
        return courseDao.deleteCourse(id);
    }

    //根据课程id查询课程信息
    @Override
    public Course getCourseById(int id) {
        return courseDao.getCourseById(id);
    }

    //根据课程id和教师id修改课程信息
    @Override
    public int updateCourseById(String coursename, String time, String category, String department, String credit, int course_id, int teacher_id) {
        return courseDao.updateCourseById(coursename, time, category, department, credit, course_id, teacher_id);
    }

    //教师添加课程
    @Override
    public int addCourseT(String coursename, String time, String category, String department, int credit, int teacher_id) {

        //1、对课程查询，判断该课程是否已经存在
        Course course = courseDao.findCourseOnly(coursename, time, category, department, credit, teacher_id);
        if (course != null) {
            //说明课程已存在，返回失败
            return 0;
        }
        //2、rows=0 说明可以添加
        return courseDao.addCourseT(coursename, time, category, department, credit, teacher_id);
    }

    //学生查询自己的课程
    @Override
    public List<Course> getCourseS(int id) {
        return courseDao.getCourseS(id);
    }

    //学生删除自己的课程
    @Override
    public int deleteCourseStudent(int id, int ownid) {
        return courseDao.deleteCourseStudent(id, ownid);
    }

    //学生添加课程
    @Override
    public int addMyCourse(int c_id, int s_id) {
        //判断该课程是否已经加入过
        int check = courseDao.check(c_id, s_id);
        if (check > 0) {
            return 0;
        }
        return courseDao.addMyCourse(c_id, s_id);
    }

    //admin添加课程
    @Override
    public int addCourseAdmin(String coursename, String time, String category, String department, int credit, String teacher_account) {
        //1、判断课程是否存在
        Course course = courseDao.findCourseOnlyAdmin(coursename, category, department, credit, teacher_account);
        //不等于空说明该课程已经存在
        if (course != null) {
            //-1代表课程已存在的错误信息
            return -1;
        }
        //2、根据teacher_account，查询出教师id，设置添加的课程中
        Integer tid = courseDao.findId(teacher_account);
        if (tid == null) {
            return -2;
        }
        //3、添加课程
        int rows = courseDao.addCourseAdmin(coursename, time, category, department, credit, tid);
        return rows;
    }

    //查询全部数据，导出Excel表格
    @Override
    public List<CourseExport> getAllCourse() {
        return courseDao.getAllCourse();
    }


}
