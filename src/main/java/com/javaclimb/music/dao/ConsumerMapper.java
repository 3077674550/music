package com.javaclimb.music.dao;

import com.javaclimb.music.domain.Consumer;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface ConsumerMapper {

    //查找
    Consumer selectByPrimaryKey(Integer id);

    //增加
    int insert(Consumer consumer);

    //验证
    int verifyPassword(String username, String password);

    //根据账号查询
    Consumer existUsername(String username);

    //更新
    int updateUserMsg(Consumer record);

    //更新头像
    int updateUserAvator(Consumer record);

    //删除
    int deleteUser(Integer id);

    //分页查询所有用户
    List<Consumer> selectPageUser(Integer pageNum,Integer pageSize);

}
