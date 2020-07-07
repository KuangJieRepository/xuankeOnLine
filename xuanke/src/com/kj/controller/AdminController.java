package com.kj.controller;

import com.kj.po.Admin;
import com.kj.po.ResultInfo;
import com.kj.po.Teacher;
import com.kj.service.AdminSrvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminSrvice adminSrvice;

    /*根据id查询管理员信息*/
    @RequestMapping("/getAdminById.do")
    @ResponseBody
    public ResultInfo getAdminById(Integer aid) {
        List<Admin> list = adminSrvice.getAdminById(aid);
        return new ResultInfo(true, list);
    }

    /*修改个人教师信息*/
    @RequestMapping("/updateMsg.do")
    @ResponseBody
    public ResultInfo updateMsg(Admin admin) {
        int rows = adminSrvice.updateMsg(admin);
        if (rows > 0) {
            return new ResultInfo(true, "修改成功！");
        } else {
            return new ResultInfo(false, "修改失败！");
        }

    }
}
