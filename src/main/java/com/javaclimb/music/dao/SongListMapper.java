package com.javaclimb.music.dao;

import com.javaclimb.music.domain.SongList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 歌单dao
 */
@Mapper
public interface SongListMapper {
    /**
     * 增加
     */
    public int insert(SongList songList);
    /**
     * 修改
     */
    public int update(SongList songList);
    /**
     * 删除
     */
    public int delete(Integer id);
    /**
     * 根据主键查询对象
     */
    public SongList selectByPrimaryKey(Integer id);
    /**
     * 查询所有歌单
     */
    public List<SongList> selectPageSongList(Integer pageNum,Integer pageSize);
    /**
     * 根据标题精确查询歌单列表
     */
    public  List<SongList> songListOfName(String title);
    /**
     * 根据标题模糊查询歌单列表
     */
    public  List<SongList> likeTitle(String title);
    /**
     * 根据风格模糊查询歌单列表
     */
    public  List<SongList> likeStyle(String style);
}
