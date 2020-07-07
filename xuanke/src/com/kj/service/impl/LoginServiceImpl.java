package com.kj.service.impl;

import com.kj.dao.LoginDao;
import com.kj.po.User;
import com.kj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户service实现层
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    /**
     * 查询用户
     * @param account
     * @param password
     * @return
     */
    @Override
    public User findUser(String account, String password,String identity) {
        return loginDao.findUser(account,password,identity);
    }

    //将文件名写入数据库
    @Override
    public int setImage(Integer id, String originalFilename,String identity) {
        return loginDao.setImage(id,originalFilename,identity);
    }
}
