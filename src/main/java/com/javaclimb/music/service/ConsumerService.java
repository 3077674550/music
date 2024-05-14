package com.javaclimb.music.service;

import com.javaclimb.music.domain.Consumer;

import java.util.List;

public interface ConsumerService {

    //增加
    boolean insert(Consumer consumer);

    //修改
    boolean update(Consumer consumer);

    //删除
    boolean delete(Integer id);

    //根据主键查询整个对象
    Consumer selectByPrimaryKey(Integer id);

    //查询所有用户
    List<Consumer> allConsumer();

    //修改密码
    boolean vertifyPassword(String username,String password);

    //根据账号查询
    Consumer getByUsername(String username);

}
