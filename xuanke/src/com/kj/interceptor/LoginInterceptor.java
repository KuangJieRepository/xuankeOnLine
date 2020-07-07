package com.kj.interceptor;


import com.kj.po.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //1、获取请求的URL
        String url=request.getRequestURI();
        //拦截login外的所有清求
        if (url.indexOf("/login")>=0){
            return true;
        }
        HttpSession session=request.getSession();
        //获取session进行判断，空的就是没有登陆，返回登录界面
        User user_session = (User) session.getAttribute("USER_SESSION");
        if (user_session!=null){
            return true;
        }
        //没有登陆的返回登录界面，并提示
        response.sendRedirect("login.html");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
