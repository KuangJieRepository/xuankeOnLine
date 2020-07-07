package com.kj.service.impl;

import com.kj.dao.AdminDao;
import com.kj.po.Admin;
import com.kj.service.AdminSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSrviceImpl implements AdminSrvice {
    @Autowired
    private AdminDao adminDao;

    /*根据id查询管理员信息*/
    @Override
    public List<Admin> getAdminById(Integer aid) {
        return adminDao.getTeacherById(aid);
    }

    //修改个人教师信息
    @Override
    public int updateMsg(Admin admin) {
        return adminDao.updateMsg(admin);
    }

    //将文件名写入数据库
    @Override
    public int setImage(Integer id, String originalFilename) {
        return adminDao.setImage(id,originalFilename);
    }
}
