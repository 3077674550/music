package com.javaclimb.music.dao;

import com.javaclimb.music.domain.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface CollectMapper {

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);



    int updateByPrimaryKey(Collect record);

    int existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

    int updateCollectMsg(Collect collect);

    int deleteCollect(@Param("userId") Integer userId, @Param("songId") Integer songId);

    List<Collect> allCollect();

    List<Collect> collectionOfUser(Integer userId);
}

