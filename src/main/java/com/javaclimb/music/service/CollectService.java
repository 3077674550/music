package com.javaclimb.music.service;

import com.javaclimb.music.domain.Collect;

import java.util.List;

public interface CollectService {

    boolean addCollection(Collect collect);

    boolean existSongId(Integer userId, Integer songId);

    boolean updateCollectMsg(Collect collect);

    boolean deleteCollect(Integer userId, Integer songId);

    List<Collect> selectPageCollect(Integer pageNum,Integer pageSize);

    List<Collect> collectionOfUser(Integer userId);
}
