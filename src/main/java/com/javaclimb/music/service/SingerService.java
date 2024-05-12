package com.javaclimb.music.service;

import com.javaclimb.music.domain.Singer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SingerService {
    //增加
    public boolean insert(Singer singer);

    //修改
    public boolean update(Singer singer);

    //删除
    public boolean delete(Integer id);


    //根据主键查询对象
    public Singer selectByPrimaryKey(Integer id);

    //查询所有歌手
    public List<Singer> allSinger();

    //根据标歌手名字模糊查询列表
    public  List<Singer> SingerOfName(String name);

    //根据性别查询歌手
    public List<Singer> SingerOfSex(Integer sex);
}