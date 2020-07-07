package com.kj.service;

import com.kj.po.User;

/**
 * 用户service层接口
 */
public interface LoginService {
    /**
     * 查询用户
     * @param account
     * @param password
     * @param identity
     * @return
     */
    User findUser(String account, String password, String identity);

    //将文件名写入数据库
    int setImage(Integer id, String originalFilename,String identity);
}
