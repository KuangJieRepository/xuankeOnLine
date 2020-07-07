package com.kj.service;

import com.kj.po.Admin;

import java.util.List;

public interface AdminSrvice {
    /*根据id查询管理员信息*/
    List<Admin> getAdminById(Integer aid);

    //修改个人教师信息
    int updateMsg(Admin admin);

    //将文件名写入数据库
    int setImage(Integer id, String originalFilename);
}
