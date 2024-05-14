package com.javaclimb.music.dao;

import com.javaclimb.music.domain.Song;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 歌手dao
 */
@Mapper
public interface SongMapper {
    /**
     * 增加
     */
    public int insert(Song song);
    /**
     * 修改
     */
    public int update(Song song);
    /**
     * 根据主键删除
     */
    public int delete(Integer id);
    /**
     * 根据主键查询整个对象
     */
    public Song selectByPrimaryKey(Integer id);
    /**
     * 查询所有歌曲
     */
    public List<Song> allSong();
    /**
     * 根据歌手id查询
     */
    public List<Song> songOfSingerId(Integer id);
    /**
     * 根据歌名模糊查询
     */
    public List<Song> songOfName(String name);

}
