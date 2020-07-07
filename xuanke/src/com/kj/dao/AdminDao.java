package com.kj.dao;

import com.kj.po.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {
    //根据id查询管理员信息
    List<Admin> getTeacherById(Integer aid);

    //修改个人教师信息
    int updateMsg(Admin admin);

    //将文件名写入数据库
    int setImage(@Param("id") Integer id, @Param("originalFilename") String originalFilename);
}
