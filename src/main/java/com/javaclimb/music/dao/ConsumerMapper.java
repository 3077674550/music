package com.javaclimb.music.dao;

import com.javaclimb.music.domain.Consumer;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface ConsumerMapper {

    int deleteByPrimaryKey(Integer id);

    //增加
    int insert(Consumer consumer);

    int insertSelective(Consumer record);

    Consumer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Consumer record);

    int updateByPrimaryKey(Consumer record);

    int verifyPassword(String username, String password);

    int existUsername(String username);

    int addUser(Consumer consumer);

    int updateUserMsg(Consumer record);

    int updateUserAvator(Consumer record);

    int deleteUser(Integer id);

    List<Consumer> allUser();

    List<Consumer> userOfId(Integer id);

    List<Consumer> loginStatus(String username);

}
