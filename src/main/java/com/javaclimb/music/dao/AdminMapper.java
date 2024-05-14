package com.javaclimb.music.dao;

import org.apache.ibatis.annotations.Mapper;

/*管理员dao*/
@Mapper
public interface AdminMapper {
    /* 验证密码是否正确*/
     int verifyPassword(String username, String password);
}