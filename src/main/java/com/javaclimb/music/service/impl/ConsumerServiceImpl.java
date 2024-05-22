package com.javaclimb.music.service.impl;

import com.javaclimb.music.dao.ConsumerMapper;
import com.javaclimb.music.domain.Consumer;
import com.javaclimb.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public Consumer selectByPrimaryKey(Integer id){
        return consumerMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean insert(Consumer consumer) {
        return consumerMapper.insert(consumer) >0;
    }

    @Override
    public boolean update(Consumer consumer) {
        return consumerMapper.updateUserMsg(consumer) >0;
    }

    @Override
    public boolean updateUserAvator(Consumer consumer) {

        return consumerMapper.updateUserAvator(consumer) >0;
    }

    @Override
    public Consumer getByUsername(String username) {
        return consumerMapper.existUsername(username);
    }

    /**
     *
     * @param username
     * @param password
     */
    @Override
    public boolean vertifyPassword(String username, String password) {
        return consumerMapper.verifyPassword(username, password)>0;
    }


    @Override
    public boolean delete(Integer id) {
        return consumerMapper.deleteUser(id) >0;
    }

    @Override
    public List<Consumer> selectPageConsumer(Integer pageNum,Integer pageSize) {
        return consumerMapper.selectPageUser(pageNum,pageSize);
    }
}


