package com.javaclimb.music.service.impl;

import com.javaclimb.music.dao.RankMapper;
import com.javaclimb.music.domain.Rank;
import com.javaclimb.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankMapper rankMapper;

    //增加评分
    @Override
    public boolean insert(Rank rank) {
        return rankMapper.insert(rank)>0;
    }

    //查询总分
    @Override
    public int selectScoreSum(Integer songListId) {
        return rankMapper.selectScoreSum(songListId);
    }

    //查询评分总人数
    @Override
    public int selectRankNum(Integer songListId) {
        return rankMapper.selectRankNum(songListId);
    }

    @Override
    public int rankOfSongListId(Integer songListId) {
        int rankNum=rankMapper.selectRankNum(songListId);
        if (rankNum==0)
            return 5;
        return rankMapper.selectScoreSum(songListId)/rankNum;
    }
}
