package com.javaclimb.music.dao;

import com.javaclimb.music.domain.Song;
import com.javaclimb.music.domain.SongWithSinger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 歌曲dao
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
     * 根据主键查询歌曲和歌曲的歌手
     */
    public SongWithSinger selectByPrimaryKey(Integer id);
    /**
     * 分页查询歌曲
     */
    public List<Song> selectPageSong(Integer pageNum,Integer pageSize);
    /**
     * 根据歌手id查询
     */
    public List<Song> songOfSingerId(Integer id);
    /**
     * 根据歌名模糊查询
     */
    public List<Song> songOfName(String name);

}
