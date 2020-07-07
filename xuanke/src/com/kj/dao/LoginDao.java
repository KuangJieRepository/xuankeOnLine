package com.kj.dao;

import com.kj.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface LoginDao {
    /**
     * 查询用户
     * @param account
     * @param password
     * @return
     */
    User findUser(@Param("account") String account, @Param("password") String password, @Param("identity") String identity);

    //将文件名写入数据库
    int setImage(@Param("id") Integer id, @Param("originalFilename") String originalFilename,@Param("identity")String identity);

}
