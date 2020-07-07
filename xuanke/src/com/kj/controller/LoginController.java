package com.kj.controller;

import com.kj.po.ResultInfo;
import com.kj.po.User;
import com.kj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 用户控制层
 *
 * @author 匡杰
 * @date
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService userService;

    //查询用户
    @RequestMapping("/login.do")
    @ResponseBody
    public ResultInfo login(String account, String password,String identity, HttpSession session) {
        User user = userService.findUser(account, password,identity);
        user.setIdentity(identity);
        if (user != null) {
            session.setAttribute("USER_SESSION", user);
            return new ResultInfo(true, "登录成功！", user);
        } else {
            return new ResultInfo(false, "账号或密码错误，请重新输入！");
        }
    }

    // 查询session判断是否登陆
    @RequestMapping("/getSession.do")
    @ResponseBody
    public ResultInfo getSession(HttpSession session) {
        User user = (User) session.getAttribute("USER_SESSION");
        if (user!=null){
            return new ResultInfo(true, "用户已登录！", user);
        } else{
            return new ResultInfo(false, "用户未登录，请先登录！");
        }
    }
    // 退出登录
    @RequestMapping("/logout.do")
    public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        session.invalidate();
//        request.getRequestDispatcher("web/login.html").forward(request,response);
        response.sendRedirect("login.html");

    }

    //更改头像
    //上传头像
    @RequestMapping("/imageUpload.do")
    public String imageUpload(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile uploadFile, @RequestParam("identity")String identity, HttpServletRequest request, HttpServletResponse response) {
        //判断上传文件是否存在
        if (!uploadFile.isEmpty() && uploadFile.getSize() > 0) {
            //获取文件的原始名称
            String originalFilename = uploadFile.getOriginalFilename();
            //设置上传的文件的保存的地址
            String dirPath = request.getServletContext().getRealPath("/upload/");
            File filePath = new File(dirPath);
            //如果地址不存在，先创建目录
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            //使用UUID重新命名
            String newName = UUID.randomUUID() + "" + originalFilename;
            //使用MultipartFile 接口的方法完成文件上传到指定位置
            try {
                uploadFile.transferTo(new File(dirPath + newName));
                //将文件名写入数据库
                int flag = userService.setImage(id, newName, identity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "success.jsp";
        }
        return  null;
    }
    @RequestMapping("fanhui.do")
    @ResponseBody
    public ResultInfo fanhui(){
        return new ResultInfo(true);
    }
}
