package com.javaclimb.music.dao;

import com.javaclimb.music.domain.Consumer;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface ConsumerMapper {

    //增加
    int insert(Consumer consumer);

    //修改
    int update(Consumer consumer);

    //删除
    int delete(Integer id);

    //根据主键查询整个对象
    Consumer selectByPrimaryKey(Integer id);

    //查询所有用户
    public List<Consumer> allConsumer();

    //验证密码
    int vertifyPassword(String username,String password);

    //根据账号查询
    Consumer getByUsername(String username);

}
