package com.javaclimb.music.dao;
import com.javaclimb.music.domain.Rank;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价dao
 */
@Mapper
public interface RankMapper {
    /**
     * 增加
     */
    public int insert(Rank rank);
    /**
     * 查询总分
     */
    public int selectScoreSum(Integer songListId);
    /**
     * 查询总评分人数
     */
    public int selectRankNum(Integer songListId);

}
